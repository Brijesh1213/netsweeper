package pages;

import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;



public abstract class AbstractPage {
	
	Properties prop;
	WebDriver driver ;
	WebDriverWait wait;
	
	
	
	public AbstractPage(WebDriver driver) {
		this.driver =driver;
        }

		public abstract String getTitle();
		public abstract WebElement getElementforPage ( By locator);
		public abstract void waitforElementPresent ( By locator);
		public abstract void waitForPageTitle (String title);
		
		public <Tpage extends BasePage> Tpage getInstancePage(Class<Tpage> pageClass) {
			
			try {
				return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
			} catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			
		
		}

}
