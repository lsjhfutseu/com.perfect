package com.taotao.sso.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.sso.dao.JedisCLient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author shijun_li
 * @date 2018年4月14日 下午6:32:20
 * 
 */
public class JedisClientSingle implements JedisCLient {

	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.set(key,value);
		jedis.close();
		return string;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long string = jedis.hset(hkey,key,value);
		jedis.close();
		return string;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.hget(hkey, key);
		jedis.close();
		return string;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long string = jedis.incr(key);
		jedis.close();
		return string;
	}

	@Override
	public long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		Long string = jedis.expire(key, seconds);
		jedis.close();
		return string;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long string = jedis.ttl(key);
		jedis.close();
		return string;
	}

}
