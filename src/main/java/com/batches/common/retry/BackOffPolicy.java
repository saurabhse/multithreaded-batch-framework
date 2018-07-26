package com.batches.common.retry;

import org.springframework.retry.backoff.FixedBackOffPolicy;

public class BackOffPolicy {
	
	private String resource;
	private FixedBackOffPolicy backOffPolicy;
	
	public BackOffPolicy(String resource, FixedBackOffPolicy backOffPolicy  ) {
		this.resource = resource;
		this.backOffPolicy = backOffPolicy;
	}
	
	public FixedBackOffPolicy getBackOffPolicy() {
		backOffPolicy.setBackOffPeriod(Long.valueOf(20));
		return backOffPolicy;
	}

}
