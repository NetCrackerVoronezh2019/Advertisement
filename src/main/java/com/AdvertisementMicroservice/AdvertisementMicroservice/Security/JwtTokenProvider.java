package com.AdvertisementMicroservice.AdvertisementMicroservice.Security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.microserviceSecret}")
    private String microserviceSecret;
    
    @Value("${jwt.token.microserviceSubject}")
    private String microserviceSubject;
    
    
    public String createTokenForMicroservice()
    {
    	Claims claims=Jwts.claims().setSubject(this.microserviceSubject);
    	 return Jwts.builder()
                 .setClaims(claims)
                 .signWith(SignatureAlgorithm.HS256, this.microserviceSecret)
                 .compact();
    }
    
    
    public boolean validateMicroserviceToken(String token)
    {
    	 try {
             Jws<Claims> claims = Jwts.parser().setSigningKey(this.microserviceSecret).parseClaimsJws(token);
             if (!claims.getBody().getSubject().equals(this.microserviceSubject)) {
                 return false;
             }

             return true;
         } catch (JwtException |IllegalArgumentException e) {
            return false;
         }
    }
    
    public Authentication getAuthenticationForMicro(String token) {
         return new UsernamePasswordAuthenticationToken(new AuthMicroserviceDetails(), "", new AuthMicroserviceDetails().getAuthorities());
     }
    
    public String resolveMicroToken(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        if (token != null) {
            return token;
        }
        return null;
    }

}
