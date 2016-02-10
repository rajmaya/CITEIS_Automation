package com.cisco.citeis.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

import com.cisco.citeis.actions.Element;
import com.cisco.citeis.actions.Link;
import com.cisco.citeis.common.ApplicationConstants;
import com.cisco.citeis.util.CommonUtil;
import com.cisco.citeis.util.LoggerUtil;

public class MyApplicationsPage {

	@FindBy(how = How.XPATH,using = ApplicationConstants.myApplicationshdr)
	private WebElement myApplicationshdr;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.appNames)
	private List<WebElement> appNames;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.appDetailsName)
	private WebElement appDetailsName;
	
	@FindBy(how = How.XPATH,using = ApplicationConstants.epgLink)
	private WebElement epgLink;
	
	@FindBy(how = How.XPATH,using = ApplicationConstants.noAppDisplay)
	private WebElement noAppDisplay;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.currentProfileTab)
	private WebElement currentProfileTab;
	
	@FindBy(how = How.XPATH,using = ApplicationConstants.instanceLink)
	private WebElement instanceLink ;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.profileName)
	private WebElement profileName;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.appRow)
	private List<WebElement> appRows;
	
	private WebDriver driver;
	
	//private Logger log = LoggerUtil.getLogger(getClass().getSimpleName());

	public MyApplicationsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Method to check the overall application flow from checking with application name to checking for instances created for the given profile
	 * @param application
	 * @param expectedProfile
	 * @param expectedEPG
	 */
	public void validateAppFlow(String application,String expectedProfile,String expectedEPG) {
		if (Element.verify("Application names table", appRows.get(0), driver)) {
			List<Object> appData = CommonUtil.validateNamePresent(driver, application);
			if((Boolean) appData.get(0)){
				if(!CommonUtil.isOrderPending(Integer.parseInt(appData.get(2).toString()),application,driver)){
					Link.click("Application", (WebElement)appData.get(1), driver);
					ApplicationDetailsPage applicationDetailsPage = PageFactory.initElements(driver, ApplicationDetailsPage.class);
					applicationDetailsPage.validateAppNProfile(application,expectedProfile);
					CurrentProfilePage currentProfilePage = PageFactory.initElements(driver, CurrentProfilePage.class);
					currentProfilePage.validateCurrentProfile(application,expectedProfile,expectedEPG);
				}
			}
		}else {
			ATUReports.add("Checking the applications", application,"No applications are displayed in 'My Applications'",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Assert.fail("No applications are displayed in 'My Applications'");
		}
	}
	
	/**
	 * Method to verify if no applications are displayed for any login
	 */
	public void validateNoApplicationDisplayed(){
		ATUReports.add("Validating if applications are not displayed", LogAs.INFO, null);
		Element.verify("Application", noAppDisplay, driver); 
	}

	/**
	 * Method to check if application is displayed in 'My Applications' page
	 * @param application
	 * @return
	 */
	public ApplicationDetailsPage validateApplication(String application){
		if (Element.verify("Application names table", appRows.get(0), driver)) {
			List<Object> appData = CommonUtil.validateNamePresent(driver, application);
			if((Boolean) appData.get(0)){
				if(!CommonUtil.isOrderPending(Integer.parseInt(appData.get(2).toString()),application,driver)){
					Link.click("Application", (WebElement)appData.get(1), driver);
				}
			}
		}else {
			ATUReports.add("Checking the applications", application,"No applications are displayed in 'My Applications'",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Assert.fail("No applications are displayed in 'My Applications'");
		}
		
		return PageFactory.initElements(driver, ApplicationDetailsPage.class);
	}

}
