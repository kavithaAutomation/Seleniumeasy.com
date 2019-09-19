package com.qa.application.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BootstrapAlertPage extends BasePage {

	public BootstrapAlertPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageLoadWait(wait);
	}
	
	String autoBtn = "autoclosable-btn-TYPE";
	String autoAlert = "alert-autocloseable-TYPE";
	String normalBtn = "normal-btn-TYPE";
	String normalAlert = "alert-normal-TYPE";
	
	
	
	public Map<String, Object> clickAlert_autoClose(String type, int time) throws InterruptedException {
		
		By alert = By.className(autoAlert.replaceAll("TYPE", type));
		By button = By.id(autoBtn.replaceAll("TYPE", type));
		driver.findElement(button).click();
		
		explicitwait("bootstrap_alert", alert);
		boolean alertPresent = driver.findElement(alert).isDisplayed();

		Thread.sleep(time*1000);
		boolean isClosed = !driver.findElement(alert).isDisplayed();
		
		String color = driver.findElement(alert).getCssValue("background-color");
		System.out.println(alertPresent+ " - " + color+ " - " +isClosed);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bgColor", color);
		result.put("isAlert", alertPresent);
		result.put("isClosed", isClosed);
		return result;
	}
	
	public Map<String, Object> clickAlert_normalClose(String type) throws InterruptedException {
		
		By alert = By.className(normalAlert.replaceAll("TYPE", type));
		By button = By.id(normalBtn.replaceAll("TYPE", type));
		driver.findElement(button).click();
		
		explicitwait("bootstrap_alert", alert);
		boolean alertPresent = driver.findElement(alert).isDisplayed();

		WebElement closeBtn = driver.findElement(alert).findElement(By.tagName("Button"));
		boolean isCloseBtn = closeBtn.isDisplayed();
		
		closeBtn.click();		
		boolean isClosed = !driver.findElement(alert).isDisplayed();
		
		String color = driver.findElement(alert).getCssValue("background-color");
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bgColor", color);
		result.put("isAlert", alertPresent);
		result.put("isCloseBtn", isCloseBtn);
		result.put("isClosed", isClosed);
		return result;
	}
	
	
	
}
