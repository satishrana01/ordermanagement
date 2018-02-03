package com.pizza.shop;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.pizza.shop.factory.DataParserFactory;
import com.pizza.shop.model.Order;
import com.pizza.shop.model.SortByOrderName;
import com.pizza.shop.model.UserInput;
import com.pizza.shop.service.DataParser;
import com.pizza.shop.validate.Constant;
import com.pizza.shop.validate.Utility;
import com.pizza.shop.validate.ValidateInput;
/***
 * Main java file to start execution of the program
 * 
 * @author Satish
 *
 */
public class RunMain {

	public static void main(String[] args) {
		
		boolean status = false;
		// Scanner class has been used for self explanatory to end user for input parameter 
		Scanner sc=new Scanner(System.in);  
		System.out.println(Constant.ENTER_FULL_SOURCE_FILE.getConstantValue());  
		String sourceFile = sc.next();  
		System.out.println(Constant.ENTER_FULL_DEST_FILE.getConstantValue());  
		String destFile=sc.next();
		
		UserInput userInput = new UserInput(sourceFile,destFile);
		sc.close();
		// Perform validation before starting actual processing
		ValidateInput valiationObj = new ValidateInput();
		String outputMessage = valiationObj.validateInput(userInput);
		
		if (outputMessage.isEmpty()) {// check the output message string, don't process if found any error
			try {
				
				String extenstion = Utility.getExtension(userInput.getSourceFile());
				
				/***
				 *  call factory class to return object of subclass
				 */
				DataParser dataParserObject = DataParserFactory.getInstance().getParserObject(extenstion);
				dataParserObject.readInputObject(userInput.getSourceFile());
				List<Object> orderList = dataParserObject.constructObjectList();// List of object
				
				if(!orderList.isEmpty()) {
					// Write the output File
					
					/***
					 * implemented the strategy pattern here where user's has choice to decide at run time
					 * whether to sort the object by name or by time by passing appropriate Comparator class object.
					 * considering default as order by name 
					 */
					
					status = dataParserObject.writeOutputFile(orderList, userInput.getDestFile(),new SortByOrderName());
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				status = false;
			}
			if (status)
				System.out.println(Constant.SUCCESS.getConstantValue());
			else
				System.out.println(Constant.FAIL.getConstantValue());
		} else
			System.out.println(outputMessage);
		

	}
	 
}
