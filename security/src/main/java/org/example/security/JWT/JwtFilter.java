package org.example.security.JWT;

import jakarta.servlet.http.HttpServletRequest;

public class JwtFilter {

    public String getToken(HttpServletRequest httpRequest) {
        String authorization = httpRequest.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer "))
            return authorization.substring(7);
        return null;
    }
}
