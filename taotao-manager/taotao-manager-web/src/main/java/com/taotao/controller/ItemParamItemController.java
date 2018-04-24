package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.service.ItemParamItemService;

/**
 * @author shijun_li
 * @date 2018年3月28日 下午10:30:14
 * 
 */
@Controller
public class ItemParamItemController {
	
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/showItem/{itemId}")
	public String showItemParam(@PathVariable long itemId, Model model) {
		String param = itemParamItemService.getItemParamByItemId(itemId);
		model.addAttribute("params", param);
		return "item";
	}
}
