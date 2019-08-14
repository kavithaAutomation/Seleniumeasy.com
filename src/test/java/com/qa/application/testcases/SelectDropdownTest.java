package com.qa.application.testcases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.application.pages.SelectDropdownPage;
import com.qa.application.util.JsonDataManager;
import com.qa.application.util.JsonDataProvider;

public class SelectDropdownTest extends BaseTest {

	public SelectDropdownPage SDpage;
	
	@Test(dataProvider="Dropdown Option to select", priority=-1 )
	public void pageTest(String title) {
		test = extent.createTest("Simple Dropdown Page");
		//test.assignCategory("Home page Test");
		
		childTest = test.createNode("validate Page by title");		
		log("validate Simple Dropdown Page by page title");
		
		SDpage = page.getClassInstance(SelectDropdownPage.class);
		SDpage.navigateToPage("Select Dropdown List", "Input Forms");
			        
		Assert.assertEquals(SDpage.GetTitle(), title);	
	}
	
	
	@Test(dataProvider="Dropdown Option to select")
	public void singleSelectTest(String selection) {
		childTest = test.createNode("validate dropdown box");		
		
		String result = SDpage.selectFrom_dropDown(selection);
		log(String.format("Option selected : '%s', result displayed: '%s'. ", selection, result));
		Assert.assertTrue(result.contains(selection));
		
	}
	
	@Test(dataProvider="Dropdown Option to select")
	public void multiSelectTest(String selection1, String selection2) {
		childTest = test.createNode("validate multi select dropdown box");		
		
		SoftAssert SAssert = new SoftAssert();
		String result = SDpage.selectFrom_multiDropDown(selection1, selection2, "First selected");
		log(String.format("The selected options are '%s', '%s'. The result for 'First Selected' btn is '%s' ", selection1,selection2, result));
		SAssert.assertTrue(result.contains(selection1));
		
		result = SDpage.selectFrom_multiDropDown(selection1, selection2, "All selected");
		log(String.format("The selected options are '%s', '%s'. The result for 'Get All Selected' btn is '%s' ", selection1,selection2, result));
		SAssert.assertTrue(result.contains(selection1));
		SAssert.assertTrue(result.contains(selection2));
		
		SAssert.assertAll();
	}
	
	
	@DataProvider(name = "Dropdown Option to select")
	public Object[][] getDataFor_MessageInput(Method mtd) throws Exception{
		
		JsonDataManager jdm = JsonDataProvider.loadData();
		Object[][] dataMap = jdm.getData(getClass(), mtd.getName()); 
					
		return dataMap;	
	}
	
	
	
	
}
