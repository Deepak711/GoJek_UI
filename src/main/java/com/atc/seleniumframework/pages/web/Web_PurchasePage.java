package com.atc.seleniumframework.pages.web;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.atc.seleniumframework.utilities.general.GeneralUtils;
import com.atc.seleniumframework.utilities.general.WaitUtils;
import com.atc.seleniumframework.utilities.reporting.ExtentReportManager;
import com.atc.seleniumframework.utilities.reporting.ExtentTestManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class Web_PurchasePage {
	WebDriver driver;

	public Web_PurchasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public @FindBy(xpath = "//a[@class='btn buy' and text()='BUY NOW']") WebElement btn_Buynow;

	public @FindBy(xpath = "//div[@class='cart-checkout' and text()='CHECKOUT']") WebElement btn_Checkout;

	public @FindBy(xpath = "//div[@id='app']//a[@class='button-main-content']//span[text()='Continue']") WebElement btn_Continue;

	public @FindBy(xpath = "//a[@class='list' and @href='#/credit-card']") WebElement btn_CreditCard;

	public @FindBy(xpath = "//input[@name='cardnumber']") WebElement txt_Cardnumber;

	public @FindBy(xpath = "//span[text()='Invalid card number']") WebElement msg_InvalidCCNum;

	public @FindBy(xpath = "//input[@placeholder='MM / YY']") WebElement txt_ExpiryDate;

	public @FindBy(xpath = "//input[@placeholder='123']") WebElement txt_CVV;

	public @FindBy(xpath = "//label[text()='Midtrans Promo']") WebElement chk_Promo;

	public @FindBy(xpath = "//div[@class='text-button-main']") WebElement btn_Paynow;

	public @FindBy(xpath = "//*[@id='acsForm']//*[@class='col-xs-7']//*[@type='password']") WebElement txt_OTP;

	public @FindBy(xpath = "//button[@name='ok']") WebElement btn_OK;
	
//Method to check valid scenarios for Valid and Invalid Transactions
	public void purchasepillow(String vCNum, String vExpiryDate, String vCVV, String vOTP) throws InterruptedException {
		//Condition to start the purchase
		if (GeneralUtils.CheckElementdisplayed(btn_Buynow)) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Click on Buy Now");
			Assert.assertEquals(driver.findElement(By.xpath("//a[@class='btn buy' and text()='BUY NOW']")).getText(),
					"BUY NOW");
			GeneralUtils.clickWebElementbyJavascript(btn_Buynow);
		}
		//checkout
		WaitUtils.waitForPageToLoad(driver);
		if (GeneralUtils.CheckElementdisplayed(btn_Checkout)) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Validate the purchase order and click on Check out");
			Assert.assertEquals(
					driver.findElement(By.xpath("//div[@class='cart-checkout' and text()='CHECKOUT']")).getText(),
					"CHECKOUT");
			GeneralUtils.clickWebElementbyJavascript(btn_Checkout);
		}
		//Frame change and Purchase order summary
		WaitUtils.waitForPageToLoad(driver);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Switching to frame where user is navigated to checkout");
		driver.switchTo().frame("snap-midtrans");
		WaitUtils.waitForPageToLoad(driver);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Validate the item selected");
		if (GeneralUtils.CheckElementdisplayed(btn_Continue)) {
			Assert.assertEquals(btn_Continue.isDisplayed(), true, "Continue Button is present");
			GeneralUtils.clickWebElementbyJavascript(btn_Continue);
		}
		//Payment selection
		WaitUtils.waitForPageToLoad(driver);
		if (GeneralUtils.CheckElementdisplayed(btn_CreditCard)) {
			ExtentTestManager.getTest().log(LogStatus.INFO, "Payment options to select - credit card");
			Assert.assertEquals(btn_Continue.isDisplayed(), true, "Credit card option is present and selected");
			GeneralUtils.clickWebElementbyJavascript(btn_CreditCard);

		}
		//Enter valid payment Credentials
		WaitUtils.waitForPageToLoad(driver);
		if (GeneralUtils.CheckElementdisplayed(txt_Cardnumber)) {
			txt_Cardnumber.sendKeys(vCNum);
			// Assert.assertSame("4811 1111 1111 1114", vCNum);
		}
		WaitUtils.waitForPageToLoad(driver);
		if (GeneralUtils.CheckElementdisplayed(txt_ExpiryDate)) {
			txt_ExpiryDate.sendKeys(vExpiryDate);
			// Assert.assertSame("03/20", vExpiryDate);
		}
		WaitUtils.waitForPageToLoad(driver);
		if (GeneralUtils.CheckElementdisplayed(txt_CVV)) {
			txt_CVV.sendKeys(vCVV);
			//Condition to check if Invalid Credit card number is entered
			if (GeneralUtils.CheckElementdisplayed(msg_InvalidCCNum)) {
				ExtentTestManager.getTest().log(LogStatus.INFO,
						"Exit from the purchase as an invalid credit card details are entered");
				Assert.assertSame("4811 1111 1111 1114", vCNum);
				return;
			}
			// Assert.assertSame("123", vCVV);
		}
		WaitUtils.waitForPageToLoad(driver);
		if (GeneralUtils.CheckElementdisplayed(chk_Promo)) {
			Assert.assertTrue(chk_Promo.isDisplayed());
			GeneralUtils.clickWebElementbyJavascript(chk_Promo);
		}
		WaitUtils.waitForPageToLoad(driver);
		if (GeneralUtils.CheckElementdisplayed(btn_Paynow)) {
			Assert.assertTrue(btn_Paynow.isDisplayed());
			GeneralUtils.clickWebElementbyJavascript(btn_Paynow);
			
		}
		//Enter OTP and complete the transaction
		WaitUtils.waitForPageToLoad(driver);
		Thread.sleep(100000);
		WaitUtils.waitForPageToLoad(driver);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Switching to Payment Screen");
		//Switch to Child Frame
		driver.switchTo().frame(0);
		WaitUtils.waitForPageToLoad(driver);
		if (GeneralUtils.CheckElementdisplayed(txt_OTP)) {
			txt_OTP.sendKeys(vOTP);
			// Assert.assertSame("112233", vOTP);
		}
		WaitUtils.waitForPageToLoad(driver);
		if (GeneralUtils.CheckElementdisplayed(btn_OK)) {
			Assert.assertEquals(btn_OK.isDisplayed(), true, "OK button is present and selected");
			GeneralUtils.clickWebElementbyJavascript(btn_OK);
		}
		ExtentTestManager.getTest().log(LogStatus.INFO, "Successful Pillow Purchase using Credit Card");
	}
}
