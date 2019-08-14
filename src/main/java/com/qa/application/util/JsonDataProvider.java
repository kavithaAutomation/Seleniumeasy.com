package com.qa.application.util;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class JsonDataProvider {
	
	
	
	public static JsonDataManager loadData() throws Exception {
		String filePath = "src/test/resources/testData/BasicExampleData.json";
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
		Gson gson = new Gson();
		JsonDataManager data = (JsonDataManager) gson.fromJson(reader, JsonDataManager.class);  
		return data;
	}
}
