package com.qa.application.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JSalertPage extends BasePage {

	public JSalertPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageLoadWait(wait);
	}
	
	By alertBox_btn = By.xpath("//button[@class='btn btn-default']");
	
	By confirmBox_btn = By.xpath("//button[contains(@class,'btn-lg') and contains(text(),'Click me!')]");
	By confirmBox_result = By.id("confirm-demo");
	
	By alertInput_btn = By.xpath("//button[contains(text(),'Click for Prompt Box')]");
	By alertInput_result = By.id("prompt-demo");
	
	
	
	public String clickAlertBox() {
		return clickForAlert(alertBox_btn, "accept", null);
	}	
	
	public Map<String, String> clickConfirmBox() {
		String alertMessage = clickForAlert(confirmBox_btn, "accept", null);				
		String acceptedMessage = readText(confirmBox_result);
		
		clickForAlert(confirmBox_btn, "cancel", null);
		String cancelMessage = readText(confirmBox_result);

		Map<String, String> confirmBoxResult = new HashMap<String, String>();
		confirmBoxResult.put("alertMessage", alertMessage);
		confirmBoxResult.put("acceptedMessage", acceptedMessage);
		confirmBoxResult.put("cancelMessage", cancelMessage);
		
		return confirmBoxResult;
	}
	
	public Map<String, String> clickPromptBox(String input) {
		String alertMessage = clickForAlert(alertInput_btn, "accept", input);
		String acceptedMessage = readText(alertInput_result);
		
		clickForAlert(alertInput_btn, "cancel", input);
		String cancelMessage = readText(alertInput_result);
		
		Map<String, String> confirmBoxResult = new HashMap<String, String>();
		confirmBoxResult.put("alertMessage", alertMessage);
		confirmBoxResult.put("acceptedMessage", acceptedMessage);
		confirmBoxResult.put("cancelMessage", cancelMessage);
		
		return confirmBoxResult;
	}
	
	// click the button to see the alert window and return the alert window content/text
	public String clickForAlert(By by, String action, String promptInput) {
		scrollToView(by);
		driver.findElement(by).click();
		String mainWindow = driver.getWindowHandle();
		
		explicitwait("alert", null);
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		
		if(promptInput!=null)
			alert.sendKeys(promptInput);
		
		if(action.contains("accept"))
			alert.accept();
		else if(action.contains("cancel"))
			alert.dismiss();
		
		driver.switchTo().window(mainWindow);
		return alertMessage;
	}

}
