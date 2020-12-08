package com.qa.hubspot.BasePage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage 
{

	WebDriver driver;
	 Properties prop ;
	 public static boolean highlightElement;

	public WebDriver init_driver(String browsername)
	{
    highlightElement = prop.getProperty("highlight").equals("yes") ? true : false ; 
		System.out.println("The browser name is :" + browsername);
		if (browsername.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			if (prop.getProperty("headless").equals("yes")) 
			{	
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--headless");
			driver = new ChromeDriver(co);
			}
			else {
				driver = new ChromeDriver();
			}
		}
		else if (browsername.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("browser :" + browsername + " is not found , please pass the correct browser");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		return driver;
		
	}
	   
	public Properties init_properties() {
		 prop = new Properties();
		 String path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\Config.properties";
		 try {
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			System.out.println("some issue with config properties, please pass the correct config");
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return prop;
		 
	}
	
	
	
}
