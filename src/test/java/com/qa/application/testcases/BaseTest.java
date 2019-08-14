package com.qa.application.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.application.pages.Page;
import com.qa.application.util.BrowserFactory;
import com.qa.application.util.ConfigDataProvider;
import com.qa.application.util.ExtentReportSetup;

public class BaseTest {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public ConfigDataProvider prop;
	public Logger Log = Logger.getLogger(getClass());
	
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest childTest;
	
	public Page page;
	
	@BeforeSuite
	public void SuiteSetup() {		
		prop = new ConfigDataProvider();
		extent = ExtentReportSetup.initExtentReport(extent, "Selenium Easy Demo", "Functional Test");
		Log.info("Report initiated and got config data ");
		
		driver = BrowserFactory.launchApplication(driver, prop.getBrowser(), prop.getAppURL());		
		wait = new WebDriverWait(driver, prop.getNumber("pageloadtime"));
		
		
		Log.info("Browser Ready");
	}
	
	@BeforeMethod
	public void MethodSetup() {
		page = new Page(driver, wait);
	}
	
	@AfterMethod
	public void methodTeatDown(ITestResult result) {		
		ExtentReportSetup.loadExtentReport(driver, result, childTest);	
	 }

	
	@AfterSuite
	public void suiteTearDown() {		
		driver.quit();	
		extent.flush();
		Log.info("Report created");
	}
	
	
	public void log(String message) {
		childTest.log(Status.INFO, message);
		Log.info(message);
	}

}
