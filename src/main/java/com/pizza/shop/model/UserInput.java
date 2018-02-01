package com.pizza.shop.model;

/***
 * Helper class for user's input
 * 
 * @author Satish
 *
 */
public class UserInput {

	public UserInput(String sourceFile, String destFile) {
		this.sourceFile = sourceFile;
		this.destFile = destFile;
	}

	private String sourceFile;
	
	private String destFile;

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public String getDestFile() {
		return destFile;
	}

	public void setDestFile(String destFile) {
		this.destFile = destFile;
	}
	
	
}
