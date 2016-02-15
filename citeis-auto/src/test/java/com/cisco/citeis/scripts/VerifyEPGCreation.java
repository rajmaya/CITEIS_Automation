package com.cisco.citeis.scripts;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.cisco.citeis.pages.AppStartPage;
import com.cisco.citeis.testBase.TestBase;
import com.cisco.citeis.util.PropertyUtil;
import com.cisco.citeis.util.DataProviderUtil.StaticProviderClass;

public class VerifyEPGCreation extends TestBase implements ITest{
	
	protected Map<String,String>dataMap;
	
	@Factory(dataProvider="CiteisData",dataProviderClass=StaticProviderClass.class)
	public VerifyEPGCreation() {
		// TODO Auto-generated constructor stub
		this.dataMap=dataMap;
	}
	String testCase = "";
	String mTestCaseName = "";

	@SuppressWarnings("unchecked")
	@BeforeMethod(alwaysRun = true)
	public void data(Method method, Object[] data) {
		/*driver.get(PropertyUtil.configMap.get("URL"));
		driver.manage().window().maximize();*/
		init(dataMap);
		appStartPage = PageFactory.initElements(driver, AppStartPage.class);
		if (data != null && data.length > 0) {
			HashMap<String,String> elements = null;
			for (Object element : data) {
				elements = (HashMap<String,String>)element;
				break;
			}
			if (elements.get("Description") != null)
				testCase=elements.get("Description");
		}
		this.mTestCaseName = String.format("%s : %s", method.getName(),testCase);
	}

	@Test(dataProvider="CreateEPG",dataProviderClass=StaticProviderClass.class)
	public void verifyEPGCreation(Map<String, String> dataMap){
		appStartPage.checkPageLoaded();
		homePage = appStartPage.clickManageBtn();
		myApplicationsPage = homePage.clickMyApplications();
		applicationDetailsPage = myApplicationsPage.validateApplication(dataMap.get("Application Name"));
		currentProfilePage = applicationDetailsPage.validateAppNProfile(dataMap.get("Application Name"),dataMap.get("Profile Name"));
		currentProfilePage.createAndValidateEPG(dataMap);
	}
	
	@AfterClass
	public void logout() {
		driver.quit();
	}

	public String getTestName() {
		return this.mTestCaseName;
	}

}
