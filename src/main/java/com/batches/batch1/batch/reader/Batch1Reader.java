package com.batches.batch1.batch.reader;

import java.util.Map;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Batch1Reader implements ItemReader<String>{
	
	private String threadName;
	private Integer total;
	private Integer from;
	private int numOfRecProcessed = 0;

	@BeforeStep
	public void init(final StepExecution stepExec) {
		Map<String, Object> map = (Map<String, Object>)stepExec.getExecutionContext().get("partition");
		this.total = 	(Integer)map.get("total");
		this.from = (Integer) map.get("from");
		this.threadName = stepExec.getStepName();
		//this.resource = 
		
	}
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println(threadName+" " + total+ " " +from);
		String response = "default"; // normally this would be an object containing records or collection
		if(numOfRecProcessed < total) {
			//fetch records either from file, Database or webservice
			int responseCount = 5 ;
			numOfRecProcessed = numOfRecProcessed + responseCount;
			return response;
		}else {
			System.out.println("All data is processed for thread -> " + threadName);
			return null;
		}
	}

}
