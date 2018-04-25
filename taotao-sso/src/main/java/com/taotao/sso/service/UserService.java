package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {
	public TaotaoResult login(String username, String password);
	public TaotaoResult createUser(TbUser user);
	public TaotaoResult check(String str, Integer type);
}
