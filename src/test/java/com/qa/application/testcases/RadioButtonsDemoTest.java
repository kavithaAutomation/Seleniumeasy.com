package com.qa.application.testcases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.application.pages.RadioButtonsDemoPage;
import com.qa.application.util.JsonDataManager;
import com.qa.application.util.JsonDataProvider;

public class RadioButtonsDemoTest extends BaseTest {
	
	public RadioButtonsDemoPage RBpage;
	
	@Test(dataProvider="Radio Button page data", priority=-1)
	public void pageTest(String title) {
		test = extent.createTest("Radio Button Demo Page");
		
		childTest = test.createNode("validate page");		
		log("validate radiobtn demo page by page title");
		
		RBpage = page.getClassInstance(RadioButtonsDemoPage.class);
		RBpage.navigateToPage("Radio Buttons Demo", "Input Forms");
        
		Assert.assertEquals(RBpage.GetTitle(), title);	
	}
	
	
	@Test(dataProvider="Radio Button page data")
	public void radioBtnTest(String value) {
		childTest = test.createNode("Validate Radion button click");
		
		String result = RBpage.clickRadioBtn(value);
		log(String.format("Radio btn with value '%s' clicked and result displayed as '%s'.", value,result));
		
		Assert.assertTrue(RBpage.clickRadioBtn(value).contains(value));
	}
	
	@Test(dataProvider="Radio Button page data")
	public void groupRadioBtnTest(String gender, String ageGroup) {
		childTest = test.createNode("Validate Group Radion buttons");
		
		String result = RBpage.clickGroupRadioBtns(gender,ageGroup);
		
		log(String.format("The selected option for Sex:'%s' and Age:'%s', the displayed result:'%s' ", gender,ageGroup,result));
		
		SoftAssert SAssert = new SoftAssert();
		SAssert.assertTrue(result.contains(gender));
		SAssert.assertTrue(result.contains(ageGroup));
		SAssert.assertAll();
	}
	

	@DataProvider(name="Radio Button page data")
	public Object[][] resultFor_RadioBtnClick(Method mtd) throws Exception{
		JsonDataManager jdm = JsonDataProvider.loadData();
		Object[][] dataMap = jdm.getData(getClass(), mtd.getName()); 
					
		return dataMap;	
	}
	
	
}
