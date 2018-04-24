package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

/**
 * @author shijun_li
 * @date 2018年3月28日 下午8:43:45
 * 
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	TbItemParamMapper itemParamMapper;
	
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if(list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}
	
	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		//补全信息
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入表中
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

}
