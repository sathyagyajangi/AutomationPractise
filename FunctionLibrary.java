package CommonFunctionLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.Document;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFileUtil;


public class FunctionLibrary
{

	public static WebDriver StartBrowser(WebDriver driver) throws Throwable, Throwable
	{
		if(PropertyFileUtil.getcellvaluekey("Browser").equalsIgnoreCase("firefox"))
{
	driver =new FirefoxDriver();
}
		
		else
			if(PropertyFileUtil.getcellvaluekey("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","CommonJarFiles/chromedriver.exe" );
			 driver = new ChromeDriver();
		}
			else
				if(PropertyFileUtil.getcellvaluekey("Browser").equalsIgnoreCase("IE"))
				{
					System.setProperty("webdriver.iedriver.driver","CommonJarFiles/IEDriverServer.exe");
				driver= new InternetExplorerDriver();
				}
		
		
		return driver;
	}
	
		public static void OpenApplication(WebDriver driver) throws Throwable, Throwable
		{
			driver.manage().window().maximize();
	driver.get(PropertyFileUtil.getcellvaluekey("Url"));
		}
		
		
		public static void ClickAction(WebDriver driver,String LocatorType,String LocatorValue)
		{
			if(LocatorType.equalsIgnoreCase("id"))
			{
				driver.findElement(By.id(LocatorValue)).click();
			}
		
		
			else
				if(LocatorType.equalsIgnoreCase("name"))
					{
					driver.findElement(By.name(LocatorValue)).click();
				   }
				else
					if(LocatorType.equalsIgnoreCase("xpath"))
					{
						driver.findElement(By.xpath(LocatorValue)).click();
					}
		}
		
		
		public static void TypeAction(WebDriver driver,String LocatorType,String LocatorValue,String data)
		{
			if(LocatorType.equalsIgnoreCase("id"))
			{
				driver.findElement(By.id(LocatorValue)).clear();
				driver.findElement(By.id(LocatorValue)).sendKeys(data);
			}
			else
				if(LocatorType.equalsIgnoreCase("name"))
				{
					driver.findElement(By.name(LocatorValue)).clear();
					driver.findElement(By.name(LocatorValue)).sendKeys(data);
				}
			
				else
					if(LocatorType.equalsIgnoreCase("xpath"))
					{
						driver.findElement(By.xpath(LocatorValue)).clear();
						driver.findElement(By.xpath(LocatorValue)).sendKeys(data);
					}
			
			
			}
		public static void closeApplication(WebDriver driver)
		{
			driver.close();
		}
		
		public static void WaitForElement(WebDriver driver,String LocatorType,String LocatorValue)
		{
			WebDriverWait wait= new WebDriverWait(driver, 30);
			if(LocatorType.equalsIgnoreCase("id"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
			}
		
			else
				if(LocatorType.equalsIgnoreCase("name"))
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
				}
		
				else
					if(LocatorType.equalsIgnoreCase("xpath"))
					{
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
					}
		
		}
		
	public static void titleValidation(WebDriver driver,String exp_Title)
	{
		String act_Title=driver.getTitle();
	
		Assert.assertEquals(exp_Title, act_Title);
	}
		
		public static String generatedate()
		{
			Date date= new Date();
			SimpleDateFormat sdf= new SimpleDateFormat("YYY_MM_dd_ss");
			return sdf.format(date);
		
		}
		
		public static  void captureData(WebDriver driver,String LocatorType,String Locatorvalue) throws InterruptedException, Throwable
		
		{
			Thread.sleep(3000);	
			String data=" ";
			if(LocatorType.equalsIgnoreCase("id"))
			{
				data=driver.findElement(By.id(Locatorvalue)).getAttribute("value");
			}
			else
				if(LocatorType.equalsIgnoreCase("xpath"))
				{
					data=driver.findElement(By.xpath(Locatorvalue)).getAttribute("value");
				}
			
			FileWriter fw= new FileWriter("./CapturedData/Data.txt");
			BufferedWriter bw= new BufferedWriter(fw);
			bw.write(data);
			bw.flush();
			bw.close();
		}
		
	public static void dropDown(WebDriver driver,String LocatorType,String LocaterValue,String text)
	{
		Select sel;
		if(LocatorType.equalsIgnoreCase("id"))
		{
			sel=new Select(driver.findElement(By.id(LocaterValue)));
			
			sel.selectByVisibleText(text);
		}
			else
				if(LocatorType.equalsIgnoreCase("xpath"))
				{
					sel= new Select(driver.findElement(By.xpath(LocaterValue)));
					sel.selectByVisibleText(LocaterValue);
				}
		
		}
		
	public static void mouseOver(WebDriver driver,String LocaterType,String LocaterValue)
	
	{
		Actions act= new Actions(driver);		
		
		if(LocaterType.equalsIgnoreCase("id"))
		{
			act.moveToElement(driver.findElement(By.id(LocaterValue)));
		}
		else
		if(LocaterType.equalsIgnoreCase("xpath"))
		{
		
			act.moveToElement(driver.findElement(By.xpath(LocaterValue)));
			
		}
	
	
	}
	
	public static void dropdownAction(WebDriver driver,String LocaterType,String LocaterValue,String data)
	{
		Actions action= new Actions(driver);
		if(LocaterType.equalsIgnoreCase("id"))
		{
			
		WebElement element=driver.findElement(By.id(LocaterValue));
		
		action.moveToElement(element).sendKeys(data).sendKeys(Keys.ENTER).build().perform();
		
		
		}
		else
		{
			if(LocaterType.equalsIgnoreCase("xpath"))
			{
				WebElement element=driver.findElement(By.xpath(LocaterValue));
				
				action.moveToElement(element).sendKeys(data).sendKeys(Keys.ENTER).build().perform();
			}
				
		}
			
			}
	
	
	public static void dateePicker(WebDriver driver,String LocaterValue,String data)
	{
		((JavascriptExecutor)driver).executeScript("document.getElementsById('"+LocaterValue+"').value='"+data+"'");
				
	
	
	
	}
		
	
	
		

		
	
	
	
}
