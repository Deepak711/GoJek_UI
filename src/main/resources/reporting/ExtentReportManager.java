package com.atc.seleniumframework.utilities.reporting;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

import com.atc.seleniumframework.utilities.configproperties.ConfigFileReader;

public class ExtentReportManager {

	static ExtentReports extreport;
	static Calendar cal;
	static SimpleDateFormat FormatDateValue;
	
	public synchronized static ExtentReports getReporter() {
		String ResultFilePath;
		cal=Calendar.getInstance();
		FormatDateValue=new SimpleDateFormat("MM-dd-yyyy hh-mm-ss a");
		
		File file=new File(System.getProperty("user.dir")+"//ExtentReports//"+ConfigFileReader.getLoginInfo("ReleaseName"));
		
		if(!file.exists()) {
			if(!file.mkdirs()) {
				System.out.println("Unable to create folder---"+System.getProperty("user.dir")+"//ExtentReports//"+ConfigFileReader.getLoginInfo("ReleaseName"));
			}
		}
		
		ResultFilePath=System.getProperty("user.dir")+"//ExtentReports//"+ConfigFileReader.getLoginInfo("ReleaseName")+"//ExtentReport_"+ConfigFileReader.getLoginInfo("ReleaseName")+"_"+ConfigFileReader.getLoginInfo("Environment")+"_"+FormatDateValue.format(cal.getTime())+".html";
		//ResultFilePath=System.getProperty("user.dir")+"//ExtentReports//"+ConfigFileReader.getLoginInfo("ReleaseName")+"//ExtentReport_"+ConfigFileReader.getLoginInfo("ReleaseName")+"_"+ConfigFileReader.getLoginInfo("Environment")+".html";
		if(extreport==null) {
			extreport=new ExtentReports(ResultFilePath,true);
			extreport.addSystemInfo("Environment", ConfigFileReader.getLoginInfo("Environment")) 
					 .addSystemInfo("Application URL", ConfigFileReader.getLoginInfo(ConfigFileReader.getLoginInfo("Environment")))
					 .addSystemInfo("Release Name", ConfigFileReader.getLoginInfo("ReleaseName")) 
	                 .addSystemInfo("Host Name", getHostName())
	                 .addSystemInfo("User Name", System.getProperty("user.name"))        
	    			.loadConfig(new File(System.getProperty("user.dir")+"//extent-config.xml"));
		}
		return extreport;
	}
	
	public static String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String CaptureScreenshot(WebDriver driver) {
		
	
			//Rectangle screensize=new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			//BufferedImage Screencapture=null;
			String base64Encoded="";
		//	String filepath="";
			
		try {
/*
			Screencapture = new Robot().createScreenCapture(screensize);
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        ImageIO.write(Screencapture, "png", baos);
		        baos.flush();
		        byte[] encodeBase64 = Base64.getEncoder().encode(baos.toByteArray());
		        base64Encoded = new String(encodeBase64);
		        baos.close();*/
			
		
			/*
			   File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			   File screenshotLocation = new File("D:\\ATC FrameWork\\DevOps_CoE\\UI_Click\\ExtentReports\\Screenshots\\test.png");
			        FileUtils.copyFile(screenshot, screenshotLocation);
			        InputStream is = new FileInputStream(screenshotLocation);
			        byte[] imageBytes = IOUtils.toByteArray(is);
			         base64Encoded = Base64.getEncoder().encodeToString(imageBytes);
			*/
		        
		        
		    
			
			base64Encoded = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
			  
		
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	return  "data:image/png;base64,"+base64Encoded;
	//return filepath;
	
	}

}
