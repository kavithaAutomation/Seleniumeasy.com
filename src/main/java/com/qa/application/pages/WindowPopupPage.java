package com.qa.application.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowPopupPage extends BasePage {

	public WindowPopupPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageLoadWait(wait);
	}

	By single_twiter = By.xpath("//a[contains(text(),'Follow On Twitter')]");
	By single_fb = By.xpath("//a[contains(text(),'Like us On Facebook')]");
	
	By multi_twFb = By.xpath("//a[contains(text(),'Follow Twitter & Facebook')]");
	By follow = By.id("followall");
	
	@SuppressWarnings("rawtypes")
	public Map<String, List> clickSingleWindow_popup() {
		WebElement fbBtn = driver.findElement(single_fb);
		WebElement twitterBtn = driver.findElement(single_twiter);
		Map<String, List> result = new HashMap<String, List>();
		
//		String btnName = fbBtn.getText().substring(fbBtn.getText().lastIndexOf(" "));
//		System.out.println("btnName : "+btnName);
			
		fbBtn.click();
		getChildWindowTitle(result, "FB");
		
		twitterBtn.click();
		getChildWindowTitle(result, "Twitter");
		
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public Map<String, List> clickMultiWindow_popup() {
		WebElement fbTwitterBtn = driver.findElement(multi_twFb);
		WebElement followBtn = driver.findElement(follow);
		Map<String, List> result = new HashMap<String, List>();
		
		fbTwitterBtn.click();
		getChildWindowTitle(result, "FBtwitter");
		
		followBtn.click();
		getChildWindowTitle(result, "FollowAll");
		
		return result;
	}
	
	
	@SuppressWarnings("rawtypes")
	public void getChildWindowTitle(Map<String, List> result, String key) {
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> iterator = allWindows.iterator();

		List<String> titles = new ArrayList<String>();
		
		while(iterator.hasNext()) {
			String childWindow = iterator.next();
			if(!mainWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);				
				titles.add(driver.getTitle());
			
				driver.close();
			}
		}
		result.put(key, titles);
		
		driver.switchTo().window(mainWindow);

	}
	
	
}
