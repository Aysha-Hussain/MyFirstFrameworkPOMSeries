package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.BasePage.BasePage;
import com.qa.hubspot.Util.ElementUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil elementutil;
	
	By header = By.xpath("//h1[@class='page-heading']");
	By accountname = By.xpath("//a[@class='account']");
	
	public HomePage(WebDriver driver) {
		this.driver= driver;
		elementutil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle() {
		return elementutil.doGetPageTitle();
	}
	
	
	public String getHomePageheader() {
		return elementutil.doGetText(header);
	}
	
	public String getLoggedInUserAccountname() {
		return elementutil.doGetText(accountname);
	}
	
	
	
}
