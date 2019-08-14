package com.qa.application.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RadioButtonsDemoPage extends BasePage {

	public RadioButtonsDemoPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageLoadWait(wait);
	}
	
	// locators
	By radioBtns = By.cssSelector("label.radio-inline>input[name='optradio']");
	By getValue_Btn = By.id("buttoncheck");
	By radioResult = By.cssSelector("p.radiobutton");
	
	By genderGroup = By.cssSelector("input[name='gender']");
	By ageGroup = By.cssSelector("input[name='ageGroup']");
	By group_GetValueBtn = By.cssSelector("button[onclick^='getValues']");
	By group_result = By.cssSelector("p.groupradiobutton");


	public String clickRadioBtn(String value) {
		List<WebElement> rButtons = driver.findElements(radioBtns); 
		
		clickFromList(rButtons, value);
		scrollToView(getValue_Btn);
		driver.findElement(getValue_Btn).click();
		return readText(radioResult);	
	}
	
	public String clickGroupRadioBtns(String gender, String age) {
		List<WebElement> genderOptions = driver.findElements(genderGroup);
		List<WebElement> ageGroupOptions = driver.findElements(ageGroup);
		
		clickFromList(genderOptions, gender);
		clickFromList(ageGroupOptions, age);
		
		scrollToView(group_GetValueBtn);
		driver.findElement(group_GetValueBtn).click();
		return readText(group_result);
	}
	
	
	public void clickFromList(List<WebElement> list, String value) {
		for(WebElement ele : list) {
			if(ele.getAttribute("value").contains(value)) 
				ele.click();		
		}
	}
	
	
	
}
