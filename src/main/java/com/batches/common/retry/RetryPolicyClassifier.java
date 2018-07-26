package com.batches.common.retry;

import java.util.HashMap;
import java.util.Map;

import org.springframework.retry.RetryPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;

import com.batches.common.exception.MyException;

public class RetryPolicyClassifier {
	
	private String resource;
	private ExceptionClassifierRetryPolicy classifierPolicy;
	private SimpleRetryPolicy retryPolicy;
	
	
	
	public RetryPolicyClassifier(String resource, ExceptionClassifierRetryPolicy classifierPolicy,SimpleRetryPolicy retryPolicy  ) {
		this.resource = resource;
		this.classifierPolicy = classifierPolicy;
		this.retryPolicy = retryPolicy;
	}
	
	public ExceptionClassifierRetryPolicy getRetryPolicy() {
		Map<Class <? extends Throwable>,RetryPolicy> map = new HashMap<Class <? extends Throwable>,RetryPolicy>();
		retryPolicy.setMaxAttempts(5);
		map.put(MyException.class, retryPolicy);
		classifierPolicy.setPolicyMap(map);
		return classifierPolicy;
	}
}
