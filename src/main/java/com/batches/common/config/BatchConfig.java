package com.batches.common.config;

import javax.sql.DataSource;

import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.batches.common.aspects.LoggingAspect;
import com.batches.common.constants.Constants;

@Configuration
public class BatchConfig extends DefaultBatchConfigurer{
	
	@Autowired
	DataSource dataSource;
	
	@Override
	@Bean
	protected JobRepository createJobRepository() throws Exception{
		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDataSource(dataSource);
		factory.setTransactionManager(getTransactionManager());
		factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITED");
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	public PlatformTransactionManager getTransactionManager() {
		return new ResourcelessTransactionManager();
	}
	@Bean
	public PointcutAdvisor loggingAdvisor() {
		AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
		String expression = "!execution(* com.batches."+ System.getProperty(Constants.BATCH_NAME.getValue())+".config.*.*(..)) && execution (* com.batches."+ System.getProperty(Constants.BATCH_NAME.getValue())+"..*.*(..))";
		advisor.setExpression(expression);
		advisor.setAdvice(new LoggingAspect());
		return advisor;
	}

}
