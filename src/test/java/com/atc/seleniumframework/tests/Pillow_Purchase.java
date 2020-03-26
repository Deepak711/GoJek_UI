package com.atc.seleniumframework.tests;

import org.testng.annotations.Test;

import com.atc.seleniumframework.pages.web.Web_PurchasePage;
import com.atc.seleniumframework.pages.web.Web_OrderPage;
import com.atc.seleniumframework.testbase.TestBase;
import com.atc.seleniumframework.utilities.configproperties.ConfigFileReader;
import com.atc.seleniumframework.utilities.general.GeneralUtils;
import com.atc.seleniumframework.utilities.general.WaitUtils;
import com.atc.seleniumframework.utilities.reporting.ExtentTestManager;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Pillow_Purchase extends TestBase {
	ExtentTest extentTest;
	Web_OrderPage orderPage;
	Web_PurchasePage purchasePage;
	
	@Test
	public void Pillow_Purchase() throws Exception{
		//Load data from Excel Sheet
		GeneralUtils.readExceldata(dataMap, this.getClass().getSimpleName());
		
		extentTest= ExtentTestManager.startTest(this.getClass().getSimpleName(), "Pillow Purchase");
		extentTest.log(LogStatus.INFO, "Test Execution Started");
		
		//Access Application
		orderPage = new Web_OrderPage(eventDriver);
		purchasePage= orderPage.launchApp(ConfigFileReader.getLoginInfo(ConfigFileReader.getLoginInfo("Environment")));
		
		//Purchase Pillow
		WaitUtils.waitForPageToLoad(Webdriver);
		purchasePage.purchasepillow(dataMap.get("CreditCardNumber"), dataMap.get("ExpiryDate"), dataMap.get("CVV"), dataMap.get("OTP"));		
		
	}

}
