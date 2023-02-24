package test;

import org.testng.annotations.Test;

import pages.AccountPermissionPage;
import pages.LoginPage;

public class AccountPermissionTest extends BaseTest{
	
	@Test
	public void verifycreateAccount() throws InterruptedException {
	test = extent.createTest("Create Account").assignAuthor("Brijesh").assignCategory("Smoke").assignDevice("Windows,Chrome");
	basePage.getInstancePage(LoginPage.class).doLogin();
	basePage.getInstancePage(AccountPermissionPage.class).createAdminAccount();
	}
	
	

}
