package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/showLogin")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public TaotaoResult login(String username, String password, Model model) {
		model.addAttribute("redirect", null);
		return TaotaoResult.ok();
	}
}
