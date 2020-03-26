package com.atc.seleniumframework.pages.web;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

public class Web_OrderPage {
	EventFiringWebDriver driver;

	// EventFiringWebDriver eventDriver;
	public Web_OrderPage(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Method launches the application
	public Web_PurchasePage launchApp(String vURL) {
		driver.get(vURL);
		Assert.assertTrue(driver.getCurrentUrl().contains(vURL), "URL MATCHED");
		return new Web_PurchasePage(driver);

	}

}
