package com.batches.common.ws.template;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import com.batches.common.exception.MyException;



public interface RestService {
	
	default HttpHeaders getHeaders(String key, HttpHeaders headers ) {
		headers.add("Authorization",key);
		return headers;
	}
	
	default HttpHeaders getBasicHeaders(HttpHeaders headers) {
		String cred = "user:pwd";
		byte[] plainCredsBytes = cred.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		headers.add("Authorization","Basic "+base64Creds);
		return headers;
	}
	
	default RetryTemplate buildRetryTemplate(RetryTemplate temp,BackOffPolicy b,RetryPolicy r) {
		temp.setBackOffPolicy(b);
		temp.setRetryPolicy(r);
		return temp;
	}
	
	public static <T> ResponseEntity<T> exchange(RestTemplate temp,HttpMethod method,HttpEntity<String> entity, String url,Class<T> responseType) {
		return temp.exchange(url,method,entity,responseType);
	}
	
	public static <T> ResponseEntity<T> exchangeWithRetry(RetryTemplate temp,RestTemplate template,HttpMethod method,HttpEntity<String> entity, String url,Class<T> responseType) {
		return temp.execute(
				(RetryCallback<ResponseEntity<T>,MyException>) retry -> 
				exchange(template,method,entity,url,responseType)
				);
	}

}
