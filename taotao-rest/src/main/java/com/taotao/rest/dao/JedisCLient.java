package com.taotao.rest.dao;
/**
 * @author shijun_li
 * @date 2018年4月14日 下午6:26:47
 * 
 */
public interface JedisCLient {
	String get(String key);
	String set(String key, String value);
	long hset(String hkey, String key, String value);
	String hget(String hkey, String key);
	//自增
	long incr(String key);
	//设置有效期
	long expire(String key, int seconds);
	//查看有效期
	long ttl(String key);
}
