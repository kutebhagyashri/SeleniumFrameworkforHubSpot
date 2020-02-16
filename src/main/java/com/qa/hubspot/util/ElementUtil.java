package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public ElementUtil(WebDriver driver) {
		
		this.driver= driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIMEOUT);
	}
	
	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}
	
	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		return true;
	}
	
	public boolean waitForElementVisisble(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}
	
	
	
	public String doGetPageTitle() {
		try {
		return driver.getTitle();
		}catch(Exception E) {
			System.out.println("Some Exception occured while getting the Title of the page.....");
		}
		return null;
	}
	
 
	
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			  if(waitForElementPresent(locator))
		        element = driver.findElement(locator);
		}catch(Exception E) {
			System.out.println("Some Exception occured while finding the element..........");
		}
		return element;
		
	}
	
	public void doClick(By locator){
		try {
		      getElement(locator).click();
		}catch(Exception e){
			System.out.println("Some Exception occured while clicking on the element.............");
		}
		
	}
	
	public void doSendKeys(By locator, String value) {
		
		try {
	    WebElement element = getElement(locator);	
		element.clear();
		element.sendKeys(value);
		}catch(Exception e) {
			System.out.println("Some Exception occured while passing value to Webelement.............");
		}
	}
	
	public boolean doIsDisplayed(By locator) {
		try {
		return getElement(locator).isDisplayed();
		}catch(Exception e) {
			System.out.println("Some Exception occured.................");
		}
		return false;
	}
	
	public String doGetText(By locator) {
		try {
		return getElement(locator).getText();
		}catch(Exception e) {
			System.out.println("Some Exception occured while getting the text of Webelement.............");
		}
		return null;
	}
	
	

}
