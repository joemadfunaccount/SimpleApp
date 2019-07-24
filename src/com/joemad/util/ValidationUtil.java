package com.joemad.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
	private static final String emailRegex = "\\S*[@]\\S*[\\.]\\S+";

	public static boolean isEmailValid(String email){
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}
	
}
