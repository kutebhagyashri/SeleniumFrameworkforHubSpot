package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementutil;
	
	//1. We Need to create the locators = BY
	
	By emailID = By.id("username");
	By password = By.id("password");
	By loginbutton = By.id("loginBtn");
	By SignUpLink = By.linkText("Sign up");
	By loginErrorText = By.xpath("//div[@class = 'private-alert__inner']");
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		elementutil = new ElementUtil(driver);
	
	}
	
	
	// 2. Page Actions :
	
	public String getPageTitle() {
		elementutil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
		return elementutil.doGetPageTitle();
	}
	
	public boolean checkSignUpLink() {
		
		return elementutil.doIsDisplayed(SignUpLink);
	}
	 
	public HomePage doLogin(Credentials usercred) {
		elementutil.doSendKeys(emailID, usercred.getAppUsername());
		elementutil.doSendKeys(password, usercred.getAppPassword());
		elementutil.doClick(loginbutton);
		
		return new HomePage(driver);
	}
	
	public boolean getLoginErrorText() {
		return elementutil.doIsDisplayed(loginErrorText);
	}

}
 