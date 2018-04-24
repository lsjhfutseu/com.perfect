package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;

/**
 * @author shijun_li
 * @date 2018年4月9日 下午9:25:39
 * 
 */
public interface ContentCategoryService {
	public List getCategoryList(long parentId);
	public TaotaoResult insertContentCategory(long parentId, String name);
}
