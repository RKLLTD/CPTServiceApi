package com.leverage.cptapi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.*;
import org.oscarehr.util.MiscUtils;

import com.leverage.cptapi.model.Service;
import com.leverage.cptapi.model.ServiceSection;
import com.leverage.cptapi.model.Services;

public class CPTServiceService extends CPTService<Service> implements IServiceService<Service>{

	private Logger logger = MiscUtils.getLogger();
	
	public Service get(Integer id) throws MalformedURLException, IOException  {
		List<String> parameters = new ArrayList<String>();
		parameters.add(Integer.toString(id));
		JSONObject obj = makeObjectRequest(Services.SERVICE.value().concat("/"), parameters);
		
		return ProcessSingleResult(obj);
	}
	
	public Service getByCode(int code) throws MalformedURLException, IOException {
		List<String> parameters = new ArrayList<String>();
		parameters.add(Integer.toString(code));
		JSONObject obj = makeObjectRequest(Services.SERVICE.value().concat("/").concat("byCode").concat("/"), parameters);
		
		return ProcessSingleResult(obj);
	}
	
	private Service ProcessSingleResult(JSONObject jobj) {
		Service service =  new Service(
				jobj.getInt(Service.ID), 
				jobj.getInt(Service.SUBSETID),
				jobj.getString(Service.SECTION),
				jobj.getString(Service.SUBSECTION),
				jobj.getInt(Service.DTKCONCEPTID),
				jobj.getInt(Service.CODE),
				jobj.getString(Service.DESCRIPTOR));//fragile
		return service;
	}
	
	public List<Service> list() throws MalformedURLException, IOException {
		JSONArray arr = makeListRequest(Services.SERVICE.value().concat("/"));
		
		List<Service> services = new ArrayList<Service>();
		
		for(int i=0; i<arr.length(); i++) {
			JSONObject jobj = arr.getJSONObject(i);
			Service service = new Service(
								jobj.getInt(Service.ID), 
								jobj.getInt(Service.SUBSETID),
								jobj.getString(Service.SECTION),
								jobj.getString(Service.SUBSECTION),
								jobj.getInt(Service.DTKCONCEPTID),
								jobj.getInt(Service.CODE),
								jobj.getString(Service.DESCRIPTOR));//fragile
			services.add(service);
		}
		
		return services;
	}

	@Override
	public List<Service> filterBySubset(Integer id) throws MalformedURLException, IOException {
		List<Service> services = list();
		Iterator<Service> iterator = services.iterator();
		
		while(iterator.hasNext()) {
			Service service = iterator.next();
			if(service.getSubsetId()!=id)iterator.remove();
		}
		
		return services;
	}
	
	@Override
	public List<Service> filterBySubsetAndSection(Integer subsetId, ServiceSection serviceSection) throws MalformedURLException, IOException {
		List<Service> services = filterBySubset(subsetId);
		
		Iterator<Service> iterator = services.iterator();
		
		while(iterator.hasNext()) {
			Service service = iterator.next();
			if(!service.getSection().equalsIgnoreCase(serviceSection.toString())) iterator.remove();
		}
		
		return services;
	}
}
