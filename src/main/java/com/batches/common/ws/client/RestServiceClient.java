package com.batches.common.ws.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import com.batches.common.retry.BackOffPolicy;
import com.batches.common.retry.RetryPolicyClassifier;
import com.batches.common.ws.response.Response;
import com.batches.common.ws.template.RestService;

public class RestServiceClient implements RestService {
	
	@Autowired
	HttpHeaders headers;
	
	@Autowired
	RetryPolicyClassifier classifier;
	
	@Autowired
	BackOffPolicy policy;
	
	@Autowired
	RestTemplate template;
	
	@Autowired
	RetryTemplate retryTemplate;
	
	public Response getResponse() {
		HttpEntity<String> entity = new HttpEntity<String>(getHeaders("key",headers));
		buildRetryTemplate(retryTemplate,policy.getBackOffPolicy(),classifier.getRetryPolicy());
		ResponseEntity<Response> resp = RestService.exchangeWithRetry(retryTemplate, template, HttpMethod.GET, entity, "url", Response.class);
				
		return resp.getBody();
	}

}
