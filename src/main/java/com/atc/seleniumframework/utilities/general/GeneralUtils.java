package com.atc.seleniumframework.utilities.general;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.NoSuchElementException;

import com.atc.seleniumframework.utilities.reporting.ExtentTestManager;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import com.atc.seleniumframework.utilities.assertions.Assert;
import com.atc.seleniumframework.utilities.driverConfiguration.DriverFactory;

public class GeneralUtils {
	static JavascriptExecutor jsExecutor;
	
	public static String decodeString(String vEncodedString) {
		byte[] vDecodedBytes = Base64.getDecoder().decode(vEncodedString);
		String vStrDecoded = new String(vDecodedBytes);
		return vStrDecoded;
	}
	
	

	public static Map <String,String> readExceldata (Map <String,String> dataMap,String testcasename) throws IOException{
		
		DataFormatter dataformat = new DataFormatter();
		
		InputStream inStream=null;
		String filepath=System.getProperty("user.dir")+"\\src\\main\\resources\\Datasheets\\"+testcasename+".xlsx";
		
		 try {
			 inStream = new FileInputStream(filepath);
			 Workbook wb = WorkbookFactory.create(inStream);
			 
			  Sheet sheet1 = wb.getSheet(testcasename);
			  
			int lastcolumn =sheet1.getRow(0).getLastCellNum();
			
			try {
				for(int i=0;i<=lastcolumn;i++) {
					if(sheet1.getRow(1).getCell(i).getCellType()==Cell.CELL_TYPE_FORMULA){
						dataMap.put(dataformat.formatCellValue(sheet1.getRow(0).getCell(i)), wb.getCreationHelper().createFormulaEvaluator().evaluate(sheet1.getRow(1).getCell(i)).getStringValue());	
					}else {
						dataMap.put(dataformat.formatCellValue(sheet1.getRow(0).getCell(i)), dataformat.formatCellValue(sheet1.getRow(1).getCell(i)));	
					}
				}
			} catch (NullPointerException e) {
				//e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(inStream!=null)
				inStream.close();
		
			
		}
		 
		 return dataMap;
		 
	}
	
	public static void webTableValidation(String vTablelocator,Map <String,String> dataMap,String vColumns) {
		String vColarr[]=vColumns.split("\\^");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Started Table Validation");
		try {
			for(int i=0;i<vColarr.length;i++) {
				String vStractval=DriverFactory.getWebDriver().findElement(By.xpath(vTablelocator+"//tbody/tr[1]/td["+(i+1)+"]")).getText().trim();
				if(vStractval.contentEquals(dataMap.get(vColarr[i]))){
					ExtentTestManager.getTest().log(LogStatus.PASS,"Expected value for "+vColarr[i]+" :"+dataMap.get(vColarr[i])+" -----> "+"Actual value :"+vStractval);
				}else {
					Assert.assertEquals(vStractval, dataMap.get(vColarr[i])," Expected & Actual values mismatch");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in Table Validation");
			e.printStackTrace();
		}
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Table validation Compeletd Successfully");
		
	}
	

	public static boolean CheckElementdisplayed(WebElement Element) {
		try {
			Element.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static HttpResponse<String> startConnection(String url)
		      throws Exception {
		    HttpResponse<String> response = null;
		    try {
		      String dir = System.getProperty("user.dir");
		      Properties prop = new Properties();
		      prop.load(new FileInputStream(dir + "\\Properties\\config.properties"));
		      String host = prop.getProperty("dbo.host");
		      String port = prop.getProperty("dbo.port");
		      String urlBase = "https://" + host + ":" + port + "/";
		      String user = prop.getProperty("dbo.user1");
		      String password = prop.getProperty("dbo.password1");
		      Thread.sleep(4000);
		      if (url.contains("release")) {
		        Unirest.post(urlBase + url).basicAuth(user, password).asString();
		      }else {
		        response  = Unirest.get(urlBase + url).header("projectName", "Default").basicAuth(user, password).asString();
		      }
		    }catch (Exception e) {
		      throw new Exception("ERROR - Connection with Experitest Cloud failed");
		    }
		    
		    return response;
		  }
	
	public static void scrollToViewElement(WebElement Element) {
		jsExecutor = (JavascriptExecutor) DriverFactory.getWebDriver();
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",Element);
	}

	
	public static void clickWebElementbyJavascript(WebElement Element) {
        jsExecutor = (JavascriptExecutor) DriverFactory.getWebDriver();
        jsExecutor.executeScript("arguments[0].click();",Element);
    }

}
