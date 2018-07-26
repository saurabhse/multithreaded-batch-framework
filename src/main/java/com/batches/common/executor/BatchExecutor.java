package com.batches.common.executor;

public interface BatchExecutor {

	public void initialise(String... params);
	public void validate(String... params) throws Exception;
	public void execute();
}
