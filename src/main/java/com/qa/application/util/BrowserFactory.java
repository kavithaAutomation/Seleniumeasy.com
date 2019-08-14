package com.qa.application.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

	public static WebDriver launchApplication(WebDriver driver, String browserName, String appURL) {

		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("Test not support to browser: " + browserName);
		}
		
		driver.get(appURL);
	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();	
		
		return driver;
	}
}
