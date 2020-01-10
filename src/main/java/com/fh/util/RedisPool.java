package com.fh.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private RedisPool() {

    }

    private static JedisPool jedisPool;

    private static void initPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMinIdle(100);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(jedisPoolConfig, "192.168.154.128", 6379, 100, "123456");
    }

    // 静态块，只执行一次，在加载类的时候执行
    static {
        initPool();
    }

    public static Jedis getResource() {
        return jedisPool.getResource();
    }
}
