package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

public class HomePageTest {
	
	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage loginpage;
	HomePage homepage;
	Credentials usercred;
	 
	@BeforeTest
	public void setup() throws InterruptedException {
		basepage = new BasePage();
		prop = basepage.init_properties();
		String browsername = prop.getProperty("browser");
		driver =basepage.init_driver(browsername);
		driver.get(prop.getProperty("url"));
		
		loginpage = new LoginPage(driver);
		
		usercred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
		homepage = loginpage.doLogin(usercred);	

	}
	
	@Test(priority =1)
	public void verifyHomePageTitleTest() {
		String title = homepage.getHomePageTitle();
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);		
	}
	
	@Test(priority =2)
	public void verifyHomePageHeaderTest() {
		String header = homepage.getHomePageHeader();
		Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
	}
	
	@Test(priority =3)
	public void verifyloggedInUserName() {
		String username = homepage.getLoggedInUserName();
		Assert.assertEquals(username, prop.getProperty("accountname"));
	}
	
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	

}
