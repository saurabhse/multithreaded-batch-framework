package com.batches.common.aspects;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingAspect implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String method = invocation.getMethod().getName();
		String className = invocation.getThis().getClass().getName();
		System.out.println("start");
		Object result = invocation.proceed();
		System.out.println("end");
		return result;
	}

}
