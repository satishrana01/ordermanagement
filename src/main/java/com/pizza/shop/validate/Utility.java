package com.pizza.shop.validate;

/***
 * Utility class for handling utilities functions
 * 
 * @author Satish
 *
 */
public class Utility {

	public static String getExtension(String fileName) {

		char ch;
		int len;
		if (fileName == null || (len = fileName.length()) == 0
				|| (ch = fileName.charAt(len - 1)) == '/' || ch == '\\'
				|| ch == '.')
			return "";
		int dotInd = fileName.lastIndexOf('.'), sepInd = Math.max(
				fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
		if (dotInd <= sepInd)
			return "";
		else
			return fileName.substring(dotInd + 1).toLowerCase();
	}
}
