package com.example.authentication.Security;

import org.springframework.http.ResponseCookie;

public class CookieUtil {
    public static ResponseCookie create(String name, String value, Boolean secure, Integer maxAge, String domain){
        ResponseCookie cookie = ResponseCookie
                .from(name, value)
                .httpOnly(true)
                .secure(secure)
                .path("/")
                .maxAge(maxAge)
                .domain(domain)
                .build();
        return cookie;
    }
}
