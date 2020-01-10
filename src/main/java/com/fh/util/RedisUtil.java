package com.fh.util;

import redis.clients.jedis.Jedis;

public class RedisUtil {

    public static void set(String key, String value) {
        Jedis resource = null;
        try {
            resource = RedisPool.getResource();
            resource.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != resource) {
                resource.close();
            }
        }
    }

    public static String hget(String key, String field) {
        Jedis resource = null;
        String result = "";
        try {
            resource = RedisPool.getResource();
            result = resource.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != resource) {
                resource.close();
            }
        }
        return result;
    }


    public static Long hdel(String key, String field) {
        Jedis resource = null;
        Long count = 0L;
        try {
            resource = RedisPool.getResource();
            count = resource.hdel(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != resource) {
                resource.close();
            }
        }
        return count;
    }

    public static void expire(String key, int seconds) {
        Jedis resource = null;
        try {
            resource = RedisPool.getResource();
            resource.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != resource) {
                resource.close();
            }
        }
    }

    public static void setEx(String key, String value, int seconds) {
        Jedis resource = null;
        try {
            resource = RedisPool.getResource();
            resource.setex(key, seconds, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != resource) {
                resource.close();
            }
        }
    }

    public static void hset(String key, String field, String value) {
        Jedis resource = null;
        try {
            resource = RedisPool.getResource();
            resource.hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != resource) {
                resource.close();
            }
        }
    }

    public static boolean exist(String key) {
        Jedis resource = null;
        boolean exist = false;
        try {
            resource = RedisPool.getResource();
            exist = resource.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != resource) {
                resource.close();
            }
        }
        return exist;
    }


    public static boolean exist(String key, String field) {
        Jedis resource = null;
        boolean exist = false;
        try {
            resource = RedisPool.getResource();
            exist = resource.hexists(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != resource) {
                resource.close();
            }
        }
        return exist;
    }

    public static Long del(String key) {
        Jedis resource = null;
        Long delCount = 0L;
        try {
            resource = RedisPool.getResource();
            delCount = resource.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != resource) {
                resource.close();
            }
        }
        return delCount;
    }

    public static String get(String key) {
        Jedis resource = null;
        String result = null;
        try {
            resource = RedisPool.getResource();
            result = resource.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != resource) {
                resource.close();
            }
        }
        return result;
    }


}
