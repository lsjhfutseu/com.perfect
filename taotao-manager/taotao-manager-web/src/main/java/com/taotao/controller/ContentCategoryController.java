package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;

/**
 * @author shijun_li
 * @date 2018年4月9日 下午9:39:05
 * 
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	ContentCategoryService catgoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List getCategoryList(@RequestParam(value="id",defaultValue="0") long parentId) {
		List list = catgoryService.getCategoryList(parentId);
		return list;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult addContentCatgory(long parentId, String name) {
		return catgoryService.insertContentCategory(parentId, name);
	}
}
