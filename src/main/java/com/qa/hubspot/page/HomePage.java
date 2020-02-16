package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementutil;
	
	
	// By Locators
	By Header = By.cssSelector("h1.private-page__title");
	By accountName = By.cssSelector("span.account-name ");
	
	By mainContactslink = By.id("nav-primary-contacts-branch");
    By childContactslink = By.id("nav-secondary-contacts");		
			
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(driver);
	}
	
	//Element Actions
	
	public String getHomePageTitle() {
		elementutil.waitForTitlePresent(AppConstants.HOME_PAGE_TITLE);
		return elementutil.doGetPageTitle();
	}
	
	public String getHomePageHeader() {
		
		return elementutil.doGetText(Header);
	}
	
	public String getLoggedInUserName() {
		
		return elementutil.doGetText(accountName);
	}
	
	public void clickOnContacts() {
			elementutil.waitForElementPresent(mainContactslink);
			elementutil.doClick(mainContactslink);
			
			elementutil.waitForElementPresent(childContactslink);
			elementutil.doClick(childContactslink);
	}
	
	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}

}
