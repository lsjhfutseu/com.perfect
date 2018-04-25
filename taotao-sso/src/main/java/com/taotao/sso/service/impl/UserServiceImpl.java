package com.taotao.sso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	TbUserMapper userMapper;
	@Override
	public TaotaoResult login(String username, String password) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			if(list.get(0).getPassword().equals(password)) {
				return TaotaoResult.ok();
			}
		}
		return TaotaoResult.build(400, "登录失败");
	}

}
