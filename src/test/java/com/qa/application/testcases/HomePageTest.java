package com.qa.application.testcases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.application.pages.HomePage;
import com.qa.application.util.JsonDataManager;
import com.qa.application.util.JsonDataProvider;



public class HomePageTest extends BaseTest{
	
	@Test(dataProvider="Page Title")
	public void homepageTest(String title) {
		test = extent.createTest("Home Page");
		test.assignCategory("Home page Test");
		
		childTest = test.createNode("validate Homepage by page title");		
		log("Validating Homepage with title");
		
		Assert.assertEquals(page.getClassInstance(HomePage.class).pageTitle(), title);
	}
	
	@DataProvider(name = "Page Title")
	public Object[][] getFromJson(Method mtd) throws Exception {
						
		JsonDataManager jdm = JsonDataProvider.loadData();
		Object[][] dataMap = jdm.getData(getClass(), mtd.getName()); 
					
		return dataMap;		
	}
	
	
}
