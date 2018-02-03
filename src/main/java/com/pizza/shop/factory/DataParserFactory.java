package com.pizza.shop.factory;

import com.pizza.shop.service.DataParser;
import com.pizza.shop.service.impl.DocFileParser;
import com.pizza.shop.service.impl.PdfFileParser;
import com.pizza.shop.service.impl.TextFileParser;

/***
 * Keep Factory class as singleton.
 * Based on the input parameter,different subclass is created and returned.
 * getParserObject is the factory method.  
 * 
 * @author Satish
 *
 */
public class DataParserFactory {

	private static DataParserFactory instance = null;
	
	private DataParserFactory(){}
	
	public static synchronized DataParserFactory getInstance(){
		if(instance == null){
			instance = new DataParserFactory();
		}
		return instance;
	}
	
	/***
	 * Factory method create and returned different subclass based on input parameter
	 *  
	 * @param parserType
	 * @return DataParser
	 */
	public DataParser getParserObject(String parserType){
		
		if("TXT".equalsIgnoreCase(parserType)){
			return new TextFileParser();
		}
		else if("PDF".equalsIgnoreCase(parserType)){
			return new PdfFileParser();
		}
		else if("DOC".equalsIgnoreCase(parserType) || "DOCX".equalsIgnoreCase(parserType)){
			return new DocFileParser();
		}
		return null;
	}
}
