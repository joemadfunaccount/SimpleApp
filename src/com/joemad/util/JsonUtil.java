package com.joemad.util;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static Gson getGson() {
		return gson;
	}
}
