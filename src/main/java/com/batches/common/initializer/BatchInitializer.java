package com.batches.common.initializer;

import com.batches.common.constants.Constants;
import com.batches.common.executor.BatchExecutor;
import com.batches.common.util.SpringApplicationContextUtil;

public class BatchInitializer {

	public static void main(String[] args) {
		System.setProperty("batchName", "batch1");
		//set arguments or pass args
		try {
		String batchName = System.getProperty(Constants.BATCH_NAME.getValue());
		SpringApplicationContextUtil.setApplicationContext(Constants.BASE_PACKAGE.getValue()+batchName);
		BatchExecutor batchExecutor = (BatchExecutor)SpringApplicationContextUtil.getBean(batchName);
		batchExecutor.validate(args);
		batchExecutor.initialise(args);
		batchExecutor.execute();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
