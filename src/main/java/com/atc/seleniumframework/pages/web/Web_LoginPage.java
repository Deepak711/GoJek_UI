package com.atc.seleniumframework.pages.web;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;



public class Web_LoginPage{
	EventFiringWebDriver driver;
	//EventFiringWebDriver eventDriver;
	public Web_LoginPage(EventFiringWebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public Web_HomePage launchApp(String vURL)
	{
		System.out.println(vURL);
		driver.get(vURL);
		Assert.assertTrue(driver.getCurrentUrl().contains(vURL),"URL MATCHED");
		return new Web_HomePage(driver);
		
	}

}
