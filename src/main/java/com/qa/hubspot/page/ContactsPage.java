package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

public class ContactsPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementutil;
	JavaScriptUtil jsUtil;
	
	
	
	By creatcontactbutton = By.xpath("//div[@class = 'add-control']/button[@type ='button'] //span[text()='Create contact']");
	
	By email = By.xpath("//input[@id ='UIFormControl-7']");
	By firstname = By.xpath("//input[@id ='UIFormControl-8']");
	By Lastname = By.xpath("//input[@id ='UIFormControl-10']");
	By jobTitle = By.xpath("//input[@id ='UIFormControl-14']");
	
	By createcontactformbutton = By.xpath("(//span[text()='Create contact'])[position()= 2]");
	
	public ContactsPage(WebDriver driver) {
			this.driver = driver;
			elementutil = new ElementUtil(driver);
			jsUtil = new JavaScriptUtil(driver);
		
	}
	
	public String getContactsPageTitle() {
		elementutil.waitForTitlePresent("Contacts");
		return elementutil.doGetPageTitle();
	}
	
	public void creatNewContact(String mail, String FN, String LN, String jobtitle) {
		
		elementutil.waitForElementPresent(creatcontactbutton);
		
		elementutil.doClick(creatcontactbutton);
		elementutil.doSendKeys(email, mail);
		elementutil.doSendKeys(firstname, FN);
		elementutil.doSendKeys(Lastname, LN);
		elementutil.doSendKeys(jobTitle, jobtitle);
		jsUtil.clickElemetByJS(elementutil.getElement(createcontactformbutton));
		
	}

}
