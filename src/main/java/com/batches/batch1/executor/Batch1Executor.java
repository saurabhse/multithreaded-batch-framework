package com.batches.batch1.executor;

import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

import com.batches.common.executor.BatchExecutor;

public class Batch1Executor implements BatchExecutor{
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job batch1Job;

	@Override
	public void initialise(String... params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate(String... params) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute() {
		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("RunDate", new Date());
		JobExecution execution;
		try {
			execution = jobLauncher.run(batch1Job, builder.toJobParameters());
			if(BatchStatus.FAILED.equals(execution.getStatus())){
				throw new RuntimeException();
			}
		}catch(JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
				JobParametersInvalidException e) {
			throw new RuntimeException();
		}
	}

}
