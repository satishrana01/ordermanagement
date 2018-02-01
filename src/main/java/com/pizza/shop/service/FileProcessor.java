package com.pizza.shop.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.pizza.shop.model.InputFile;
import com.pizza.shop.model.Order;
import com.pizza.shop.model.OutputFile;
import com.pizza.shop.validate.Constant;

/***
 * Java file for handling file related operation
 * 
 * @author Satish
 *
 */
public class FileProcessor {

	
	private BufferedReader bufferReader;
	
	/***
	 * Read input file and create BufferReader object
	 * 
	 * @param sourceName
	 * @throws FileNotFoundException
	 */
	public void readInputFile(String sourceName) throws FileNotFoundException {
		InputFile fileReader = new InputFile();
		fileReader.setInputSource(sourceName);
		bufferReader = new BufferedReader(new FileReader(fileReader.getInputSource()));
	}
	
	/***
	 * Create directory & file enter by the user's if not exists
	 * 
	 * @param destPath
	 * @return File
	 * @throws IOException
	 */
	public File createOuputFile(String destPath) throws IOException {
		OutputFile fileWrite = new OutputFile();
		fileWrite.setOuputSource(destPath);
		File file = new File(fileWrite.getOuputSource());
		if(!file.isDirectory() && !file.exists())
			new File(file.getParent() != null ?file.getParent():"").mkdirs();
		file.delete();
		file.createNewFile();
		return file;
	}
	
	/***
	 * Constructing list of Order object
	 * 
	 * @return List<Order>
	 * @throws IOException
	 */
	public List<Order> constructOrderList() throws IOException {
		List<Order> orderList = new ArrayList<>();
		String line;
		Order order;
		line = bufferReader.readLine();
		if(validateDataHeader(line)){
		while ((line = bufferReader.readLine()) != null && ! line.isEmpty()) {
			order = getOrder(line.toUpperCase());
			orderList.add(order);
		}
		}else{
			System.out.println(Constant.INVALID_FILE_CONTENT.getConstantValue());
		}
		return orderList;	
	}
	
	/***
	 * Write sorted order list in readable format
	 * 
	 * @param orderList
	 * @param outputFileName
	 * @return boolean
	 * @throws IOException 
	 */
	public boolean writeOutputFile(List<Order> orderList, String destPath) throws IOException {
		File file = createOuputFile(destPath);
		
		boolean outPutFileStatus = true;
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		try {
			fileWriter = new FileWriter(file.getAbsolutePath());
			printWriter = new PrintWriter(fileWriter);
			printWriter.printf("%-20s %s%n","Order","Time");
			for (Order order : orderList) {
				printWriter.printf("%-20s %s%n", order.getName(),
						order.getTime());
			}

		} catch (IOException e) {
			outPutFileStatus = false;
			throw e;
		} finally {
			if (fileWriter != null)
				try {
					fileWriter.close();
				} catch (IOException e) {
					throw e;
				}
			if (printWriter != null)
				printWriter.close();
		}
		if(outPutFileStatus)
			System.out.println("path : "+file.getAbsolutePath());
		return outPutFileStatus;

	}
	
	private Order getOrder(String inputText) {

		Order order = new Order();
		String[] inputOrderText = inputText.split("\\s+");
		if (inputOrderText.length > 0) {
			order.setName(inputOrderText[0].trim());
			try {
				order.setTime(Integer.parseInt(inputOrderText[1].trim()));
			} catch (Exception e) {
				order.setTime(0);
				e.printStackTrace();
			}

		}
		return order;
	}
	
	private boolean validateDataHeader(String headerText) {
		
		boolean headerStatus = false;
		String[] inputOrderText = headerText.split("\\s+");
		if (inputOrderText.length == 2) {
			if(inputOrderText[0].trim().equalsIgnoreCase("Order")
					&& inputOrderText[1].trim().equalsIgnoreCase("time"))
				headerStatus = true;
		}
		return headerStatus;
	}
}
