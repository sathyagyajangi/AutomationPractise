package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class temp {

	public static void main(String[] args) 
	{
		System.setProperty("webdriver.chrome.driver","CommonJarFiles/chromedriver.exe" );	

	
	WebDriver	driver = new ChromeDriver();
	
	
	
	driver.get("http://automationpractice.com/index.php");
	}

}
