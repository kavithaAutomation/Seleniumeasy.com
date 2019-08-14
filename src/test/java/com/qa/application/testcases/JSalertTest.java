package com.qa.application.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.application.pages.JSalertPage;
import com.qa.application.util.JsonDataManager;
import com.qa.application.util.JsonDataProvider;

public class JSalertTest extends BaseTest {

	public JSalertPage JSpage;
	
	@Test(dataProvider="JS Alert page data", priority=-1)
	public void pageTest(String title) {
		test = extent.createTest("Javascript Alert Page");
		
		childTest = test.createNode("validate page");		
		log("validate JS Alert page by page title");
		
		JSpage = page.getClassInstance(JSalertPage.class);
		JSpage.navigateToPage("Javascript Alerts", "Alerts & Modals");
        
		Assert.assertEquals(JSpage.GetTitle(), title);	
	}
	
	@Test(dataProvider="JS Alert page data")
	public void alertBoxTest(String message) {
		childTest = test.createNode("Validate popup window");
		
		String result = JSpage.clickAlertBox();
		log(String.format("Alert appeared with message: '%s'", result));
		Assert.assertTrue(result.contains(message));
	}
	
	@Test(dataProvider="JS Alert page data")
	public void acceptReject_AlertTest(String message) {
		childTest = test.createNode("validate buttons on popup");
		Map<String, String> result = JSpage.clickConfirmBox();
		
		SoftAssert SAssert = new SoftAssert();
		log(String.format("Alert appeared with message: '%s'", result.get("alertMessage")));
		SAssert.assertTrue(result.get("alertMessage").contains(message));
		
		String okResult = result.get("acceptedMessage");
		log(String.format("The message after clicked OK btn: '%s'", okResult));
		SAssert.assertTrue(okResult.substring(okResult.lastIndexOf(" ")).contains("OK"));
		
		String cancelResult = result.get("cancelMessage");
		log(String.format("The message after clicked Cancel btn: '%s'", cancelResult));
		SAssert.assertTrue(cancelResult.substring(cancelResult.lastIndexOf(" ")).contains("Cancel"));
		
		SAssert.assertAll();
	}
	
	@Test(dataProvider="JS Alert page data")
	public void promptBoxTest(String message, String input) {
		childTest = test.createNode("validate prompt box with input '"+input+"'");
		Map<String, String> result = JSpage.clickPromptBox(input);
		
		SoftAssert SAssert = new SoftAssert();
		log(String.format("Alert appeared with message: '%s'", result.get("alertMessage")));
		SAssert.assertTrue(result.get("alertMessage").contains(message));
		
		String okResult = result.get("acceptedMessage");
		log(String.format("The message after clicked OK btn: '%s'", okResult));

		SAssert.assertTrue(okResult.substring(okResult.indexOf("'")+1, okResult.lastIndexOf("'")).contains(input));
		
		String cancelResult = result.get("cancelMessage");
		log(String.format("The message after clicked Cancel btn: '%s'", cancelResult));
		SAssert.assertEquals(cancelResult, okResult);
		
		SAssert.assertAll();
	}
	
	
	@DataProvider(name="JS Alert page data")
	public Object[][] resultFor_RadioBtnClick(Method mtd) throws Exception{
		JsonDataManager jdm = JsonDataProvider.loadData();
		Object[][] dataMap = jdm.getData(getClass(), mtd.getName()); 
					
		return dataMap;	
	}
	
	
	
	
	
}
