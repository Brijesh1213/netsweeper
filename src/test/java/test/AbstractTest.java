package test;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractTest {
	
	Properties prop;
	WebDriver driver ;
	WebDriverWait wait;
	
	
	
	public AbstractTest(WebDriver driver) {
		this.driver =driver;
        }

	public abstract WebElement getElementforTest( By locator);
		
		
		

}
