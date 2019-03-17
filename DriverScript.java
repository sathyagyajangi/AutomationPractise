package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctionLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript
{
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	public void starTest() throws Throwable
	{
		ExcelFileUtil excel= new ExcelFileUtil();
		
		
		String Module_status=" ";
		for(int i=1;i<=excel.rowCount("MasterTestCases");i++)
				
		
		if(excel.getData("MasterTestCases", i,2).equalsIgnoreCase("Y"))
		{
			String TcModule=excel.getData("MasterTestCases", i, 1);
			report=new ExtentReports("./Reports/"+TcModule+".html"+"_"+FunctionLibrary.generatedate());
			
			logger=report.startTest(TcModule);
			int rowcount=excel.rowCount(TcModule);
		for(int j=1;j<=rowcount;j++)
			{
				String description=excel.getData(TcModule, j, 0);
				String Object_type=excel.getData(TcModule, j, 1);
				String Locator_type=excel.getData(TcModule, j, 2);
				String locator_value=excel.getData(TcModule, j, 3);
				String test_data=excel.getData(TcModule, j, 4);
			
				
				try
				{
			
				if(Object_type.equalsIgnoreCase("StartBrowser"))
				{
					driver=FunctionLibrary.StartBrowser(driver);
					logger.log(LogStatus.INFO, description);
				}
				if(Object_type.equalsIgnoreCase("OpenApplication"))
				{
					FunctionLibrary.OpenApplication(driver);
					logger.log(LogStatus.INFO, description);
				}
				if(Object_type.equalsIgnoreCase("WaitForElement"))
				{
					FunctionLibrary.WaitForElement(driver, Locator_type, locator_value);
					logger.log(LogStatus.INFO, description);
				}
				if(Object_type.equalsIgnoreCase("TypeAction"))
				{
					FunctionLibrary.TypeAction(driver, Locator_type, locator_value, test_data);
					logger.log(LogStatus.INFO, description);
				}
				if(Object_type.equalsIgnoreCase("ClickAction"))
				{
					FunctionLibrary.ClickAction(driver, Locator_type, locator_value);
					logger.log(LogStatus.INFO, description);
				}
				if(Object_type.equalsIgnoreCase("closeApplication"))
				{
					FunctionLibrary.closeApplication(driver);
					logger.log(LogStatus.INFO, description);
				}
				if(Object_type.equalsIgnoreCase("titleValidation"))
				{
					FunctionLibrary.titleValidation(driver, test_data);
					logger.log(LogStatus.INFO, description);
				}
				if(Object_type.equalsIgnoreCase("capturedData"))
				{
					FunctionLibrary.captureData(driver, Locator_type, locator_value);
				
					logger.log(LogStatus.INFO, description);
				}
				
				if(Object_type.equalsIgnoreCase("dropDown"))
				{
					FunctionLibrary.dropDown(driver, Locator_type, locator_value, test_data);
					logger.log(LogStatus.INFO, description);
				}
				if(Object_type.equalsIgnoreCase("mouseOver"))
				{
					FunctionLibrary.mouseOver(driver, Locator_type, locator_value);
					logger.log(LogStatus.INFO, description);
				}
				
				if(Object_type.equalsIgnoreCase("datePicker"))
				{
					FunctionLibrary.dateePicker(driver, locator_value, test_data);
					logger.log(LogStatus.INFO, description);
				}
				
				if(Object_type.equalsIgnoreCase("dropdownAction"))
				{
					FunctionLibrary.dropdownAction(driver, Locator_type, locator_value, test_data);
				
					logger.log(LogStatus.INFO, description);
				}
				
				excel.setData(TcModule, j, 5,"Pass");
				
				Module_status="True";
				logger.log(LogStatus.PASS, description+"Pass");
				
				}
				
				catch(Exception e)
				{
					excel.setData(TcModule, j, 5,"Fail");
					Module_status="False";
					logger.log(LogStatus.FAIL, description+" Fail");
						//generate screenshot
					File scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("./Screenshots/"+description+"_"+FunctionLibrary.generatedate()+".jpg"));
				break;
				}
				catch(AssertionError a)
				{
					excel.setData(TcModule, j, 5,"Fail");
					Module_status="False";
					logger.log(LogStatus.FAIL, description+" Fail");
						//generate screenshot
					File scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("./Screenshots/"+description+"_"+FunctionLibrary.generatedate()+".jpg"));
				break;
				}
				
				}
		
		if(Module_status.equalsIgnoreCase("True"))
		{
			excel.setData("MasterTestCases",i,3,"Pass");
		}
		
		else
		{
			excel.setData("MasterTestCases",i,3,"Fail");
		}
		}
		else
		{
			if(Module_status.equalsIgnoreCase("Not Executed"));
		}
		report.endTest(logger);
		report.flush();
		
	}
	

}
