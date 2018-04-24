package com.taotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * @author shijun_li
 * @date 2018年4月14日 下午2:32:24
 * 
 */

public class JedisTest {
   
	@Test
	public void testJedisSingle() {
		Jedis jedis = new Jedis("192.168.111.128", 6379);
		jedis.set("test", "88");
		String teString = jedis.get("test");
		System.out.println(teString);
		jedis.close();
	}
	
	//使用连接池
	@Test
	public void testJedisPool() {
		JedisPool pool = new JedisPool("192.168.111.128", 6379);
		Jedis jedis = pool.getResource();
		String string = jedis.get("test");
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	
	//集群测试
	@Test
	public void testJedisCluster() {
		HashSet<HostAndPort>nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.111.128", 7001));
		nodes.add(new HostAndPort("192.168.111.128", 7002));
		nodes.add(new HostAndPort("192.168.111.128", 7003));
		nodes.add(new HostAndPort("192.168.111.128", 7004));
		nodes.add(new HostAndPort("192.168.111.128", 7005));
		nodes.add(new HostAndPort("192.168.111.128", 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("key1", "985");
		String string = cluster.get("key1");
		System.out.println(string);
		cluster.close();
	}
	
	@Test
	public void testSpringJedisSingle() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
		JedisPool jedisPool = (JedisPool)applicationContext.getBean("redisClient");
		Jedis jedis = jedisPool.getResource();
		jedis.set("hello", "world");
		System.out.println(jedis.get("hello"));
		jedis.close();
		jedisPool.close();
	}
	
	@Test
	public void testSpringJedisCluster() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
		JedisCluster jedisCluster = (JedisCluster)applicationContext.getBean("redisClient");
		System.out.println(jedisCluster.get("key1"));
		jedisCluster.close();
	}
}
