package com.qa.hubspot.logintest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.BasePage.BasePage;
import com.qa.hubspot.Util.AppConstants;
import com.qa.hubspot.Util.Credentials;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.Loginpage;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basepage;
	Properties prop;
	Loginpage loginpage;
	Credentials userCred;
	
	@BeforeMethod
	public void setup()  {
	    basepage = new BasePage();
	    prop = basepage.init_properties();
		String browsername = prop.getProperty("browser");
		driver = basepage.init_driver(browsername);
		String url =prop.getProperty("url");
		driver.get(url);
		
		loginpage = new Loginpage(driver);
	    userCred = new Credentials(prop.getProperty("username") , prop.getProperty("password"));
	
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitle() throws InterruptedException {
		Thread.sleep(5000);
		String title = loginpage.getPageTitle();
		System.out.println("the login page title is :" + title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}
	  
	@Test(priority = 2)
	public void verifyForgotPwdTest() throws InterruptedException {
		Thread.sleep(5000);
		boolean forgotPwd = loginpage.checkForgotPwdLink();
		Assert.assertTrue(forgotPwd);
	}
	
	@Test(priority = 3)
	public void LoginTest() throws InterruptedException  {
		Thread.sleep(5000);
		HomePage homepage =loginpage.doLogin(userCred);
		String title =homepage.getHomePageTitle();
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getLoginInvalidData() {
		Object data[][] = { 
				            {"test111@gmail.com", "test123"},
				            {"test11@gmail.com" , "testtest"},
				            {" " , "testtest"},
				            {"test@gmail.com" , " "}
		                   };
		return data;
	}
	
	
	@Test(priority = 4 , dataProvider = "getLoginInvalidData" , enabled = false)
    public void loginInvalidData(String username , String pwd) {
	userCred.setAppUsername(username);
	userCred.setAppPassword(pwd);
    loginpage.doLogin(userCred);
    Assert.assertTrue(loginpage.checkLoginErrorMsg());
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
