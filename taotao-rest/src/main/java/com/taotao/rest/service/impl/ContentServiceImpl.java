package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisCLient;
import com.taotao.rest.service.ContentService;

import redis.clients.jedis.JedisCluster;

/**
 * 获取大广告位
 * @author shijun_li
 * @date 2018年4月11日 下午7:48:56
 * 
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	TbContentMapper contentMapper;
	@Autowired
	private JedisCLient JedisClient;
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public List<TbContent> getContentList(long contentCid) {
		//先从redis缓存中获取内容
		try {
			String string = JedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid+"");
		    if(!StringUtils.isBlank(string)) {
		    	List<TbContent> resultList = JsonUtils.jsonToList(string, TbContent.class);
		    	return resultList;
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		List<TbContent> list = contentMapper.selectByExample(example);
		
		//保存至redis缓存
		try {
			//先把list转换成字符串
			String cacheString = JsonUtils.objectToJson(list);
			JedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid+"", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
