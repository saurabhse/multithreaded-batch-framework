package com.batches.common.batch.partition;

import java.util.Map;

import org.springframework.batch.item.ExecutionContext;

public interface PartitionStrategy {
	public Integer partition(Map<String, ExecutionContext> execParams,Map<String,Object> params);
}
