package com.leverage.cptapi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.leverage.cptapi.model.ServiceSection;

public interface IServiceService<Service> extends IController<Service>{
	public List<Service> filterBySubset(Integer id) throws MalformedURLException, IOException;
	public List<Service> filterBySubsetAndSection(Integer subsetId, ServiceSection serviceSection) throws MalformedURLException, IOException ; 
}