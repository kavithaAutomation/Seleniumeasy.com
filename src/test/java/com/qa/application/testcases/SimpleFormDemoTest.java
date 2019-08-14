package com.qa.application.testcases;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.application.pages.SimpleFormDemoPage;
import com.qa.application.util.JsonDataManager;
import com.qa.application.util.JsonDataProvider;

public class SimpleFormDemoTest extends BaseTest{

	public SimpleFormDemoPage SFpage;
		
	@Test(dataProvider = "getFromJson", priority=-1)
	public void pageTest(String title) throws Exception {
		test = extent.createTest("Simple Form Page");
		//test.assignCategory("Home page Test");
		System.out.println("Title "+title);
		childTest = test.createNode("validate Page by title");		
		log("validate Simple Form Page by page title");
		
		SFpage = page.getClassInstance(SimpleFormDemoPage.class);
		SFpage.navigateToPage("Simple Form Demo", "Input Forms");
			        
		Assert.assertEquals(SFpage.GetTitle(), title);	
	}
	
	
	@Test(dataProvider = "getFromJson")
	public void singleInputTest(String message) {
		//test.assignCategory("Home page Test");
		
		childTest = test.createNode("validate single input field");		
		log(String.format("validate single input field by ' %s '", message));
		
		Assert.assertEquals(SFpage.enterAndGetResult(message), message);
		
	}
	
	@Test(dataProvider = "getFromJson")
	public void twoInputTest(String a, String b, String expected) {
		//test.assignCategory("Home page Test");
		
		childTest = test.createNode("validate two input field");		
		log(String.format("validate two input fields by ' %s ' and ' %s' ", a,b));
		
		//String expected = String.valueOf((Integer.parseInt(a)+Integer.parseInt(b)));
		Assert.assertEquals(SFpage.enterAndGetResult(a,b), expected);
		
	}
	
	
	@DataProvider
	public Object[][] getFromJson(Method mtd) throws Exception {
						
		JsonDataManager jdm = JsonDataProvider.loadData();
		Object[][] dataMap = jdm.getData(getClass(), mtd.getName()); 
					
		return dataMap;		
	}
	
	
	
	
}
