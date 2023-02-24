package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPermissionPage extends BasePage {

	public AccountPermissionPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	  private By sideBarList = By.xpath("//div[@class='widget stay-on-collapse']/nav/ul/li/a/span");
	  private By accounts = By.xpath("//nav[@role='navigation']/ul/li[2]/ul/li[1]");
	  private By accountTabs = By.xpath("//ul[@class='nav nav-tabs ']/li/a");
	  private By pageTitle = By.xpath("//ol[@class='breadcrumb']/li[2]/a/strong");
	  private By createAdvanceList = By.xpath("//div[@class='form-group']/div/input");
	  private By submit = By.xpath("//input[@type='submit']");
	  private By checkbox = By.className("checkbox");
	//WebElements
	       public WebElement getsideBar() {
	           return getElementforPage(sideBarList);
	      }
	       public WebElement getaccountsTab() {
		  	   return getElementforPage(accounts);
		  }
	       public WebElement getcreateAdvanceTab() {
			  	return getElementforPage(accountTabs);
		  }
	       public WebElement getcreateAdvanceList() {
			  	return getElementforPage(createAdvanceList);
		  }
	       public WebElement getSubmit() {
			  	return getElementforPage(submit);
		  }
	      public WebElement getCheckboxNoPassword() {
			  	return getElementforPage(checkbox);
		  }	
	      public WebElement getPageTitle() {
			  	return getElementforPage(pageTitle);
		  }	
	      
	      //methodss
	       public void createAdminAccount() throws InterruptedException {
	    	   driver.switchTo().frame(1);	    	   
	    	   if(getaccountsTab().isDisplayed()) {
	    		   System.out.println("if");
	    		   getaccountsTab().click();
	    		   getAttributeBasedElement(accountTabs, "add_tab", "id").click();
	    		   //getcreateAdvanceTab().click();     		  
	    	   }
	    	   else {  
	    		   System.out.println("else");
	    		   getStringBasedElement(sideBarList, "Accounts").click();
	    		   getaccountsTab().click();
	    		   getAttributeBasedElement(accountTabs, "add_tab", "id").click();  
	    		   }
        	   getAttributeBasedElement(createAdvanceList, "Login Name", "placeholder").sendKeys("selenium");
	    	   getAttributeBasedElement(createAdvanceList, "Organization", "placeholder").sendKeys("org");
	    	   Thread.sleep(5000);
	    	   getCheckboxNoPassword().click();
	    	   scroll(getSubmit());
	    	   getSubmit().click();
	    	   Thread.sleep(5000);
	       }
}
