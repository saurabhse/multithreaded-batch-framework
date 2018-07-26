package com.batches.common.constants;

public enum Constants {
	BASE_PACKAGE("com.batches."),
	BATCH_NAME("batchName");
	
	 Constants(String value) {
		this.value = value;
	}
	
	private String value;
	
	public String getValue() {
		return value;
	}

}
