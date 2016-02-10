package com.cisco.citeis.scripts;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cisco.citeis.pages.AppStartPage;
import com.cisco.citeis.testBase.TestBase;
import com.cisco.citeis.util.PropertyUtil;

public class VerifyAppFlow extends TestBase{

	@BeforeMethod(alwaysRun=true)
	public void initialize(){
		driver.get(PropertyUtil.configMap.get("URL"));
		driver.manage().window().maximize();
		appStartPage = PageFactory.initElements(driver, AppStartPage.class);
	}
	
	@Test(groups={"SanityTest","ExtendedSanityTest"})
	@Parameters({"application","profile","epg"})
	public void validatePositiveFlow(String application,String profile,String epg) {
		validateFlow(application, profile, epg);
	}
	
	@Test(groups={"SanityTest","ExtendedSanityTest"})
	@Parameters({"wrongapplication","profile","epg"})
	public void validateWrongApplication(String application,String profile,String epg) {
		validateFlow(application, profile, epg);
	}
	
	@Test(groups={"ExtendedSanityTest"})
	@Parameters({"application","wrongprofile","epg"})
	public void validateWrongProfile(String application,String profile,String epg) {
		validateFlow(application, profile, epg);
	}
	
	@Test(groups={"ExtendedSanityTest"})
	@Parameters({"application","profile","wrongepg"})
	public void validateWrongEPG(String application,String profile,String epg) {
		validateFlow(application, profile, epg);
	}
	
	private void validateFlow(String application,String profile,String epg){
		appStartPage.checkPageLoaded();
		homePage = appStartPage.clickManageBtn();
		myApplicationsPage = homePage.clickMyApplications();
		myApplicationsPage.validateAppFlow(application,profile,epg);
	}
	
	@AfterClass(alwaysRun=true)
	public void logout() {
		driver.quit();
	}

}
