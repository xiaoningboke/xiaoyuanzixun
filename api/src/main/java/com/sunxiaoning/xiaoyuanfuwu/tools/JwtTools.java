package com.sunxiaoning.xiaoyuanfuwu.tools;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class JwtTools {

    public static final String JWT_KEY = "my_password_defined";

    public static String genToken(Object data) {

        try {
            if (!(data instanceof Map || data instanceof Array)) {
                return null;
            }

            HashMap<String, Object> header = new HashMap<>();
            header.put("create", System.currentTimeMillis());

            String originalData = JSON.toJSONString(data);


            return Jwts.builder()
                    .setHeader(header)
                    .setSubject(originalData)
                    .signWith(SignatureAlgorithm.HS256, JWT_KEY)
                    .compact();
        } catch (Exception e) {
            return null;
        }
    }

    public static String parseToken(String token) {
        try {
            String jwtString = Jwts.parser().setSigningKey(JWT_KEY).parseClaimsJws(token).getBody().getSubject();
            return jwtString;
        } catch (Exception e) {
            return null;
        }
    }

    public static void parseToken(String token, HashMap<String, Object> result) {
        try {
            Jwt jwt = Jwts.parser().setSigningKey(JWT_KEY).parseClaimsJws(token);
            Claims claims = (Claims) jwt.getBody();
            result.put("body", claims.getSubject());

            Header header = jwt.getHeader();
            result.put("header", header);

        } catch (Exception e) {

        }

    }
}
