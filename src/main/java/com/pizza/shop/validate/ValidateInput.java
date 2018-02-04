package com.pizza.shop.validate;

import java.io.File;

import com.pizza.shop.model.UserInput;


/***
 * Helper class to perform all the validation
 * 
 * @author Satish
 *
 */
public class ValidateInput {

	public String validateInput(UserInput userInput) {

		String message = "";
		if (null == userInput.getSourceFile() && userInput.getSourceFile().isEmpty())
			message = Constant.MISSING_INPUT.getConstantValue();
		else if (null == userInput.getDestFile() && userInput.getDestFile().isEmpty())
			message = Constant.MISSING_OUTPUT.getConstantValue();
		else if (!validatePath(userInput.getSourceFile()))
			message = Constant.INVALID_SOURCEPATH.getConstantValue();
		else if (Utility.getExtension(userInput.getDestFile()).isEmpty())
			message = Constant.MISSING_OUTPUT_EXT.getConstantValue();
		return message;

	}

	private boolean validatePath(String path) {

		boolean pathExists = false;

		File file = new File(path);
		if (file.exists()) {
			pathExists = true;
		}
		return pathExists;

	}
}
