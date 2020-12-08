package com.qa.hubspot.Util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtil {
	
	WebDriver driver;
	
	public JavascriptUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public void flash(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i =0; i< 10; i ++) {
			changeColor("rbg(0,200,0)" , element );
			changeColor(bgcolor , element);
		}
	}
	
	public void changeColor(String color , WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		
	}

}


