package com.batches.batch1.batch.partitioner;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ExecutionContext;

import com.batches.common.batch.partition.PartitionStrategy;



public class Batch1Partitioner implements PartitionStrategy {

	@Override
	public Integer partition(Map<String, ExecutionContext> execParams, Map<String, Object> params) {
		Integer total = (Integer)params.get("total");
		Integer gridSize = (Integer) params.get("gridSize"); 
		Integer partitions =  (Integer) params.get("partitions");
		final int q = total/gridSize;
		final int r = total % gridSize;
		Integer[] results = new Integer[gridSize];
		int counter = 0;
		for(int i=0;i<gridSize;i++) {
			ExecutionContext context = new ExecutionContext();
			Map<String, Object> map = new HashMap<String, Object>();
			results[i]= i < r ? q +1: q;
			partitions++;
			map.put("total", results[i]);
			map.put("from", counter); 
			counter = (counter + results[i]);
			
			context.put("data", map);
			execParams.put("partition"+partitions, context);
		}
		return partitions;
	}

}
