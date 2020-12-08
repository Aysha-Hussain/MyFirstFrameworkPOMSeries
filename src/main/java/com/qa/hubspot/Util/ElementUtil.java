package com.qa.hubspot.Util;

import java.util.Properties;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.BasePage.BasePage;

public class ElementUtil extends BasePage {
	
	WebDriver driver;
	WebDriverWait wait;
	JavascriptUtil jsUtil;
	Properties prop ; 
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver , AppConstants.DEFAULT_TIMEOUT);
        jsUtil = new JavascriptUtil(driver)	;	
		
	}
	
	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}
	
	
	// this method is used to create the webelement on the basis of locator
	public WebElement getElement(By locator) {
		WebElement element =null;
		try {
			//if(waitForElementPresent(locator));
			
		 element =driver.findElement(locator);
		 if (highlightElement) {
			 jsUtil.flash(element);
		 }
		}
		catch(Exception e) {
			System.out.println("some exception occuring while cereating the element..");
		}
		
		return element;
	}

	public void doClick(By locator) {
		try {
		 getElement(locator).click();
			  }
		 catch (Exception e) {
			 System.out.println("some exception occuring while clicking the web element..");
		 }
	}
	
	public void doSendkeys(By locator , String value) {
		try {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
		
		}
		catch(Exception e) {
			System.out.println("some exception occuring while entering the value..");
		}
		
	}
	
	public boolean doIsDisplayed(By locator) {
		try {
		return getElement(locator).isDisplayed();
		}
		catch(Exception e) {
			System.out.println("some exception occuring ");
		}
		return false;
	}
	
	public String  doGetText(By locator) {
		try {
		return getElement(locator).getText();
		}
		catch(Exception e) {
			System.out.println("some exception occuring while getting the text..");
		}
		return null;
	}
	
	public String doGetPageTitle() {
		try {
		return driver.getTitle();
		}
		catch(Exception e ) {
			System.out.println("some exception occuring while getting the title..");
		}
		return null;
		
	}
	
	
	
	
	
	
}
