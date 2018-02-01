package com.pizza.shop;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.pizza.shop.model.Order;
import com.pizza.shop.model.UserInput;
import com.pizza.shop.service.FileProcessor;
import com.pizza.shop.validate.Constant;
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
				// Read the input file
				FileProcessor fileProcessor = new FileProcessor();
				fileProcessor.readInputFile(userInput.getSourceFile());

				// get list of order object
				List<Order> orderList = fileProcessor.constructOrderList();
				
				if(!orderList.isEmpty()) {
					//Sort it in lexicographical order
					Collections.sort(orderList);
					// Write the output File
					// Return the status whether file written successfully or not.
					status = fileProcessor.writeOutputFile(orderList, userInput.getDestFile());
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
