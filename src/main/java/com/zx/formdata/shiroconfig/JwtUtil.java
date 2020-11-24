package com.zx.formdata.shiroconfig;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;



import java.util.Date;

/**
 * @author create by zhaoxu
 * @create 2020/11/15
 */
@Slf4j
public class JwtUtil {
    /**
     * JWT验证过期时间 EXPIRE_TIME 分钟
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            System.out.println(token);
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            log.info("登录验证成功!");
            return true;
        } catch (Exception exception) {
//            exception.printStackTrace();
            log.error("JwtUtil登录验证失败!");

            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成token签名EXPIRE_TIME 分钟后过期
     *
     * @param username 用户名(电话号码)
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);

    }

    public static void main(String[] args) {
        /**
         * 测试生成一个token
         */
        String sign = sign("admin", "1");
        log.warn("测试生成一个token\n" + sign);
        String token  = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDU0MTM4OTMsInVzZXJuYW1lIjoiYWRtaW4ifQ.LYQ00LE_A7RHffhu49yWbAAwbWGHrVX0T1zLtHAbCk8";
        verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDU0MTgwNjQsInVzZXJuYW1lIjoiYWRtaW4ifQ.g8M222SDXliirBAa0A_SIjDAEGxTxaQ4-eldH0DItaQ","admin","1");
        System.out.println(getUsername(sign));
    }

    }
