package com.taotao.rest.service;
/**
 * 商品服务
 * @author shijun_li
 * @date 2018年4月24日 下午7:52:38
 * 
 */

import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {
	public TaotaoResult getItemListByQuerry(String querry);
}
