package com.pizza.shop.validate;

/***
 * Utility class for handling utilities functions
 * 
 * @author Satish
 *
 */
public class Utility {

	public static String getExtension(String fileName){
		
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i >= 0) {
		    extension = fileName.substring(i+1);
		}
		return extension;
	}
}
