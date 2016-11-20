package com.leverage.cptapi;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.oscarehr.util.MiscUtils;

public abstract class CPTService<T> implements IController<T>{
	
	protected Logger logger = MiscUtils.getLogger();
			
	protected String SERVICES_ADDRESS = "http://localhost:8080/CPTService/";
	
	protected JSONArray makeListRequest(String serviceString) throws MalformedURLException, IOException {
		List<String> parameters = new ArrayList<String>();
		return makeListRequest(serviceString, parameters);
	}
	
	protected JSONArray makeListRequest(String serviceString, List<String> parameters) throws IOException, MalformedURLException {
		String responseBody = request(serviceString, parameters);
		JSONArray arr = new JSONArray(responseBody);
		return arr;
	}

	protected JSONObject makeObjectRequest(String serviceString, List<String> parameters) throws IOException, MalformedURLException {
		String responseBody = request(serviceString, parameters);
		return new JSONObject(responseBody);
	}
	
	private String request(String serviceString, List<String> parameters) throws IOException, MalformedURLException {
		StringBuilder stringBuilder= new StringBuilder(SERVICES_ADDRESS);
		stringBuilder.append(serviceString);
		
		String address;
		
		for(String parameter:parameters) {
			logger.info("parameter: " + parameter);
		}
		
		if(parameters.size() > 0) {
			StringJoiner stringJoiner = new StringJoiner("/", stringBuilder.toString(), "");
			for(String parameter:parameters) {
				stringJoiner.add(parameter);
			}
			address = stringJoiner.toString();
		} else {
			address = stringBuilder.toString();
		}
		
		logger.info("address: " + address);
		URLConnection connection = new URL(address).openConnection();
		connection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
		InputStream response = connection.getInputStream();

		Scanner scanner = new Scanner(response);
		String responseBody = scanner.useDelimiter("\\A").next();
		scanner.close();
		
		logger.info("response: " + responseBody);
		return responseBody;
	}
}
