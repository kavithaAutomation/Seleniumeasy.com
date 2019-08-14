package com.qa.application.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimpleFormDemoPage extends BasePage {

	public SimpleFormDemoPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageLoadWait(wait);
	}
	
	//locators - Simple Form	
	By simpleFormMenu = By.xpath("//li[@class='tree-branch']//a[contains(text(),'Simple Form Demo')]");
	By parentMenu = By.xpath("//li[@class='tree-branch']//a[contains(text(),'Simple Form Demo')]/ancestor::li[@class='tree-branch'][1]");
	
	
	By singleInputField = By.id("user-message");
	By singleInputButton = By.xpath("//form[@id='get-input']/button");
	By singleInputDisplay = By.id("display");
	
	By twoInputFieldA = By.id("sum1");
	By twoInputFieldB = By.id("sum2");
	By twoInputFieldButton = By.xpath("//form[@id='gettotal']/button");
	By twoInputFieldDisplay = By.id("displayvalue");
	
	//locators - check box
	By checkbox = By.xpath("//li[@class='tree-branch']//a[contains(text(),'Checkbox Demo')]");
	
	By singleCheckboxField = By.id("isAgeSelected");
	By singleCheckboxDisplay = By.xpath("//div[@id='txtAge'][contains(@style,'block')]");
	

	// Actions
	
	public String enterAndGetResult(String message) {
		inputDataToElement(singleInputField, message);
		driver.findElement(singleInputButton).click();
		return driver.findElement(singleInputDisplay).getText();
	}
	
	public String enterAndGetResult(String a, String b) {
		inputDataToElement(twoInputFieldA, a);
		inputDataToElement(twoInputFieldB, b);
		driver.findElement(twoInputFieldButton).click();
		return driver.findElement(twoInputFieldDisplay).getText();
	}
	
}
