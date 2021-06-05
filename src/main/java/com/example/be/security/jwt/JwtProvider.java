package com.example.be.security.jwt;

import com.example.be.security.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

@Component
public class JwtProvider {
    public static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecretKey = "jwtGrokonezSecretKey";
    private int jwtExpiration = 86400;





    public String createToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrinciple.getUsername())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Jwt signature failed! ", e);
        } catch (MalformedJwtException e) {
            logger.error("Jwt invalid formed! ", e);
        } catch (ExpiredJwtException e) {
            logger.error("Jwt expired! ", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Jwt unsupported!", e);
        } catch (IllegalArgumentException e) {
            logger.error("Jwt is empty", e);
        }
        return false;
    }

    public String getUserNameFromToken(String token) {
        String userName = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }
}
