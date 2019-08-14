package com.qa.application.util;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportSetup {
	
		//initiate extend report v4
		public static ExtentReports initExtentReport(ExtentReports extent, String reportTitle, String reportName) {
			 
			  ExtentHtmlReporter htmlReporter;htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/ApplicationReport.html");
			  htmlReporter.config().setDocumentTitle(reportTitle); // Title
			  htmlReporter.config().setReportName(reportName); // Report Name
			  htmlReporter.config().setTheme(Theme.DARK);
			  
			  extent = new ExtentReports();
			  extent.attachReporter(htmlReporter);
			  
			  extent.setSystemInfo("Host name", "localhost");
			  extent.setSystemInfo("Environemnt", "QA");
			  extent.setSystemInfo("user", "Kavitha");
			  return extent;
		}
		
		//load content to Extent report
		public static void loadExtentReport(WebDriver driver, ITestResult result, ExtentTest childTest) {
			if (result.getStatus() == ITestResult.FAILURE) {
				
				childTest.log(Status.FAIL, MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
				childTest.log(Status.FAIL, result.getThrowable());

				
				// add screenshot
				String screenshotPath = UtilFunctions.getScreenshot(driver, result.getName());
				try {
					childTest.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
					childTest.addScreenCaptureFromPath(screenshotPath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			   
				   
			} else if (result.getStatus() == ITestResult.SKIP) {
				childTest.skip(MarkupHelper.createLabel(result.getName()+" Test case Skipped", ExtentColor.YELLOW));
				childTest.skip(result.getThrowable());
				  
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				childTest.log(Status.PASS, MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
			  }
		}

}
