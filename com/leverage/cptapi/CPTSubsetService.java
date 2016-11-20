package com.leverage.cptapi;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

import org.json.*;

import com.leverage.cptapi.model.Services;
import com.leverage.cptapi.model.Subset;

public class CPTSubsetService extends CPTService<Subset>{

	public Subset get(Integer id) throws MalformedURLException, IOException  {//TODO: fix this
		Map<String,String> parameters = new HashMap<String, String>();
		parameters.put(Subset.ID, Integer.toString(id));
		JSONArray arr = makeRequest(Services.SUBSET.value(), parameters);
		
		if(arr.length()<0) {
			throw new IllegalStateException("Array with fewer than zero results returned by service");
		} else if(arr.length() == 0 ) {
			return null;
		} else if(arr.length() == 1) {
			JSONObject jobj = arr.getJSONObject(0);
			Subset subset = new Subset(jobj.getInt(Subset.ID), jobj.getString(Subset.NAME));//fragile
			return subset;
		} else {
			
			throw new IllegalStateException("Get Service returnd more than one result");
		}
	}

	public List<Subset> list() throws MalformedURLException, IOException {
		JSONArray arr = makeListRequest(Services.SUBSET.value().concat("/"));
		
		List<Subset> subsets = new ArrayList<Subset>();
		
		for(int i=0; i<arr.length(); i++) {
			JSONObject jobj = arr.getJSONObject(i);
			Subset subset = new Subset(jobj.getInt(Subset.ID), jobj.getString(Subset.NAME));//fragile
			subsets.add(subset);
		}
		
		return subsets;
	}

	protected JSONArray makeListRequest(String serviceString) throws MalformedURLException, IOException {
		Map<String, String> parameters = new HashMap<String, String>();
		return makeRequest(serviceString, parameters);
	}
	
	protected JSONArray makeRequest(String serviceString, Map<String, String> parameters) throws IOException, MalformedURLException {
		StringBuilder stringBuilder= new StringBuilder(SERVICES_ADDRESS);
		stringBuilder.append(serviceString);
		
		String address;
		
		if(parameters.size() > 0) {
			stringBuilder.append("?");
			StringJoiner stringJoiner = new StringJoiner("&", stringBuilder.toString(), "");
			for(Map.Entry<String,String> entry:parameters.entrySet()) {
				stringJoiner.add(entry.getKey().concat("=").concat(entry.getValue()));
			}
			address = stringJoiner.toString();
		} else {
			address = stringBuilder.toString();
		}
		
		URLConnection connection = new URL(address).openConnection();
		connection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
		InputStream response = connection.getInputStream();

		Scanner scanner = new Scanner(response);
		String responseBody = scanner.useDelimiter("\\A").next();
		scanner.close();
		
		JSONArray arr = new JSONArray(responseBody);
		return arr;
	}
	
}
