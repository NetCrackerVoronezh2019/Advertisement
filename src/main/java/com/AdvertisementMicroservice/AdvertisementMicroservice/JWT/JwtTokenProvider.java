package com.AdvertisementMicroservice.AdvertisementMicroservice.JWT;

import org.springframework.beans.factory.annotation.Value;
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
	 
	public String createTokenForMicroservice()
    {
    	Claims claims=Jwts.claims().setSubject("AdvertisementMicroservice");
    	 return Jwts.builder()
                 .setClaims(claims)
                 .signWith(SignatureAlgorithm.HS256, this.microserviceSecret)
                 .compact();
    }
    
    
    public boolean validateMicroserviceToken(String token)
    {
    	 try {
             Jws<Claims> claims = Jwts.parser().setSigningKey(this.microserviceSecret).parseClaimsJws(token);
             if (!claims.getBody().getSubject().equals("AdvertisementMicroservice")) {
                 return false;
             }

             return true;
         } catch (JwtException |IllegalArgumentException e) {
            return false;
         }
    }
    
}
