package com.taotao.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctc.wstx.util.StringUtil;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping("/showLogin")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/showRegister")
	public String showRegister() {
		return "register";
	}
	
	@RequestMapping("/check/{str}/{type}")
	@ResponseBody
	public Object check(@PathVariable String str, @PathVariable Integer type, String r) {
		
		TaotaoResult result = null;
		if(StringUtils.isBlank(str))
			result = TaotaoResult.build(400, "校验内容不可为空");
		if(type == null || (type != 1 && type != 2 && type != 3))
			result = TaotaoResult.build(400, "校验类型出错");
		//不为null说明校验出错
		if(result != null && r != null) {
			MappingJacksonValue back = new MappingJacksonValue(result);
			back.setJsonpFunction(r);
			return back;
		}
		else if (result != null && r == null)
			return result;
		else {
			result =  userService.check(str, type);
			if(r != null) {
				MappingJacksonValue back = new MappingJacksonValue(result);
				back.setJsonpFunction(r);
				return back;
			}
			else return result;
		}
		
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public TaotaoResult login(String username, String password, Model model) {
		model.addAttribute("redirect", null);
		return userService.login(username, password);
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public TaotaoResult createUser(String username, String password, String phone) {
		TbUser user = new TbUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		return userService.createUser(user);
	}
}
