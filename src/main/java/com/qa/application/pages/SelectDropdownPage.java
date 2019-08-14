package com.qa.application.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectDropdownPage extends BasePage {

	public SelectDropdownPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageLoadWait(wait);
	}
	
	By daySelect = By.id("select-demo");
	By daySelect_selected = By.className("selected-value");
	
	By multiSelect = By.id("multi-select");
	By firstSelected_btn = By.id("printMe");
	By allSelected_btn = By.id("printAll");	
	By multiSelect_selected = By.className("getall-selected");
	
	public String selectFrom_dropDown(String selection) {
		inputDataToElement(daySelect, selection);
		return readText(daySelect_selected);
	}
	
	public String selectFrom_multiDropDown(String val1, String val2, String btnName) {
		inputDataToElement(multiSelect, val1); 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inputDataToElement(multiSelect, val2);
		
		if(btnName.contains("All"))
			driver.findElement(allSelected_btn).click();
		else 
			driver.findElement(firstSelected_btn).click();
		
		return readText(multiSelect_selected);
	}

}
