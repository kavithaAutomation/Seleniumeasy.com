package com.qa.application.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageLoadWait(wait);
	}
	
	//locators

	
	
	//actions
	
	public String pageTitle() {
		return driver.getTitle();
	}
	
	

}
