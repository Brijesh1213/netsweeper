package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends AbstractPage {

	public BasePage(WebDriver driver) {
		super(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	@Override
	public WebElement getElementforPage(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			return element;
		} catch (Exception e) {
			System.out.println("Some err occered while creation of element" + locator.toString());
			e.printStackTrace();
		}
		return element;
	}

	@Override
	public void waitforElementPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println("Exception occered while waiting for" + locator.toString());
		}

	}

	@Override
	public void waitForPageTitle(String title) {
		try {
			wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception e) {
			System.out.println("Exception occered while waiting for title" + title);
		}
	}

	public WebElement getStringBasedElement(By locator, String List) {
		List<WebElement> list = driver.findElements(locator);
		WebElement X = null;
		for (WebElement X1 : list) {
			if (X1.getText().equals(List)) {
				X = X1;
				break;
			}
		}
		return X;
	}

	public WebElement getAttributeBasedElement(By locator, String List, String Attribute) {
		List<WebElement> list = driver.findElements(locator);
		WebElement X = null;
		for (WebElement X1 : list) {
			if (X1.getAttribute(Attribute).equals(List)) {
				X = X1;
				break;
			}
		}
		return X;
	}
	public void scroll(WebElement element) {
		JavascriptExecutor  JsE = (JavascriptExecutor) driver;
		JsE.executeScript("arguments[0].scrollIntoView(true);", element);
	}

}