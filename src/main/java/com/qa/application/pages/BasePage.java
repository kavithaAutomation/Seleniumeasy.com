package com.qa.application.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends Page {
		
	public BasePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	
	// common functions for all the page classes
	
	// click element through javascript
	public void clickMenu(By menu, By parent) {
		if(!isDisplayed(menu)) 
			driver.findElement(parent).click();
		driver.findElement(menu).click();
	}
	
	// navigate to page through main menu
	public void navigateToPage(String menuName, String parentName) {
		String htmlPageMenu = "//li[@class='tree-branch']//a[contains(text(),'%menuName%')]";
		String htmlPageMenu_parent = "//li[@class='tree-branch']//a[contains(text(),'%menuName%')]/ancestor::li[@class='tree-branch'][1]";
		By menu = By.xpath(htmlPageMenu.replaceAll("%menuName%", menuName));
		By parent = By.xpath(htmlPageMenu_parent.replaceAll("%menuName%", parentName));
		By menuTitle = By.cssSelector("#treemenu>li");
		//By menuExpanded = By.cssSelector("i.glyphicon-chevron-down");
		
		scrollToView(menuTitle);		
		clickMenu(menu, parent);
	}
	
	// enter/click web element
	public void inputDataToElement(By by, String dataToInput)  {
		
		WebElement ele = driver.findElement(by);
		String tagName = ele.getTagName();
		
		if (tagName.equals("select")) {

			scrollToView(by);
			Select dropdown = new Select(ele);
			dropdown.selectByVisibleText(dataToInput);

		} else if (tagName.equals("textarea")) {

			scrollToView(by);
			ele.clear();
			ele.sendKeys(new CharSequence[] { dataToInput });
			ele.sendKeys(new CharSequence[] { Keys.TAB });
			
		} else if (tagName.equals("input")) {
			String inputType = ele.getAttribute("type");
			scrollToView(by);
			
			if ((inputType.equals("text")) || (inputType.equals("email")) || (inputType.equals("password"))) {
				ele.clear();
				ele.sendKeys(new CharSequence[] { dataToInput });
				ele.sendKeys(new CharSequence[] { Keys.TAB });
			}

			else if (inputType.equals("checkbox")) {
				if ((!ele.isSelected()) && (dataToInput.equals("1"))) {
					ele.click();

				} else if ((ele.isSelected()) && (dataToInput.equals("0"))) {
					ele.click();
				}
			}
			
			else if (inputType.equals("radio")) {
				if ((!ele.isSelected()) && (dataToInput.equals("1"))) {
					ele.click();
				}
			}
			
			
		}
	}
	

	
	// get the text inside the element
	public String readText(By by) {
		return driver.findElement(by).getText();
	}
	
	// get page title
	public String GetTitle() {
		return driver.getTitle();
	}
	
	// click on the button
	public void btnClick(String cssSelector) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(cssSelector).focus();");
		js.executeScript("document.querySelector(cssSelector).click();");
	}
	
	// check the element visibility
	public boolean isDisplayed(By by) {
		return driver.findElement(by).isDisplayed();
	}
	
	// scroll to element
	public void scrollToView(By by) {
		JavascriptExecutor jsDriver = (JavascriptExecutor)driver;
		jsDriver.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
	}
	
	//Close the popup if exists
    public void handlePopup (By by) throws InterruptedException {
        List<WebElement> popup = driver.findElements(by);
        if(!popup.isEmpty()){
            popup.get(0).click();
            Thread.sleep(200);
        }
    }
    
    // webDriverWait for alert/element/...
    public void explicitwait(String condition, By by) {
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	if(condition.contains("bootstrap_alert")) {
    		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    	}else if(condition.contains("button")) {
    		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
    	}else if(condition.contains("alert")) {
    		wait.until(ExpectedConditions.alertIsPresent());
    	}
    }
	
	// pageloadTimeOut
	public void PageLoadWait(WebDriverWait wait) {	
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            wait.until(expectation);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
	

		
		
		

}
