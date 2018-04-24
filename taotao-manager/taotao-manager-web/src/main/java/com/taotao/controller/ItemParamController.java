package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

/**
 * @author shijun_li
 * @date 2018年3月28日 下午9:09:29
 * 
 */
@RequestMapping("/item/param")
@Controller
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable long cid) {
		
		TaotaoResult taotaoResult = itemParamService.getItemParamByCid(cid);
		return taotaoResult;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable long cid, String paramData) {
		TbItemParam tbItemParam = new TbItemParam();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		TaotaoResult result = itemParamService.insertItemParam(tbItemParam);
		return result;
	}
}
