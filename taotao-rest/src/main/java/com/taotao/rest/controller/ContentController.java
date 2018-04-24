package com.taotao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;

/**
 * @author shijun_li
 * @date 2018年4月11日 下午7:56:21
 * 
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{cid}")
	@ResponseBody
	public TaotaoResult getContentByCid(@PathVariable long cid) {
		try {
			List<TbContent>list = contentService.getContentList(cid);
			return TaotaoResult.ok(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	
}
