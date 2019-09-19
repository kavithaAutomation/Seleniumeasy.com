package com.qa.application.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.application.pages.BootstrapAlertPage;


public class BootstrapAlertTest extends BaseTest {
	
	public BootstrapAlertPage BApage;
	
	@Test(dataProvider="Bootstrap Alert page data", priority=-1)
	public void pageTest(String title) {
		test = extent.createTest("Bootstrap Alert Page");
		
		childTest = test.createNode("validate Page by title");		
		log("validate Bootstrap Alert Page by page title");
		
		BApage = page.getClassInstance(BootstrapAlertPage.class);
		BApage.navigateToPage("Bootstrap Alerts", "Alerts & Modals");
		
		Assert.assertEquals(BApage.GetTitle(), title);
	}
	
	@Test(dataProvider="Bootstrap Alert page data")
	public void alertTest(String type, String bgcolor) throws InterruptedException {
		
		childTest = test.createNode("validate Auto Success Alert by color and time taken to close");		
		
//		Map<String, Object> resultAuto = BApage.clickAlert_autoClose(type, color, 6);
//		Map<String, Object> resultNormal = BApage.clickAlert_autoClose(type, color, 6);
		
		alertAssertions(BApage.clickAlert_autoClose(type, 7), bgcolor);
		alertAssertions(BApage.clickAlert_normalClose(type), bgcolor);
		
		

	}
	
	
	public void alertAssertions(Map<String, Object> result, String bgcolor) {
		
		Assert.assertTrue((Boolean)result.get("isAlert"));
		log(String.format("The alert appeared: %s", result.get("isAlert")));
		
		Assert.assertTrue((Boolean)result.get("isClosed"));
		log(String.format("The alert auto closed within 6s: %s", result.get("isClosed")));
		
		Assert.assertEquals(result.get("bgColor"), bgcolor);
		log(String.format("Expected color: %s and Actual color: %s", bgcolor, result.get("bgColor")));
		
	}
	
	
	
	
	@DataProvider(name="Bootstrap Alert page data")
	public Object[][] dataFor_BootstrapAlerts(Method mtd) throws Exception{
		
//		JsonDataManager jdm = JsonDataProvider.loadData();
//		Object[][] dataMap = jdm.getData(getClass(), mtd.getName()); 
//					
//		return dataMap;	
	
		if(mtd.getName().contains("alertTest"))
			return new Object[][] {
				{"success", "rgba(223, 240, 216, 1)"},
				{"warning", "rgba(252, 248, 227, 1)"},
				{"danger", "rgba(242, 222, 222, 1)"},
				{"info", "rgba(217, 237, 247, 1)"}
			};

		else
			return new Object[][] {
				{"Selenium Easy - Bootstrap Alerts Demo for Automation"}
			};
			
	}
	
	
	
	
}
