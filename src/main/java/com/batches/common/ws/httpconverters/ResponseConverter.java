package com.batches.common.ws.httpconverters;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import com.batches.common.ws.response.Response;

public class ResponseConverter extends AbstractHttpMessageConverter<Response>{

	
	public ResponseConverter() {
		
	}
	
	protected boolean supports(Class<?> clazz) {
		return Response.class.equals(clazz);
	}
	
	@Override
	protected Response readInternal(Class<? extends Response> clazz,HttpInputMessage inputmessage) throws IOException {
		Response r = new Response();
		InputStream is = inputmessage.getBody();
		Scanner s = new Scanner(is).useDelimiter("\\A");
		String responseStr = s.hasNext() ? s.next() : "";
		Map<String,List<String>> columns = new LinkedHashMap<>();
		List<Map<String,Object>> rows = new LinkedList<Map<String,Object>>();
		CSVParser parser = null;
		CSVFormat csvformat = CSVFormat.DEFAULT.withHeader().withDelimiter('\t');
		Reader in = new StringHeader(responseStr);
		
		try {
			csvFileParser = new CSVParser(in,csvformat);
			Map<String,Integer> map = csvFileParser.getHeaderMap();
			for(Entry<String,Integer> e :map.entrySet()) {
				if(!e.getKey().trim().isEmpty()) {
					columns.put(e.getKey(), new ArrayList<>());
					
				}
			}
			List<CSVRecord> csvRecords = csvFileParser.getRecords();
			Map<String,Object> colMap = null;
			for(int i=0;i<csvRecords.size();i++) {
				CSVRecord rec = (CSVRecord)csvRecords.get(i);
				colMap = new LinkedHashMap<String,Object>();
				for(Entry<String,Integer> e :map.entrySet()) {
					if(!e.getKey().trim().isEmpty()) {
						columns.get(e.getKey()).add(record.get(e.getKey()));
						colMap.put(e.getKey(), record.get(e.getKey()));
						
					}
					
				}
				rows.add(colMap);
			}
			r.setColumns(columns);
			r.setRows(rows);
		}catch(Exception e) {
			
		}
		
		return r; 
	}
	
	@Override
	protected void writeInternal(Response s, HttpOutputMessage arg) {
		
	}
}
