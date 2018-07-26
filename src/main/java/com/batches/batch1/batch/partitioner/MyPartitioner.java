package com.batches.batch1.batch.partitioner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Arrays;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.batches.common.batch.partition.Partition;


public class MyPartitioner implements Partitioner{

	@Autowired
	Partition partition;
	
	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		Map<String, ExecutionContext> execParams = new HashMap<String, ExecutionContext>();
		//fetch number of resources
		//List<Entity<Object,Object>> resources =
		String [] arr = {"a","b"};
		List resources = Arrays.asList(arr);
		int partitions =0;
		for(int i=0;i<resources.size();i++) {
			//get total count per resource
			int count = 4;
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("total", count);
			params.put("gridSize", gridSize); // or fetch it from system properties from configuration table
			params.put("partitions", partitions);
			partition.partition(execParams, params);
			
		}
		
		return execParams;
	}

}
