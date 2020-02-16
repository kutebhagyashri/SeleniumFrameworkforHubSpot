package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	public Properties prop;
	public ChromeOptions co;
	public FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
		
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		
		co = new ChromeOptions();
		
		if(prop.getProperty("incognito").equals("yes"))  co.addArguments("--incognito");
		
		if(prop.getProperty("headless").equalsIgnoreCase("yes"))  co.getCapability("--headless");
		
		return co;
	}
	
public FirefoxOptions getFirefoxOptions() {
		
		fo = new FirefoxOptions();
	
		if(prop.getProperty("headless").equalsIgnoreCase("yes"))  fo.getCapability("--headless");
		
		return fo;
	}
	
	

}
