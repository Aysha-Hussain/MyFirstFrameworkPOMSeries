package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.BasePage.BasePage;
import com.qa.hubspot.Util.Credentials;
import com.qa.hubspot.Util.ElementUtil;

public class Loginpage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementutil;
	
	// 1. create the 'by' locators.
	By emailID = By.id("email");
	By password = By.id("passwd");
    By LoginButton = By.id("SubmitLogin");
    By ForgotPwd = By.linkText("Forgot your password?");
    By ErrorMsg = By.xpath("//div[@class='alert alert-danger']");
    
    public Loginpage(WebDriver driver) {
    	this.driver = driver;
    	elementutil = new ElementUtil(driver);
    }
    
    //page actions;
    
    public String getPageTitle() {
    	return elementutil.doGetPageTitle();
    }
    
    
    public boolean checkForgotPwdLink() {
    	return elementutil.doIsDisplayed(ForgotPwd);
    }
    
    
    
    public HomePage doLogin(Credentials userCred) {
    	
    	elementutil.doSendkeys(emailID, userCred.getAppUsername());
    	elementutil.doSendkeys(password, userCred.getAppPassword());
    	elementutil.doClick(LoginButton);
    	
    	return new HomePage(driver);
    }
    
    public boolean checkLoginErrorMsg() {
    	return elementutil.doIsDisplayed(ErrorMsg);
    }
    
    
    
    
    
}
