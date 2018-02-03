package com.pizza.shop.service;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/***
 * Define basic operation of parsing data either from file system or DB
 * 
 * @author Satish
 *
 */
public interface DataParser {

	
	/***
	 * Read input data either from file or DB
	 * 
	 * @param sourceName
	 * @throws Exception
	 */
	public void readInputObject(String sourceName) throws Exception;
	
	/***
	 * Create output file 
	 * 
	 * @param destPath
	 * @return File
	 * @throws IOException
	 */
	public File createOuputFile(String destPath) throws IOException;
	
	/***
	 * Constructing list of object
	 * 
	 * @return List<Object>
	 * @throws Exception
	 */
	public List<Object> constructObjectList() throws Exception;
	
	/***
	 * Write object list into file
	 * 
	 * @param objectList
	 * @param destinationPath
	 * @return boolean
	 * @throws IOException 
	 */
	public boolean writeOutputFile(List<Object> objectList, String destinationFilePath,Comparator<? super Object> c) throws IOException;
	
}
