package com.qa.application.util;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

	Properties prop;
	
	public ConfigDataProvider() {
		try {
			
			FileInputStream propFile = new FileInputStream("src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(propFile);
		} catch(Exception e) {
			System.out.println("Unable to read the config file : " + e.getMessage());
		}
	}
	
		// get the data from property file through key
		public String getData(String key) {
			return prop.getProperty(key);
		}
		
		public String getBrowser() {
			return prop.getProperty("browser");
		}
		
		public String getAppURL() {
			return prop.getProperty("URL");
		}
		
		public long getNumber(String key) {
			return Long.parseLong(prop.getProperty(key));
		}
}
