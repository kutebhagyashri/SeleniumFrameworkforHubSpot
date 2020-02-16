package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	Properties prop;
	public OptionsManager optionsmanager;
	
	
	
	  public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	  
	  public static synchronized WebDriver getDriver() {
		   
		  return tldriver.get();
	  }
	
	
	public WebDriver init_driver(String browserName) {
		
		System.out.println("Browser Name is " + browserName);
		
		optionsmanager = new OptionsManager(prop);
		
		if(browserName.equals("chrome")) {
			 WebDriverManager.chromedriver().setup();
			
			 //driver = new ChromeDriver(optionsmanager.getChromeOptions());
			 
			 tldriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
			 
		}
			
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			
			//driver = new FirefoxDriver(optionsmanager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
		}
		
		else {
			System.out.println("Browser name" + browserName + "not found");
		}
		
		getDriver().manage().deleteAllCookies();
		//driver.manage().window().fullscreen();
		
		return getDriver();
	}
	
	public Properties init_properties() {
		  prop = new Properties();
		  String path = "./src/main/java/com/qa/hubspot/config/config.properties";
		  try {
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("Some issues with config properties....Correct your config");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  return prop;
		  
	}
	
	
	/**
	 * take screenshot
	 */

	
	  public String getScreenshot() {
	  
	  File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	  String path = System.getProperty("user.dir") + "/screenshots/" +
	  System.currentTimeMillis() + ".png"; File destination = new File(path);
	  
	  try { FileUtils.copyFile(src, destination); } catch (IOException e) {
	  System.out.println("screenshot captured failed..."); }
	  
	  return path;
	  
	  }
	 

}
