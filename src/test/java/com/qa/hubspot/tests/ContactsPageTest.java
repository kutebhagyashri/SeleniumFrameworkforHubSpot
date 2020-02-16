package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.ContactsPage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ExcelUtil;

public class ContactsPageTest {
	
	
	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage loginpage;
	HomePage homepage;
	Credentials usercred;
	ContactsPage contactspage;
	 
	@BeforeMethod
	public void setup() throws InterruptedException {
		basepage = new BasePage();
		prop = basepage.init_properties();
		String browsername = prop.getProperty("browser");
		driver =basepage.init_driver(browsername);
		driver.get(prop.getProperty("url"));
		
		loginpage = new LoginPage(driver);
		
		usercred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
		homepage = loginpage.doLogin(usercred);	
		
		contactspage = homepage.goToContactsPage();

	}
	
	@Test(priority = 1)
	public void verifyContactsPageTitle() {
		String title = contactspage.getContactsPageTitle();
		System.out.println("Contacts page Title is " +title);
		Assert.assertEquals(title, "Contacts");
	}
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object[][] data = ExcelUtil.getTestData("Contacts");
		return data;
		
	}
	
	@Test(priority = 2, dataProvider = "getContactsTestData")
	public void creatContactsTest(String email, String firstname, String lastname, String jobtitle) {
		contactspage.creatNewContact(email, firstname, lastname, jobtitle);
		
	}
		

	@AfterMethod
		public void tearDown() {
			driver.quit();
		}

	
	
	
	
}
