package com.qa.application.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.application.pages.CheckboxDemoPage;

public class CheckboxDemoTest extends BaseTest {

	public CheckboxDemoPage CKpage;
	
	
	@Test(dataProvider = "success message for checkbox", priority=-1)
	public void pageTest(String title) {
		test = extent.createTest("Checkbox Demo Page");
		//test.assignCategory("Home page Test");
		
		childTest = test.createNode("validate checkbox demo page");		
		log("validate checkbox demo page by page title");
		
		CKpage = page.getClassInstance(CheckboxDemoPage.class);
		CKpage.navigateToPage("Checkbox Demo", "Input Forms");
			        
		Assert.assertEquals(CKpage.GetTitle(), title);	
	}
	
	@Test(dataProvider = "success message for checkbox")
	public void singleCheckboxTest(String successMessage) {
		childTest = test.createNode("validate single checkbox field");		
		log(String.format("validate single checkbox field by message ' %s ' ", successMessage));
		
		Assert.assertEquals(CKpage.clickSingleCheckbox(), successMessage);
	}
	
	/*
	 * 	Click on 'Check All' to check all checkboxes at once.
	 *	When you check all the checkboxes, button will change to 'Uncheck All'
	 *	When you uncheck at least one checkbox, button will change to 'Check All' 
	 */
	@Test
	public void multipleCheckboxTest() {
		childTest = test.createNode("validate multiple checkbox field");		
		
		Map<String, String> checkAllbtn = CKpage.validateCheckall_uncheckOne();
				
		SoftAssert SAssert = new SoftAssert();
		
		log(String.format(String.format("'Check All' btn clicked and btn value now is '%s'", checkAllbtn.get("btnValue"))));
		SAssert.assertEquals(checkAllbtn.get("btnValue"), "Uncheck All");
		
		log(String.format("'Check All' btn clicked and all the checkboxes clicked : '%s'", checkAllbtn.get("allChecked")));
		SAssert.assertEquals(checkAllbtn.get("allChecked"), "true");
		
		log(String.format(String.format("'Check All' btn clicked and unchecked one checkbox, btn value now is '%s' ", checkAllbtn.get("oneChecked_btnValue"))));
		SAssert.assertEquals(checkAllbtn.get("oneChecked_btnValue"), "Check All");	
		
		SAssert.assertAll();
		
	}
	
	@DataProvider(name="success message for checkbox")
	public Object[][] messageFor_success(Method mtd){
		if(mtd.getName().contains("CheckboxTest"))
			return new Object[][] {
				{"Success - Check box is checked"}
			};
		else
			return new Object[][] {
			{"Selenium Easy - Checkbox demo for automation using selenium"}
		};
	}
	
	
}
