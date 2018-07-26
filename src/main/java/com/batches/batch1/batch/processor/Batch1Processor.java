package com.batches.batch1.batch.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

public class Batch1Processor implements ItemProcessor<String,List<String>> {

	@BeforeStep
	public void init(final StepExecution stepExec) {
		Map<String, Object> map = (Map<String, Object>)stepExec.getExecutionContext().get("partition");
	}
	
	@Override
	public List<String> process(String item) throws Exception {
		// do some processing and prepare list of objects in this case Strings to be sent to writer
		List<String> processedRecords = new ArrayList<String>();
		return processedRecords;
	}

}
