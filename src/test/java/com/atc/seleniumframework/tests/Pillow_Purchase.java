package com.atc.seleniumframework.tests;

import org.testng.annotations.Test;

import com.atc.seleniumframework.pages.web.Web_HomePage;
import com.atc.seleniumframework.pages.web.Web_LoginPage;
import com.atc.seleniumframework.testbase.TestBase;
import com.atc.seleniumframework.utilities.configproperties.ConfigFileReader;
import com.atc.seleniumframework.utilities.general.GeneralUtils;
import com.atc.seleniumframework.utilities.reporting.ExtentTestManager;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Pillow_Purchase extends TestBase {
	ExtentTest extentTest;
	Web_LoginPage loginPage;
	Web_HomePage homePage;
	
	@Test
	public void Pillow_Purchase() throws Exception{
		//Load data from Excel Sheet
		GeneralUtils.readExceldata(dataMap, this.getClass().getSimpleName());
		
		extentTest= ExtentTestManager.startTest(this.getClass().getSimpleName(), "Verify User account Details");
		extentTest.log(LogStatus.INFO, "Test Execution Started");
		
		//Access Application
		loginPage = new Web_LoginPage(eventDriver);
		loginPage.launchApp(ConfigFileReader.getLoginInfo(ConfigFileReader.getLoginInfo("Environment")));
		
		
		
		
	}

}
