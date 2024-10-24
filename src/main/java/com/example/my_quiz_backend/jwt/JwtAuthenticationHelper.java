package com.example.my_quiz_backend.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuthenticationHelper {

	private final String secrete = "vishwanathkkhuli19012001mangalwadhaliyalkarnatakaisaverysecureandlongkey";
	
	private static final long JWT_TOKEN_VALIDITY = 7 * 24 * 60*60;
	
	private static final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60; // 7 days in seconds

	public String getUsernameFromToken(String token) {
		Claims claims = getClaimsFromToken(token);
        return claims.getSubject(); 
	}
	
	 public Claims getClaimsFromToken(String token) {
	        Claims claims = Jwts.parserBuilder()
	                            .setSigningKey(secrete.getBytes())  // Use the signing key
	                            .build()
	                            .parseClaimsJws(token)
	                            .getBody();
	        return claims;
	    }
	 
	 public boolean isTokenExpired(String token) {
		 Claims claims = getClaimsFromToken(token);
		 Date expDate = claims.getExpiration();
		 return expDate.before(new Date());
	 }

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(new SecretKeySpec(secrete.getBytes(), SignatureAlgorithm.HS512.getJcaName()),SignatureAlgorithm.HS512)
				.compact();
	}
	
	public String generateRefreshToken(UserDetails userDetails) {
	    Map<String, Object> claims = new HashMap<>();
	    
	    return Jwts.builder()
	            .setClaims(claims)
	            .setSubject(userDetails.getUsername())
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY * 1000)) // Longer expiry time
	            .signWith(new SecretKeySpec(secrete.getBytes(), SignatureAlgorithm.HS512.getJcaName()), SignatureAlgorithm.HS512)
	            .compact();
	}
	
	public boolean isTokenValid(String token) {
	    try {
	        Jwts.parserBuilder()
	            .setSigningKey(secrete.getBytes())
	            .build()
	            .parseClaimsJws(token);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}


}
