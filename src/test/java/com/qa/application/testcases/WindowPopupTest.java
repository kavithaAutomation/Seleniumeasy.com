package com.qa.application.testcases;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.application.pages.WindowPopupPage;

public class WindowPopupTest extends BaseTest {
	
	public WindowPopupPage WPpage;
	
	@Test(dataProvider="Window Alert page data", priority = -1)
	public void pageTest(String title) {
		test = extent.createTest("Window Popup Page");
		
		childTest = test.createNode("validate page");		
		log("validate window Popup page by page title");
		
		WPpage = page.getClassInstance(WindowPopupPage.class);
		WPpage.navigateToPage("Window Popup Modal", "Alerts & Modals");
        
		Assert.assertEquals(WPpage.GetTitle(), title);	
	}
	
	
	@SuppressWarnings("rawtypes")
	@Test(dataProvider="Window Alert page data")
	public void singleWindowPopup_Test(String twitter, String fb) {
		childTest = test.createNode("validate single window popup");
		
		Map<String, List> result = WPpage.clickSingleWindow_popup();
		SoftAssert SAssert = new SoftAssert();
		System.out.println(result.get("FB").get(0));
		log(String.format("Window poped with title: '%s'", result.get("FB").get(0)));
		SAssert.assertEquals(result.get("FB").get(0), fb);
		log(String.format("Window poped with title: '%s'", result.get("Twitter").get(0)));
		SAssert.assertEquals(result.get("Twitter").get(0), twitter);
		
		SAssert.assertAll();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void multiWindowPopup_Test() {
		childTest = test.createNode("validate multiple window popups");
		
		List<String> fbTwArray = new ArrayList(Arrays.asList("Selenium Easy - Home | Facebook", "Selenium Easy (@seleniumeasy) on Twitter"));
		List<String> followArray = new ArrayList(Arrays.asList("Sign in – Google accounts", "Selenium Easy - Home | Facebook", "Selenium Easy (@seleniumeasy) on Twitter"));
		
		Map<String, List> result = WPpage.clickMultiWindow_popup();
		SoftAssert SAssert = new SoftAssert();

		log(String.format("Windows poped with title: '%s'", result.get("FBtwitter")));	
		result.get("FBtwitter").removeAll(fbTwArray);
		SAssert.assertTrue(result.get("FBtwitter").size()==0);
		
		log(String.format("Windows poped with title: '%s'", result.get("FollowAll")));
		result.get("FollowAll").removeAll(followArray);	
		SAssert.assertTrue(result.get("FollowAll").size()==0);
		
		SAssert.assertAll();
	}
	
	
	@DataProvider(name="Window Alert page data")
	public Object[][] titlesFor_windowPopup(Method mtd){
		if(mtd.getName().contains("singleWindowPopup_Test"))
			return new Object[][] {
				{"Selenium Easy (@seleniumeasy) on Twitter", "Selenium Easy - Home | Facebook"}
			};
//		else if(mtd.getName().contains("multiWindowPopup_Test"))
//			return new Object[][] {
//				{"Sign in – Google accounts", "Selenium Easy (@seleniumeasy) on Twitter"}
//				//{"Sign in – Google accounts", "Selenium Easy - Home | Facebook", "Selenium Easy (@seleniumeasy) on Twitter"}
//			};
		else
			return new Object[][] {
				{"Selenium Easy - Window Popup Modal Demo"}
			};
	}
	
	
	
	

}
