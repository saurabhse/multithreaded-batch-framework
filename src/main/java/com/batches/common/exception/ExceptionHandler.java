package com.batches.common.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class ExceptionHandler implements ResponseErrorHandler{

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return !response.getStatusCode().getReasonPhrase().equalsIgnoreCase(HttpStatus.OK.getReasonPhrase());
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		throw new MyException("error");
		
	}

}
