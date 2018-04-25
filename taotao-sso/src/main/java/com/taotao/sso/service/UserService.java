package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;

public interface UserService {
	public TaotaoResult login(String username, String password);
}
