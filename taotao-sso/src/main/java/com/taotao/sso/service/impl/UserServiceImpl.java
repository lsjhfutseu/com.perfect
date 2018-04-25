package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.JedisCLient;
import com.taotao.sso.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	TbUserMapper userMapper;
	
	@Autowired
	JedisCLient jedis;
	
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	
	@Override
	public TaotaoResult login(String username, String password) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		if(list == null || list.size() == 0) {
			return TaotaoResult.build(400, "登录失败");
		}
		TbUser user = list.get(0);
		if(DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			String token = UUID.randomUUID().toString();
			user.setPassword("");
			jedis.set(REDIS_USER_SESSION_KEY +":" + token, JsonUtils.objectToJson(user));
			jedis.expire(REDIS_USER_SESSION_KEY +":" + token, 86400);
			return TaotaoResult.ok(token);
		}
		return TaotaoResult.build(400, "登录失败");
	}

	//用户注册
	@Override
	public TaotaoResult createUser(TbUser user) {
		user.setCreated(new Date());
		user.setUpdated(new Date());
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		try {
			userMapper.insert(user);
			return TaotaoResult.ok();
		}
		catch (Exception e) {
			return TaotaoResult.build(400, "新建用户错误");
		}
		
		
	}

	//注册校验，type表示校验类型
	@Override
	public TaotaoResult check(String str, Integer type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if(type == 1) {
			criteria.andUsernameEqualTo(str);
		}
		else if(type == 2) {
			criteria.andPhoneEqualTo(str);
		}
		else {
			criteria.andEmailEqualTo(str);
		}
		List<TbUser> list = userMapper.selectByExample(example);
		if(list == null || list.size() == 0) {
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

}
