package com.pizza.shop.validate;

/***
 * enum class to define constants
 * 
 * @author Satish
 *
 */
public enum Constant {

	FILE_TXT_EXT(".txt"),

	DATE_TIME_FORMAT("yyyyMMddHHmmss"),
	
	MISSING_ARGS("Src file location, destination file parameters are missing"),
	
	MISSING_INPUT("Input file name is missing"),
	
	MISSING_OUTPUT("Outfile file name is missing"),
	
	EXTENSION("Only .txt file is permitted"),
	
	INVALID_SOURCEPATH("Input path is invalid"),
	
	INVALID_DESTEPATH("Output path is invalid"),
	
	ENTER_FULL_SOURCE_FILE("Enter source file path: "),
	
	ENTER_FULL_DEST_FILE("Enter destincation path:"),
	
	SUCCESS("Output file created successfully"),
	
	FAIL("Something went wrong ! Please try again"),
	
	INVALID_FILE_CONTENT("File content is invalid");
	
	private String constantValue;
	 
	Constant(String constantValue) {
        this.constantValue = constantValue;
    }

	public String getConstantValue() {
		return constantValue;
	}
 
   
	}
