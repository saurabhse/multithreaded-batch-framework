package com.batches.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringApplicationContextUtil {
	
	private static AbstractApplicationContext applicationContext;

	public static void setApplicationContext(String... basepkg) {
		applicationContext = new AnnotationConfigApplicationContext(basepkg);
	}
	
	public static AbstractApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static Object getBean(final String beanName) {
		Object obj = null;
		try {
			obj = getApplicationContext().getBean(beanName);
		}catch(BeansException e) {
			throw e;
		}
		
		return obj;
	}
	
	public static void registerShutDownHook() {
		 getApplicationContext().registerShutdownHook();
	}
	
	public static void setApplicationContext(AbstractApplicationContext  context) {
		SpringApplicationContextUtil.applicationContext = context;
	}
}
