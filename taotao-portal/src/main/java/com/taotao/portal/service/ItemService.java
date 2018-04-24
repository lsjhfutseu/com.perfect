package com.taotao.portal.service;

import java.util.List;

import com.taotao.pojo.TbItem;

/**
 * 商品服务
 * @author shijun_li
 * @date 2018年4月24日 下午8:18:48
 * 
 */

public interface ItemService {
	public List<TbItem> getItemListByQuerry(String querry);
}
