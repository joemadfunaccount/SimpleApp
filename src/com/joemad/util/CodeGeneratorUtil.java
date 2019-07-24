package com.joemad.util;

public class CodeGeneratorUtil {
	public static String generateRandomString() {
		StringBuilder randomStr = new StringBuilder();
		for(int counter=0; counter<2;counter++) {
			randomStr.append(Math.random()*1000);
		}
		String generatedCode = randomStr.toString().replace(".", "");
		return generatedCode;
	}
}
