package com.cisco.citeis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

import com.cisco.citeis.actions.Button;
import com.cisco.citeis.actions.Element;
import com.cisco.citeis.common.ApplicationConstants;

public class AppStartPage {

	@FindBy(how = How.XPATH,using = ApplicationConstants.manageBtn)
	private WebElement manageBtn;
	
	@FindBy(how = How.XPATH,using = ApplicationConstants.accHeader)
	private WebElement accHeader;
	
	private WebDriver driver;

	public AppStartPage(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage clickManageBtn() {
		Button.click("Manage", manageBtn, driver);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public void checkPageLoaded(){
		if(Element.verify("ACC Application Header", accHeader, driver)){
			ATUReports.add("Validating Application Centric Cloud page is loaded","Application Centric Cloud page should load","Application Centric Cloud page is loaded", LogAs.PASSED, null);
		}else{
			ATUReports.add("Validating Application Centric Cloud page is loaded","Application Centric Cloud page should load","Application Centric Cloud page is not loaded", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

}
