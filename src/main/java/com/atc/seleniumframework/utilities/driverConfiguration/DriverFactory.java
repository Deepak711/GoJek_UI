package com.atc.seleniumframework.utilities.driverConfiguration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;




public class DriverFactory {
	static WebDriver webDriver =null;
	static DesiredCapabilities desiredcap;
	static ChromeOptions options ;
   
	
	public ChromeOptions getChromeoPtions()
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("start-maximised");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("disable-infobars");
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		options.setCapability(ChromeOptions.CAPABILITY, options);
		return options;
	}
	
	public static WebDriver setWebDriver(String Platformdriver)
	{
	
		switch(Platformdriver){
		 case "chrome":
			 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\chromedriver.exe");
		  	

		  options = new ChromeOptions();
		  options.addArguments("--disable-popup-blocking");
		  options.addArguments("disable-infobars");
		  options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,true);
			
		  webDriver = new ChromeDriver(options);
		  return webDriver;
		   
		 case "firefox":
			
			 
		 default:
			 webDriver = new ChromeDriver();
			return webDriver;
			
		}
	
		
	}
	
	public static WebDriver getWebDriver() {
		return webDriver;
	}
	
	
	
	
	
}
