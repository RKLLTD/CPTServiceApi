package com.leverage.cptapi;

import java.io.IOException;
import java.net.MalformedURLException;

import java.util.List;


public interface IController<T> {

	T get(Integer id) throws Exception;

	List<T> list() throws MalformedURLException, IOException;

}