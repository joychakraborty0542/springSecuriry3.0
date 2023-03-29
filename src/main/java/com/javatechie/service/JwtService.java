package com.javatechie.service;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {
	
	private static final String SECRET = "6E5A723474377721";


	public String generateToken(String userName)
	{
		Map<String,Object> claims=new HashMap<>();
		 
		return createToken(claims,userName);
	
		
	}

	 private String createToken(Map<String, Object> claims, String userName) {
	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(userName)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
	                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	    }
	 
	 

	
	 private Key getSignKey() {
	        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
	        return Keys.hmacShaKeyFor(keyBytes);
	    }
}
