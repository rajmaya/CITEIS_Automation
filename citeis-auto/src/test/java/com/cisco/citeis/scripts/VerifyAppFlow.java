package com.cisco.citeis.scripts;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.ISuite;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cisco.cat.reports.exceptions.CATReporterException;
import com.cisco.citeis.pages.AppStartPage;
import com.cisco.citeis.testBase.TestBase;
import com.cisco.citeis.util.DataProviderUtil.StaticProviderClass;

public class VerifyAppFlow extends TestBase{
	
	protected Map<String,String>dataMap;
	
	@Factory(dataProvider="CiteisData",dataProviderClass=StaticProviderClass.class)
	public VerifyAppFlow(Map<String, String> dataMap) {
		this.dataMap=dataMap;
	}

	@BeforeMethod(alwaysRun=true)
	public void initialize(){
		//suiteName = suite.getName();
		init(dataMap);
		appStartPage = PageFactory.initElements(driver, AppStartPage.class);
	}
	
	@Test(groups={"SanityTest","ExtendedSanityTest","FullTest"})
	@Parameters({"application","profile","epg"})
	public void validatePositiveFlow(String application,String profile,String epg) {
		validateFlow(application, profile, epg);
	}
	
	@Test(groups={"SanityTest","ExtendedSanityTest","FullTest"})
	@Parameters({"wrongapplication","profile","epg"})
	public void validateWrongApplication(String application,String profile,String epg) {
		validateFlow(application, profile, epg);
	}
	
	@Test(groups={"ExtendedSanityTest","FullTest"})
	@Parameters({"application","wrongprofile","epg"})
	public void validateWrongProfile(String application,String profile,String epg) {
		validateFlow(application, profile, epg);
	}
	
	@Test(groups={"FullTest"})
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
	
	@AfterMethod(alwaysRun=true)
	public void logout() {
		driver.quit();
	}
	
}
