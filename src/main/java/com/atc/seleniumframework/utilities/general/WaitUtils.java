package com.atc.seleniumframework.utilities.general;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WaitUtils {
	static JavascriptExecutor jsExecutor;
	static WebDriverWait wait;
	
	public static void waitForPageToLoad(WebDriver driver) {
		jsExecutor=(JavascriptExecutor) driver;
		
		try {
			if(jsExecutor.executeScript("return document.readyState").toString().equalsIgnoreCase("complete")) {
				return;
			}else {
				for(int i=0;i<=60;i++) {
					try {
						Thread.sleep(1000);
						if(jsExecutor.executeScript("return document.readyState").toString().equalsIgnoreCase("complete")) {
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (JavascriptException e) {
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
	}
	
	public static void waitForVisibilityOfElement(WebDriver driver,WebElement element) {
		try {
		wait=new WebDriverWait(driver, 30);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		
		
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
		
		}
		
		
	}
	
	public static void waitForInVisibilityOfElement(WebDriver driver,WebElement element) {
			
			wait=new WebDriverWait(driver, 10);
			try {
				wait.until(ExpectedConditions.visibilityOf(element));
			} catch (Exception e) {
			
			}
			
			
		}
	
	public static void waitForMobileSyncElement(WebDriver driver) {
		WebDriverWait Wait =new WebDriverWait(driver, 10);
		
		try {

			Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'Syncing')]")));
			WebElement Elem=driver.findElement(By.xpath("//*[contains(@text,'Syncing')]"));
			if(Elem.isDisplayed()) {
				
				for(int i=0;i<10;i++) {
					if(Wait.until(ExpectedConditions.invisibilityOf(Elem))) {
						break;
					}else {
						continue;
					}
				}
				
			}
		} catch (Exception e) {
	
		}
		
	}

}
