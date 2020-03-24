package com.atc.seleniumframework.utilities.listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.atc.seleniumframework.utilities.reporting.ExtentReportManager;
import com.atc.seleniumframework.utilities.reporting.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class EventCapture implements WebDriverEventListener {
	
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
	 // TODO Auto-generated method stub
	 System.out.println("From Event Listener");
	 }
	 
	 @Override
	 public void afterClickOn(WebElement arg0, WebDriver arg1) {
	 // TODO Auto-generated method stub
		 System.out.println("From Event Listener click");
		 ExtentTestManager.getTest().log(LogStatus.INFO, ExtentTestManager.getTest().addBase64ScreenShot(ExtentReportManager.CaptureScreenshot(arg1)));
	 }
	 
	 @Override
	 public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 @Override
	 public void afterNavigateBack(WebDriver arg0) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 @Override
	 public void afterNavigateForward(WebDriver arg0) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 @Override
	 public void afterNavigateRefresh(WebDriver arg0) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 @Override
	 public void afterNavigateTo(String arg0, WebDriver arg1) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 @Override
	 public void afterScript(String arg0, WebDriver arg1) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 @Override
	 public void beforeClickOn(WebElement arg0, WebDriver arg1) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 @Override
	 public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 @Override
	 public void beforeNavigateBack(WebDriver arg0) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 @Override
	 public void beforeNavigateForward(WebDriver arg0) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 @Override
	 public void beforeNavigateRefresh(WebDriver arg0) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 @Override
	 public void beforeNavigateTo(String arg0, WebDriver arg1) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 @Override
	 public void beforeScript(String arg0, WebDriver arg1) {
	 // TODO Auto-generated method stub
	 
	 }
	 
	 @Override
	 public void onException(Throwable arg0, WebDriver arg1) {
	 // TODO Auto-generated method stub
	 
	 }

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		
	} 

}
