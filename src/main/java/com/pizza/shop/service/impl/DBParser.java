package com.pizza.shop.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import com.pizza.shop.service.DataParser;
/***
 * Implementation class for processing the data from DB
 * 
 * @author Satish
 *
 */
public class DBParser implements DataParser {

	@Override
	public void readInputObject(String sourceName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public File createOuputFile(String destPath) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> constructObjectList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeOutputFile(List<Object> objectList,
			String destinationFilePath, Comparator<? super Object> c)
			throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	

	
}
