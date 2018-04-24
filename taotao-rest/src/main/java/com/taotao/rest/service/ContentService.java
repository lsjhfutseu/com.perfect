package com.taotao.rest.service;

import java.util.List;

import com.taotao.pojo.TbContent;

/**
 * @author shijun_li
 * @date 2018年4月11日 下午7:45:37
 * 
 */
public interface ContentService {
	public List<TbContent> getContentList(long contentCid);
}
