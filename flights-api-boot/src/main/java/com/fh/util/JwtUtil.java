package com.fh.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    private static final String SECRET_KEY = "sd237#@sd!$%sdk-=";

    /**
     * 加密生成token
     * @param object 载体信息
     * @param <T>
     * @return
     */
    public static <T> String createToken(T object) {
        try {
            final Algorithm signer = Algorithm.HMAC256(SECRET_KEY);//生成签名
            String token = JWT.create()
                    .withSubject(JSON.toJSONString(object))//主题，科目
                    .sign(signer);
            return token;
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 解析验证token
     * @param token 加密后的token字符串
     * @return
     */
    public static String verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
