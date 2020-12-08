package com.qa.hubspot.logintest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.BasePage.BasePage;
import com.qa.hubspot.Util.AppConstants;
import com.qa.hubspot.Util.Credentials;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.Loginpage;

public class HomePageTest {
	BasePage basepage;
	Properties prop;
	WebDriver driver;
	Loginpage loginpage;
	HomePage homepage;
	Credentials userCred;

	@BeforeMethod
	public void setup() throws InterruptedException {
		basepage = new BasePage();
	    prop = basepage.init_properties();
	    String browsername = prop.getProperty("browser");
		driver = basepage.init_driver(browsername);
		driver.get(prop.getProperty("url"));
		loginpage = new Loginpage(driver);
		Thread.sleep(5000);
		userCred = new Credentials(prop.getProperty("username") , prop.getProperty("password"));
		homepage = loginpage.doLogin(userCred);
		
	}
	
	
	@Test(priority=1)
    public void verifyHomePageTtile() {
	String title =	homepage.getHomePageTitle();
	System.out.println("home page title is :" + title);
	Assert.assertEquals(title, "My account - My Store");
	}
	
	@Test(priority=2)
	public void verifyHomepageHeader() {
		String header = homepage.getHomePageheader();
		System.out.println("the home page header is :" + header);
		Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
	}
	
	
	@Test(priority=3)
	public void verifyLoggedInUserTest() {
		String accountname =homepage.getLoggedInUserAccountname();
		System.out.println("the account name is:" + accountname);
		Assert.assertEquals(accountname, prop.getProperty("accountname"));
	}
	
	
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
