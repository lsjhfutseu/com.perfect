package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 获取分类列表
 * @author shijun_li
 * @date 2018年4月3日 下午9:43:48
 * 
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	TbItemCatMapper itemCatMapper;
	
	@Override
	public CatResult getItemCatList() {
		
		CatResult catResult = new CatResult();
		catResult.setData(getCatList(0));
		return catResult;
	}

	//lsj 采用递归获取数据
	private List<?> getCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat>list = itemCatMapper.selectByExample(example);
		List resultList = new ArrayList<>();
		int count = 0;
		for(TbItemCat tbItemCat : list) {
			CatNode catNode = new CatNode();
			if (tbItemCat.getIsParent()) {
				if (parentId == 0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				}
				else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				resultList.add(catNode);
				count++;
				if(parentId == 0 && count >= 14) {
					break;
				}
			}
			else {
				resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
			}
		}
		return resultList;
	}

}
