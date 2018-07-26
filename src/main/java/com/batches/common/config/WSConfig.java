package com.batches.common.config;

import java.util.Arrays;

import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.batches.common.exception.ExceptionHandler;
import com.batches.common.retry.BackOffPolicy;
import com.batches.common.retry.RetryPolicyClassifier;
import com.batches.common.ws.httpconverters.ResponseConverter;

public class WSConfig {
	
	@Bean
	public ResponseErrorHandler exceptionHandler() {
		return new ExceptionHandler();
	}
	
	@Bean
	public AbstractHttpMessageConverter<?> converter() {
		return new ResponseConverter();
	}
	
	@Bean
	public HttpHeaders headers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}
	
	@Bean
	public CredentialsProvider basicCredentialsProvider() {
		return new BasicCredentialsProvider();
	}
	
	@Bean
	public RestTemplate template() {
		RestTemplate temp= new RestTemplate();
		temp.setErrorHandler(exceptionHandler());
		temp.getMessageConverters().add(converter());
		return temp;
	}
	
	@Bean
	public RetryTemplate retryTemplate() {
		return new RetryTemplate();
	}
	
	@Bean
	public FixedBackOffPolicy fixedPolicy() {
		return new FixedBackOffPolicy();
	}
	
	@Bean
	public BackOffPolicy policy() {
		return new BackOffPolicy("",fixedPolicy());
	}
	@Bean
	public ExceptionClassifierRetryPolicy classPolicy() {
		return new ExceptionClassifierRetryPolicy();
	}
	
	@Bean
	public SimpleRetryPolicy retryPolicy() {
		return new SimpleRetryPolicy();
	}
	
	@Bean
	public RetryPolicyClassifier classifier() {
		return new RetryPolicyClassifier("",classPolicy(),retryPolicy());
	}

}
