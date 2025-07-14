package com.example.demo.application.user.helper;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.example.demo.application.user.config.UserConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHelper {
	private final UserConfig userConfig;

	public TokenHelper(UserConfig userConfig) {
		this.userConfig = userConfig;
	}

	public String generateToken(UUID userId) {
		byte[] secret = userConfig.getTokenSecret().getBytes();
		SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;

		Date issuedAt = new Date();
		Date expiresAt = new Date(System.currentTimeMillis() + userConfig.getTokenExpiry());
		Key key = new SecretKeySpec(secret, algorithm.getJcaName());

		return Jwts.builder().setSubject(userId.toString()).setIssuedAt(issuedAt).setExpiration(expiresAt)
				.signWith(key, algorithm).compact();
	}

	public String validateToken(String token) {
		byte[] secret = userConfig.getTokenSecret().getBytes();

		Claims claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();

		return claims.getSubject();
	}
}
