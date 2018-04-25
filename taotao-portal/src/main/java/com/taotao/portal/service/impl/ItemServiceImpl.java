package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.service.ItemService;

/**
 * @author shijun_li
 * @date 2018年4月24日 下午8:20:48
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Override
	public List<TbItem> getItemListByQuerry(String querry) {
		String string = HttpClientUtil.doGet(REST_BASE_URL + "item/search/" + querry);
		//转化为list                            
		TaotaoResult result = TaotaoResult.formatToList(string, TbItem.class);		
		List<TbItem> list = (List<TbItem>) result.getData();
		return list;
	}

	@Override
	public TbItem getItemDetailsById(long id) {
		String string = HttpClientUtil.doGet(REST_BASE_URL + "item/" + String.valueOf(id));
		//转化为pojo
		TaotaoResult result = TaotaoResult.formatToPojo(string, TbItem.class);
		TbItem item = (TbItem) result.getData();
		return item;
	}

}
