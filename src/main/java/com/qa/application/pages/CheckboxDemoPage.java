package com.qa.application.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckboxDemoPage extends BasePage {

	public CheckboxDemoPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageLoadWait(wait);
	}
	
	// locators
	By singleCheckbox = By.id("isAgeSelected");
	By singleCheckbox_success = By.id("txtAge");
	
	By multipleCheckbox = By.cssSelector("input[type='checkbox'].cb1-element");
	By multipleCheckbox_btn = By.id("check1");
	
	
	public String clickSingleCheckbox() {
		inputDataToElement(singleCheckbox, "1");
		return readText(singleCheckbox_success);
	}
	

	public Map<String, String> validateCheckall_uncheckOne() {
		WebElement btn = driver.findElement(multipleCheckbox_btn);
		String btnValue = btn.getAttribute("value");
		List<WebElement> multipleBoxes = (List<WebElement>) driver.findElements(multipleCheckbox);
		boolean allChecked = true;
		
		// make button text = check all
		if(btnValue.contains("Uncheck"))
			btn.click();	
		// press the btn
		if(btnValue.contains("Check All"))
			btn.click();
		// check all the boxes are selected
		for(WebElement ele:multipleBoxes) {
			if(!ele.isSelected()) allChecked=false;
		}
		Map<String, String> checkAllResult = new HashMap<String, String>();
		
		checkAllResult.put("btnValue", btn.getAttribute("value"));
		checkAllResult.put("allChecked", String.valueOf(allChecked));
		
		if(uncheck(multipleBoxes))
			checkAllResult.put("oneChecked_btnValue", btn.getAttribute("value"));
		else checkAllResult.put("oneChecked_btnValue", "uncheck not successful");
		
		return checkAllResult;
	}
	
	public boolean uncheck(List<WebElement> multipleBoxes) {
		// uncheck one box
		for(WebElement el:multipleBoxes) {
			if(el.isSelected()) { 
				el.click();
				return true;
			}
		}
		return false;
	}
	

}
