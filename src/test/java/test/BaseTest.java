package test;

import java.io.File;   
import java.io.FileInputStream;  
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.BasePage;
import utility.TestRailManager;
import utility.TestUtils;

public class BaseTest { 
	
	//Global Variable
	public String TestCaseID;
	public BasePage basePage;
	WebDriver driver;
	BaseTest baseTest;
	ExtentTest test;
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("Extentreport.html");
	
	private By moreButton = By.id("details-button");
	private By proceedLink = By.id("proceed-link");
	private By proceedButton = By.xpath("//input[@type='submit']");
	
	
    public WebElement getMoreButton() {
		return basePage.getElementforPage(moreButton);
	}

	public WebElement getProceedLink() {
		return basePage.getElementforPage(proceedLink);
	}
	public WebElement getProceedButton() {
		return basePage.getElementforPage(proceedButton);
	}
	
	@BeforeMethod
	public  void initialisation() throws Throwable { 
    	  
    	WebDriverManager.chromedriver().setup();
    	driver = new ChromeDriver();
    	driver.manage().deleteAllCookies();
    	driver.get("https://192.168.25.169/webadmin/common/");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	basePage  = new BasePage(driver);
    	driver.manage().window().maximize();
    	getMoreButton().click();
    	getProceedLink().click();
    	getProceedButton().click();	
    	}
	
	 
	 @AfterMethod
		public void teardown(ITestResult Result) throws Throwable {	
		 
		 baseTest = new BaseTest();
		 
		 try {
		 if(Result.getStatus()==ITestResult.SUCCESS) {
		  TestRailManager.addResultsForTestcases(TestCaseID, TestRailManager.TEST_CASE_PASSED_STATUS, ""); 
		 }
		 else if (Result.getStatus()==ITestResult.FAILURE) {
		  TestRailManager.addResultsForTestcases(TestCaseID, TestRailManager.TEST_CASE_FAILED_STATUS, "");
		  }
		 
//			if(Result.getStatus()== ITestResult.FAILURE) {
//				  //failure is defined as 2
//				  test.log(Status.FAIL, "Test Case Failed Is" + Result.getName());
//				  test.log(Status.FAIL, "Test Case Failed Is" + Result.getThrowable());
//				  
//				  String screenshotPath = baseTest.getScreenshot(driver, null);
//				 // extentTest.log(Status.FAIL, extentTest.addScreenCaptureFromPath(screenshotPath));
//				  test.fail( MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//			  } 
//			  
//			  else if (Result.getStatus()==ITestResult.SKIP) {
//				  test.log(Status.SKIP, "test case skipped is"+ Result.getName());
//			  }
		 }
		 catch(Exception e){
			 
				//  test.pass("Test Case Passed Is" + Result.getName());
			  
		 }
			  
		//above method need to be added for ur screenshot and for html report.
			driver.close();
		}
	 
//	 @BeforeTest 
	 public void setExtent() {
		 extent.attachReporter(spark);

	 }
	 
	
	 
	// @AfterTest
	 public void endReport() {
		 extent.flush();
		 //extent.close();
	 }
	 
	 public String getScreenshot(WebDriver driver, String ScreenshotName) throws IOException { //method for extent
		 
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String Destination =  System.getProperty("user.dir") + "/FailedScreenshot"+ ScreenshotName + dateName+ ".png" ;
			FileUtils.copyFile (source, new File (Destination));
			return Destination;
		   
		 }
	 
	
}
	
