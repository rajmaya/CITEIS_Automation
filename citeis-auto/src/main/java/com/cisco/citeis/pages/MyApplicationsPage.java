package com.cisco.citeis.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.Reporter;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

import com.cisco.citeis.actions.Element;
import com.cisco.citeis.actions.Link;
import com.cisco.citeis.actions.Sync;
import com.cisco.citeis.common.ApplicationConstants;
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
	
	@FindBy(how = How.ID,using = ApplicationConstants.totalAppPages)
	private WebElement totalAppPages;
	
	@FindBy(how = How.ID,using = ApplicationConstants.tableNext)
	private WebElement tableNext;
	
	private WebDriver driver;
	private Integer row = 0;
	private WebElement appName;
	
	private Logger log = LoggerUtil.getLogger(getClass().getSimpleName());

	public MyApplicationsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void validateAppFlow(String application,String expectedProfile,String expectedEPG) {
		boolean isProfilePresent = false;
		if (Element.verify("Application names table", appRows.get(0), driver)) {
			if(validateIfAppNamePresent(application)){
				if(!isOrderPending(row,application)){
					Link.click("Application", appName, driver);
					  if (validateApplicationsDetails(application)) {
						if (appNames.size() != 0) {
							ATUReports.add("Checking the Profiles for displayed for <b>'"+application+"'<b>","Profiles are displayed",appNames.size()+" Profiles are displayed",LogAs.PASSED, null);
							for (WebElement profile : appNames) {
							String profileName = profile.getText();
							if(profileName.equals(expectedProfile)){
							 isProfilePresent = true;	
							 Link.click("Profile", profile, driver);
							 ATUReports.add("Clicking on profile '"+profileName,"Profile name should be clicked, Clicked on profile",profileName+" profile is clicked",LogAs.PASSED, null);
							 if(validateProfileDetails(profileName)){
								Link.click("EPG", epgLink, driver);
								Sync.waitForSeconds(5, driver);
								if(appNames.size() != 0){
									StringBuilder epgs = new StringBuilder();
									validateEPGDisplayed(appNames,expectedEPG);
									for(WebElement epg : appNames){
										epgs.append(epg.getText()+",");
									}
									ATUReports.add("Validating if EPGs are displaying","EPGs are displayed in "+profileName,epgs.substring(0, epgs.length()-1)+" EPGs are displayed for <b>'"+profileName+"'</b>",LogAs.PASSED, null);
									Link.click("Current Profile", currentProfileTab, driver);
									Link.click("Instance", instanceLink, driver);
									Sync.waitForSeconds(3, driver);
									if(appNames.size() != 0){
										StringBuilder instances = new StringBuilder();
										for(WebElement instance : appNames){
											instances.append(instance.getText()+",");
										}
										ATUReports.add("Validating if Instances are displaying","Instances are displayed in "+profileName,instances.substring(0, instances.length()-1)+" are displayed for <b>'"+profileName+"'<b>",LogAs.PASSED, null);
									}else{
										ATUReports.add("Validating if Instances are displaying","Instances are displayed in "+profileName,"Instances are not displayed for profile <b>'"+profileName+"'<b>",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
										Assert.fail("Instances are not displayed for profile <b>'"+profileName+"'<b>");
									}
								}else{
									ATUReports.add("Validating if EPGs are displaying","EPGs are displayed in "+profileName,"EPGs are not displayed for profile <b>'"+profileName+"'<b>",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
									Assert.fail("EPGs are not displayed for profile <b>'"+profileName+"'<b>");
								}
							}
							break;
							}
						  }	
						}else{
							ATUReports.add("Checking the Profiles for displayed for <b>'"+application+"'<b>","Profiles are displayed","Profiles are not displayed for <b>'"+application+"'<b>",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
							Assert.fail("Profiles are not displayed for <b>'"+application+"'<b>");
						}
					}
				}
			}	
			if(!isProfilePresent){
				ATUReports.add("Checking the profile name for '"+application+"'",expectedProfile,"Profile not present",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail("'"+expectedProfile+"' Profile not present");
			}
		} else {
			ATUReports.add("Checking the applications", application,"No applications are displayed in 'My Applications'",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Assert.fail("No applications are displayed in 'My Applications'");
		}
	}
	
	private boolean validateIfAppNamePresent(String application){
		boolean isAppPresent = false;
		
		for(int pages=1;pages<= Integer.parseInt(totalAppPages.getText());pages++){
			int i = 1;
			for (WebElement appName : appNames) {
				if (appName.getText().equals(application)) {
					this.appName = appName;
					isAppPresent = true;
					row = i;
					ATUReports.add("Checking the application name in 'My Applications'",application,appName.getText(),LogAs.PASSED, null);
					break;
				}
				i++;
			}
			if(isAppPresent){
				break;
			}else if(pages < Integer.parseInt(totalAppPages.getText())){
				Link.click("Next", tableNext, driver);
				Sync.waitForSeconds(3, driver);
			}
		}
		
		if(!isAppPresent){
			ATUReports.add("Checking the application name in 'My Applications'",application,"Application not present",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			log.error(application+" Application not present");
			Assert.fail("'"+application+"' Application not present");
		}
		
		return isAppPresent;
	}
	
	private void validateEPGDisplayed(List<WebElement> epgs, String expectedEPG) {
		boolean isEPGPresent = false;
		for(WebElement epg : epgs){
			if(epg.getText().equals(expectedEPG)){
				isEPGPresent = true;
			}
		}
		if(isEPGPresent){
			ATUReports.add("Checking the EPG name",expectedEPG,"EPG present",LogAs.PASSED, null);
		}else{
			ATUReports.add("Checking the EPG name",expectedEPG,"EPG not present",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Assert.fail("'"+expectedEPG+"' EPG not present");
		}
	}

	public void validateNoApplicationDisplayed(){
		ATUReports.add("Validating if applications are not displayed", LogAs.INFO, null);
		Element.verify("Application", noAppDisplay, driver); 
	}

	private boolean validateApplicationsDetails(String application) {
		Reporter.log("Start of validation of '"+application+"' details under 'Details' tab");
		boolean isValid = Element.verify("Application Details", appDetailsName, application, driver);
		Reporter.log("End of validation of '"+application+"' details under 'Details' tab");
		return isValid;
	}
	
	private boolean validateProfileDetails(String profileName) {
		Reporter.log("Start of validation of '"+profileName+"' details under 'Current Profile' tab");
		boolean isValid = Element.verify("Profile Name", this.profileName, profileName, driver);
		Reporter.log("Start of validation of '"+profileName+"' details under 'Current Profile' tab");
		return isValid;
	}
	
	private boolean isOrderPending(int row,String appName){
		boolean isPending = true;
		WebElement appRow = appRows.get(row-1);
		String className = null;
		try{
		className = appRow.findElement(ApplicationConstants.appRadioBtn).getAttribute("class");
		}catch(StaleElementReferenceException e){
			className = appRow.findElement(ApplicationConstants.appPendingBtn).getAttribute("class");
		}catch(NoSuchElementException e){
			className = appRow.findElement(ApplicationConstants.appPendingBtn).getAttribute("class");
		}
		if(className.equals("ng-scope inProgress")){
			ATUReports.add("Checking the Application Approval status","Application "+appName+" should be approved","Application Order for <b>'"+appName+"'</b> is still pending for approval", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Assert.fail("Application Order for <b>'"+appName+"'</b> is still pending for approval");
		}else {
			isPending = false;
			ATUReports.add("Checking the Application Approval status","Application "+appName+" should be approved","Application Order for <b>'"+appName+"'</b> is approved", LogAs.PASSED, null);
		}
		return isPending;
	}

}
