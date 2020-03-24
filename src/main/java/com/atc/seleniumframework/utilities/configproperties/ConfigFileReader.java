package com.atc.seleniumframework.utilities.configproperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.atc.seleniumframework.utilities.reporting.LoggerHelper;


public class ConfigFileReader {
	 //abstract
	/* Class to read the data provider properties file for login details */
	 
	//private static Logger log= LoggerHelper.getLogger(this.getClass());

	   public static String getLoginInfo(String key){  

	     Properties config = new Properties();
	     InputStream input = null;
	  String value=null;
	     try {
	            
	      String filename = System.getProperty("user.dir")+"\\src\\main\\resources\\configfile\\config.properties";
	      input = ConfigFileReader.class.getClassLoader().getResourceAsStream("\\configfile\\config.properties");
	     // System.out.println(input);
	      if(null==input){
	    	 // log.info("Sorry,unable to find " + filename);
	               
	          throw new IOException("Cannot Find the Properties file");

	      }

	      //load a properties file from class path, inside static method
	      config.load(input);
//	       Log.info("****Loaded login_info properties file****"+config.size());
	   value= config.getProperty(key);
	  
	     } catch (IOException ex) {
	     // log.info("stacktrace error",ex);
	        } finally{
	         if(input!=null){
	          try {
	    input.close();
	   } catch (IOException e) {
	   // log.info("stacktrace error",e);
	   }
	         }
	        }
	  return value;

	    }
}
