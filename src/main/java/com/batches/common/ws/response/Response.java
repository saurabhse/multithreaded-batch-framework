package com.batches.common.ws.response;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Response {
	
	Map<String,List<String>> columns = new LinkedHashMap<>();
	List<Map<String,Object>> rows = new LinkedList<Map<String,Object>>();
	public Map<String, List<String>> getColumns() {
		return columns;
	}
	public void setColumns(Map<String, List<String>> columns) {
		this.columns = columns;
	}
	public List<Map<String, Object>> getRows() {
		return rows;
	}
	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}
	

}
