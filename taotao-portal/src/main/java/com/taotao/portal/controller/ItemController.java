package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.pojo.TbItem;
import com.taotao.portal.service.ItemService;

/**
 * @author shijun_li
 * @date 2018年4月24日 下午8:29:14
 * 
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired 
	ItemService itemService;
	@RequestMapping("/search")
	public String getItemListByQuerry(String q, Model model) throws UnsupportedEncodingException {
		q = new String(q.getBytes("iso-8859-1"),"utf-8");
		List<TbItem>list = itemService.getItemListByQuerry(q);
		model.addAttribute("itemList", list);
		return "search";
	}
}
