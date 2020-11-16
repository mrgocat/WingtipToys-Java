package com.wingtip.webapi.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	@Value("${wingtipsso.tokenSecret}")
	private String tokenSecret;
	
	@Value("${jwt.issuer}")
	private String issuer;
	
	@Value("${jwt.audience}")
	private String audience;
	
	public String getIssuer() {
		return issuer;
	}
	public String getAudience() {
		return audience;
	}
	public String getTokenSecret() {
		return tokenSecret;
	}

}
