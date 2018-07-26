package com.batches.batch1.config;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.batches.batch1.batch.partitioner.Batch1Partitioner;
import com.batches.batch1.batch.partitioner.MyPartitioner;
import com.batches.batch1.batch.processor.Batch1Processor;
import com.batches.batch1.batch.reader.Batch1Reader;
import com.batches.batch1.batch.writer.Batch1Writer;
import com.batches.batch1.executor.Batch1Executor;
import com.batches.batch1.tasklet.TaskletClass;
import com.batches.common.batch.partition.Partition;
import com.batches.common.batch.partition.PartitionStrategy;
import com.batches.common.executor.BatchExecutor;

@Configuration
@EnableBatchProcessing
@EnableAspectJAutoProxy
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
//@Import({ServiceConfig.class})
@PropertySource("classpath:application.properties")
public class Batch1Config {
	
	@Autowired
	private StepBuilderFactory  stepBuilderFactry;
	
	@Autowired
	private JobBuilderFactory  jobBuilderFactry;
	
	@Bean
	public BatchExecutor batch1() {
		return new Batch1Executor();
	}

	@Bean
	public Job batch1Job() {
		return jobBuilderFactry.get("batch1Job")
				.start(taskletStep())
				.next(partitionStep())
				.build();
	}
	
	@Bean
	public Step taskletStep() {
		return stepBuilderFactry.get("taskletStep")
				.tasklet(tasklet())
				.build();
	}
	
	@Bean
	public Tasklet tasklet() {
		return new TaskletClass();
	}
	
	
	@Bean
	public Step partitionStep() {
		return stepBuilderFactry.get("partitionStep")
				.partitioner(processingStep())
				.partitioner("processingStep",partitioner())
				.gridSize(2)
				.taskExecutor(taskExecutor())
				.build();
	}
	
	@Bean
	public Step processingStep() {
		return stepBuilderFactry.get("processingStep")
				.<String,List<String>> chunk(1)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
	
	@Bean
	public AsyncListenableTaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
		executor.setThreadNamePrefix("Partition-");
		return executor;
	}
	
	@Bean
	public Partitioner partitioner() {
		return new MyPartitioner();
	}
	
	@StepScope
	@Bean
	public Batch1Reader reader() {
		return new Batch1Reader();
	}
	
	@StepScope
	@Bean
	public Batch1Writer writer() {
		return new Batch1Writer();
	}
	
	@StepScope
	@Bean
	public Batch1Processor processor() {
		return new Batch1Processor();
	}
	
	@Bean
	public PartitionStrategy partitionStrategy() {
		return new Batch1Partitioner();
		
	}
	
	@Bean
	public Partition partition() {
		return new Partition();
		
	}
}
