package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

/**
 * @author shijun_li
 * @date 2018年4月9日 下午9:27:48
 * 
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List getCategoryList(long parentId) {
		TbContentCategoryExample categoryExample = new TbContentCategoryExample();
		Criteria criteria = categoryExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(categoryExample);
		List resultList = new ArrayList<>();
		for(TbContentCategory tbContentCategory : list) {
			Map map = new HashMap<>();
			map.put("id", tbContentCategory.getId());
			map.put("text", tbContentCategory.getName());
			map.put("state", tbContentCategory.getIsParent()? "closed":"open");
			resultList.add(map);
		}
		return resultList;
	}

	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		TbContentCategory tbContentCategory = new TbContentCategory();
		tbContentCategory.setName(name);
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setStatus(1);
		tbContentCategory.setIsParent(false);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setUpdated(new Date());
		tbContentCategoryMapper.insert(tbContentCategory);
		
		TbContentCategory parentCategory = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentCategory.getIsParent()) {
			parentCategory.setIsParent(true);
			//更新
			tbContentCategoryMapper.updateByPrimaryKey(parentCategory);
		}
			
		return TaotaoResult.ok(tbContentCategory);
	}

}
