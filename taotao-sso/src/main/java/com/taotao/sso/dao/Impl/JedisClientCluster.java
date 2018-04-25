package com.taotao.sso.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.sso.dao.JedisCLient;

import redis.clients.jedis.JedisCluster;

/**
 * @author shijun_li
 * @date 2018年4月14日 下午6:42:19
 * 
 */
public class JedisClientCluster implements JedisCLient {

	@Autowired
	JedisCluster jedisCluster;
	
	@Override
	public String get(String key) {
		String string = jedisCluster.get(key);
		return string;
	}

	@Override
	public String set(String key, String value) {
		String string = jedisCluster.set(key, value);
		return string;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		long string = jedisCluster.hset(hkey, key, value);
		return string;
	}

	@Override
	public String hget(String hkey, String key) {
		String string = jedisCluster.hget(hkey, key);
		return string;
	}

	@Override
	public long incr(String key) {
		long string = jedisCluster.incr(key);
		return string;
	}

	@Override
	public long expire(String key, int seconds) {
		long string = jedisCluster.expire(key, seconds);
		return string;
	}

	@Override
	public long ttl(String key) {
		long string = jedisCluster.ttl(key);
		return string;
	}

}
