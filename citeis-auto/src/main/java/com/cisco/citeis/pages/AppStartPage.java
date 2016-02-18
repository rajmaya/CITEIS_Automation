package com.cisco.citeis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cisco.cat.reports.CATReports;
import com.cisco.cat.reports.logging.LogAs;
import com.cisco.cat.reports.sel.CaptureScreen;
import com.cisco.cat.reports.sel.CaptureScreen.ScreenshotOf;
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

	/**
	 * To Click on Manage Button
	 * @return
	 */
	public HomePage clickManageBtn() {
		Button.click("Manage", manageBtn, driver);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	/**
	 * To check if the ACC Page is loaded
	 */
	public void checkPageLoaded(){
		if(Element.verify("ACC Application Header", accHeader, driver)){
			CATReports.add("Validating Application Centric Cloud page is loaded","Application Centric Cloud page should load","Application Centric Cloud page is loaded", LogAs.PASSED, null);
		}else{
			CATReports.add("Validating Application Centric Cloud page is loaded","Application Centric Cloud page should load","Application Centric Cloud page is not loaded", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

}
