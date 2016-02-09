/*package com.cisco.citeis.scripts;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cisco.citeis.pages.LoginPage;
import com.cisco.citeis.testBase.TestBase;

public class VerifyLogin extends TestBase{
	
	@BeforeClass
	public void initialize(ITestContext testContext) {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
	}
	
	@Test
	public void verifyLogin(){
		homePage = loginPage.login(username,password);
		homePage.validateHomePageLoaded();
	}
	
	@AfterClass
	public void logout(){
		driver.quit();
	}

}
*/