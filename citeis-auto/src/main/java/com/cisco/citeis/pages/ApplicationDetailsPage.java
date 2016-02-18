package com.cisco.citeis.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.cisco.cat.reports.CATReports;
import com.cisco.cat.reports.logging.LogAs;
import com.cisco.cat.reports.sel.CaptureScreen;
import com.cisco.cat.reports.sel.CaptureScreen.ScreenshotOf;
import com.cisco.citeis.actions.Element;
import com.cisco.citeis.actions.Link;
import com.cisco.citeis.common.ApplicationConstants;

public class ApplicationDetailsPage {
	
	@FindBy(how = How.CSS,using = ApplicationConstants.appDetailsName)
	private WebElement appDetailsName;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.appNames)
	private List<WebElement> profileNames;
	
	private WebDriver driver;
	
	public ApplicationDetailsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * To check for the application details and profile gets displayed properly
	 * @param application
	 * @param expectedProfile
	 * @return
	 */
	public CurrentProfilePage validateAppNProfile(String application,String expectedProfile){
		boolean isProfilePresent = false;
		if (validateApplicationsDetails(application)) {
			if (profileNames.size() != 0) {
				CATReports.add("Checking the Profiles for displayed for <b>'"+application+"'<b>","Profiles are displayed",profileNames.size()+" Profiles are displayed",LogAs.PASSED, null);
				for (WebElement profile : profileNames) {
				String profileName = profile.getText();
				if(profileName.equals(expectedProfile)){
				 isProfilePresent = true;	
				 Link.click("Profile", profile, driver);
				 CATReports.add("Clicking on profile '"+profileName,"Profile name should be clicked, Clicked on profile",profileName+" profile is clicked",LogAs.PASSED, null);
				 break;
				}
				}
			} else {
				CATReports.add("Checking the Profiles for displayed for <b>'"+ application + "'<b>", "Profiles are displayed","Profiles are not displayed for <b>'" + application+ "'<b>", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail("Profiles are not displayed for <b>'" + application+ "'<b>");
			}
		 }
		if(!isProfilePresent){
			CATReports.add("Checking the profile name for '"+application+"'",expectedProfile,"Profile not present",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Assert.fail("'"+expectedProfile+"' Profile not present");
		}
		return PageFactory.initElements(driver, CurrentProfilePage.class);
	}
	
	/**
	 * Validates for correct Application details to be displayed
	 * @param application
	 * @return
	 */
	private boolean validateApplicationsDetails(String application) {
		Reporter.log("Start of validation of '"+application+"' details under 'Details' tab");
		boolean isValid = Element.verify("Application Details", appDetailsName, application, driver);
		Reporter.log("End of validation of '"+application+"' details under 'Details' tab");
		return isValid;
	}

}
