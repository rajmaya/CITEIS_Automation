package com.cisco.citeis.scripts;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cisco.citeis.pages.AppStartPage;
import com.cisco.citeis.testBase.TestBase;
import com.cisco.citeis.util.DataProviderUtil.StaticProviderClass;
import com.cisco.citeis.util.PropertyUtil;

public class VerifyAppFlow extends TestBase implements ITest{

	String testCase = "";
	String mTestCaseName = "";

	@BeforeMethod(alwaysRun = true)
	public void data(Method method, Object[] data) {
		driver.get(PropertyUtil.configMap.get("URL"));
		driver.manage().window().maximize();
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
	
	@Test(dataProvider="CiteisData",dataProviderClass=StaticProviderClass.class)
	public void validateAppFlow(Map<String, String> dataMap) {
		appStartPage.checkPageLoaded();
		homePage = appStartPage.clickManageBtn();
		myApplicationsPage = homePage.clickMyApplications();
		myApplicationsPage.validateAppFlow(dataMap.get("Application Name"),dataMap.get("Profile Name"),dataMap.get("EPG Name"));
	}

	@AfterClass
	public void logout() {
		driver.quit();
	}

	public String getTestName() {
		return this.mTestCaseName;
	}

}
