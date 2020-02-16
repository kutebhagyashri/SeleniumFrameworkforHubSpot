package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage loginpage;
	Credentials usercred;
	
	@BeforeTest
	@Parameters(value= {"browser"})
	public void setUp(String browser) {
		String browserName = null;
		basepage = new BasePage();
		prop = basepage.init_properties();
		
		if(browser.equals(null)) {
			browserName = prop.getProperty("browser");
		}else {
			browserName = browser;
		}
		
		
		driver = basepage.init_driver(browserName);
		driver.get(prop.getProperty("url"));	
		
		loginpage = new LoginPage(driver);
		usercred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test(priority = 1)
	public void verifyLoginPageTitleTest() throws InterruptedException {
		String title = loginpage.getPageTitle();
		System.out.println("Login Page title is" +title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
		
	}
	
	@Test(priority = 2)
	public void verifySignLinkTest() {
		Assert.assertTrue(loginpage.checkSignUpLink());
	}
	
	@Test(priority = 3)
	public void loginTest() {
		loginpage.doLogin(usercred);
	}
	
	//Data provider will always return two dimension(2D) array
	
	@DataProvider
	public Object[][] getInvalidData() {
		Object data[][] = {{"Test@gmail.com", "Test123"}, 
				          {"Test1@gmail.com", " "}, 
				          {" ", "Test12345"},
				          {"test", "Test12345"},
				          {" ", " "}
				         };
		
		return data;
		 
	}
	
	
	@Test(priority = 4, dataProvider = "getInvalidData", enabled = false)
	public void login_InvalidTestCase(String username, String pwd) {
		usercred.setAppUsrename(username);
		usercred.setAppPassword(pwd);
		loginpage.doLogin(usercred);
		Assert.assertTrue(loginpage.getLoginErrorText());
	}
	
	@AfterTest
	public void tearDownMethod() {
		driver.quit();
	}

}
