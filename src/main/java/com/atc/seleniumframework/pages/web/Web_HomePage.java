package com.atc.seleniumframework.pages.web;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Web_HomePage {
	WebDriver driver;
	
	
	public Web_HomePage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public @FindBy(xpath="//a[@class='btn buy' and text()='BUY NOW']")
	WebElement btn_Buynow;
	
	public @FindBy(xpath="//div[@class='cart-checkout' and text()='CHECKOUT']")
	WebElement btn_Checkout;
	
	public @FindBy(xpath="//div[@class='text-button-main']")
	WebElement btn_Continue;
	
	public @FindBy(xpath="//a[@class='list with-promo']")
	WebElement btn_CreditCard;
	
	public @FindBy(xpath="//input[@name='cardnumber']")
	WebElement txt_Cardnumber;
	
	public @FindBy(xpath="//input[@placeholder='MM / YY']")
	WebElement txt_ExpiryDate;
	
	public @FindBy(xpath="//input[@placeholder='123']")
	WebElement txt_CVV;
	
	public @FindBy(xpath="//div[@class='text-button-main']")
	WebElement btn_Paynow;
	
	public @FindBy(xpath="//input[@type='password']")
	WebElement txt_OTP;
	
	public @FindBy(xpath="//button[@name='ok']")
	WebElement btn_OK;	
	
}
