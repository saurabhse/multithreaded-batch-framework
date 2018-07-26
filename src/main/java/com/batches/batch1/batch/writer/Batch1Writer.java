package com.batches.batch1.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class Batch1Writer implements ItemWriter<List<String>> {

	@Override
	public void write(List<? extends List<String>> items) throws Exception {
		for(List<String> s : items) {
			//make service calls
		}
		
	}

}
