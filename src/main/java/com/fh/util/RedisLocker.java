package com.fh.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * redis setnx 锁
 */


public class RedisLocker {

    private static Logger log = LoggerFactory.getLogger(RedisLocker.class);


    /**
     * 返回是否取得锁
     *
     * @param key
     * @param timeout 超时时间毫秒
     * @return
     */
    public static boolean isAcquired(String key, long timeout) {
        //锁标志
        boolean hasLock = false;
        //获取jedis连接
        Jedis jedis = RedisPool.getResource();
        for (; ; ) {
            //SETNX lock.foo <current Unix time + lock timeout + 1>
            long expire = System.currentTimeMillis() + timeout + 1;
            String expireStr = String.valueOf(expire);
            //返回1标识取得锁,0为未取得
            long result = jedis.setnx(key, expireStr);
            //超时时间
            int expireSeconds = (int) (timeout / 1000);
            if (result == 1) {
                //取得锁
                hasLock = true;
                //设置超时，防止一直被锁定
                jedis.expire(key, expireSeconds);
                break;
            } else {
                //取锁失败,说明锁仍然被其他对象保持，检查其是否已经超时
                long prev = Long.valueOf(jedis.get(key));
                if (prev < System.currentTimeMillis()) {
                    //上次的锁已经超时
                    String val = jedis.getSet(key, expireStr);
                    if (val.equals(prev)) {
                        //正常超时
                        hasLock = true;
                        jedis.expire(key, expireSeconds);
                        break;
                    } else {
                        log.debug("RedisLocker:" + key + " 正在锁定");
                        //已被其他进程获取,等待释放
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            log.debug("RedisLocker : 线程获取锁时发生了线程休眠异常");
                            jedis.close();
                        }
                    }
                } else {
                    log.debug("RedisLocker:" + key + " 正在锁定");
                    //上次的锁正常,等待释放
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        log.debug("RedisLocker : 线程获取锁时发生了线程休眠异常");
                        jedis.close();
                    }
                }
            }
        }
        jedis.close();
        return hasLock;
    }

    /**
     * 释放锁
     *
     * @param key
     */
    public static void release(String key) {
        Jedis jedis = RedisPool.getResource();
        ;
        long systs = System.currentTimeMillis();
        if (systs < Long.valueOf(jedis.get(key))) {
            jedis.del(key);
        }
        log.debug("RedisLocker:" + key + "释放锁");
        jedis.close();
    }
}
