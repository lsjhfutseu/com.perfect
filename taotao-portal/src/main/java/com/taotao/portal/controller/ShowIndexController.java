package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.portal.service.ContentService;

/**
 * @author shijun_li
 * @date 2018年4月2日 下午9:10:47
 * 
 */
@Controller
public class ShowIndexController {
	@Autowired
	ContentService contentService;
	@RequestMapping("/index")
	public String showIndex(Model model) {
		String result = contentService.getContentList();
		model.addAttribute("ad1", result);
		return "index";
	}
}
