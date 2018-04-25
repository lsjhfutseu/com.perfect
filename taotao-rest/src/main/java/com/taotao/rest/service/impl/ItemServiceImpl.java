package com.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.rest.service.ItemService;

/**
 * @author shijun_li
 * @date 2018年4月24日 下午7:57:02
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	TbItemMapper itemMapper;
	
	@Override
	public TaotaoResult getItemListByQuerry(String querry) {
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIsNotNull();
		List<TbItem> list = itemMapper.selectByExample(example);
		for(int index = 0; index < list.size(); ) {
			TbItem item = list.get(index);
			if(!item.getTitle().contains(querry) && !item.getSellPoint().contains(querry)) {
				list.remove(index);
			}
			else index++;	
		}
		return TaotaoResult.ok(list);
	}

	//根据商品id返回商品的详细信息的pojo（满足前台要求）
	@Override
	public TaotaoResult getItemDetailsById(long id) {
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		return null;
	}

}
