package com.batches.common.batch.partition;

import java.util.Map;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Partition {
	
	@Autowired
	PartitionStrategy partitionStrategy;

	public Integer partition(Map<String, ExecutionContext> execParams,Map<String,Object> params) {
		return partitionStrategy.partition(execParams,params);
	}
}
