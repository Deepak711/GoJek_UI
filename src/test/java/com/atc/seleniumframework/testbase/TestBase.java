package com.atc.seleniumframework.testbase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.naming.event.EventDirContext;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;



import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import com.atc.seleniumframework.utilities.driverConfiguration.DriverFactory;
import com.atc.seleniumframework.utilities.general.GeneralUtils;
import com.atc.seleniumframework.utilities.listeners.EventCapture;
import com.atc.seleniumframework.utilities.reporting.ExtentTestManager;
import com.atc.seleniumframework.utilities.reporting.ExtentReportManager;


public class TestBase {
	
	public WebDriver Webdriver;
	public AndroidDriver<WebElement> Andrioddriver;
	public ExtentReports extent;
	WebDriver wait;
	public EventFiringWebDriver eventDriver;
	EventCapture eCapture;
	public Map <String,String> dataMap=new HashMap<String,String>();

	int pageLoadTimeOut=20;
	int implicitwait=20;
	int WebDriverWait=20;


	@BeforeSuite(alwaysRun=true)
	  public void beforeSuite() throws Exception{
		//Logger log= LoggerHelper.getLogger(WaitHelper.class);
	  }

	@Parameters("browser")
	@BeforeTest(alwaysRun=true)
	public void beforeTest(@Optional("chrome") String browser) {
	extent=ExtentReportManager.getReporter();
	
	
	if (browser.contains("chrome")) {
		Webdriver=DriverFactory.setWebDriver(browser);
		Webdriver.manage().timeouts().pageLoadTimeout(pageLoadTimeOut, TimeUnit.SECONDS);
		Webdriver.manage().timeouts().implicitlyWait(implicitwait, TimeUnit.SECONDS);	
		Webdriver.manage().window().maximize();
	
	} 
	 eventDriver = new EventFiringWebDriver(Webdriver);
	EventCapture eCapture = new EventCapture(); 
	eventDriver.register(eCapture);
	}
	
	
	
	 @AfterMethod(alwaysRun=true)
	  public void afterMethod(ITestResult result) {
		 if(result.getStatus()!=ITestResult.SUCCESS) {
			 if(result.getThrowable()!=null) {
				 ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
			 }
			 if(Webdriver==null && Andrioddriver!=null) {
				 Webdriver=Andrioddriver;
			 }
			 
			ExtentTestManager.getTest().log(LogStatus.FAIL, ExtentTestManager.getTest().addBase64ScreenShot(ExtentReportManager.CaptureScreenshot(Webdriver)));
		 }
		 
	  }


	  @AfterTest(alwaysRun=true)
	  public void afterTest() {
		  
		eventDriver.quit();
          //Unregister allows to detach
		eventDriver.unregister(eCapture);
	  
		if(Webdriver!=null) {
			Webdriver.quit(); 
		}
		  
	 
	  if(Webdriver!=null) {
			Webdriver.quit(); 
		}
	 
	  extent.endTest(ExtentTestManager.getTest());
	  
	  dataMap.clear();
	  }

	  @AfterSuite(alwaysRun=true)
	  public void afterSuite() {
		  extent.flush();
	  }

}
