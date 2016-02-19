package com.cisco.citeis.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.Reporter;

import com.cisco.cat.reports.CATReports;
import com.cisco.cat.reports.logging.LogAs;
import com.cisco.cat.reports.sel.CaptureScreen;
import com.cisco.cat.reports.sel.CaptureScreen.ScreenshotOf;
import com.cisco.citeis.actions.Button;
import com.cisco.citeis.actions.Element;
import com.cisco.citeis.actions.Link;
import com.cisco.citeis.actions.Sync;
import com.cisco.citeis.actions.TextBox;
import com.cisco.citeis.common.ApplicationConstants;
import com.cisco.citeis.util.CommonUtil;

public class CurrentProfilePage {
	
	@FindBy(how = How.CSS,using = ApplicationConstants.profileName)
	private WebElement profileName;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.appNames)
	private List<WebElement> tableRows;
	
	@FindBy(how = How.XPATH,using = ApplicationConstants.epgLink)
	private WebElement epgLink;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.currentProfileTab)
	private WebElement currentProfileTab;
	
	@FindBy(how = How.XPATH,using = ApplicationConstants.instanceLink)
	private WebElement instanceLink ;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.addRow)
	private WebElement addRow ;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.popUpForm)
	private WebElement popUpClass ;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.popUpHeader)
	private WebElement popUpHeader ;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.epgNamePopup)
	private WebElement epgPopupName ;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.epgDescriptionPopup)
	private WebElement epgPopupDesc ;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.epgPopupSubmit)
	private WebElement epgPopupSubmit ;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.epgPopupCancel)
	private WebElement epgPopupCancel ;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.deleteRow)
	private WebElement deleteRow ;
	
	private WebDriver driver;

	public CurrentProfilePage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Validation of current profile and checking for EPG and Instances
	 * @param application
	 * @param expectedProfile
	 * @param expectedEPG
	 */
	public void validateCurrentProfile(String application,String expectedProfile,String expectedEPG){
		if(validateProfileDetails(expectedProfile)){
			Link.click("EPG", epgLink, driver);
			Sync.waitForSeconds(5, driver);
			if(tableRows.size() != 0){
				StringBuilder epgs = new StringBuilder();
				CommonUtil.validateNamePresent(driver, expectedEPG);
				for(WebElement epg : tableRows){
					epgs.append(epg.getText()+",");
				}
				CATReports.add("Validating if EPGs are displaying","EPGs are displayed in "+expectedProfile,epgs.substring(0, epgs.length()-1)+" EPGs are displayed for <b>'"+expectedProfile+"'</b>",LogAs.PASSED, null);
				Link.click("Current Profile", currentProfileTab, driver);
				Link.click("Instance", instanceLink, driver);
				Sync.waitForSeconds(3, driver);
				if(tableRows.size() != 0){
					StringBuilder instances = new StringBuilder();
					for(WebElement instance : tableRows){
						instances.append(instance.getText()+",");
					}
					CATReports.add("Validating if Instances are displaying","Instances are displayed in "+expectedProfile,instances.substring(0, instances.length()-1)+" are displayed for <b>'"+expectedProfile+"'<b>",LogAs.PASSED, null);
				}else{
					CATReports.add("Validating if Instances are displaying","Instances are displayed in "+expectedProfile,"Instances are not displayed for profile <b>'"+expectedProfile+"'<b>",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					Assert.fail("Instances are not displayed for profile <b>'"+expectedProfile+"'<b>");
				}
			}else{
				CATReports.add("Validating if EPGs are displaying","EPGs are displayed in "+expectedProfile,"EPGs are not displayed for profile <b>'"+expectedProfile+"'<b>",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail("EPGs are not displayed for profile <b>'"+expectedProfile+"'<b>");
			}
		}
	}
	
	/**
	 * To validate Profile Details displayed
	 * @param profileName
	 * @return
	 */
	private boolean validateProfileDetails(String profileName) {
		Reporter.log("Start of validation of '"+profileName+"' details under 'Current Profile' tab");
		boolean isValid = Element.verify("Profile Name", this.profileName, profileName, driver);
		Reporter.log("Start of validation of '"+profileName+"' details under 'Current Profile' tab");
		return isValid;
	}
	
	/**
	 * To create an EPG and validate if it is created successfully
	 * @param data
	 */
	public void createAndValidateEPG(Map<String, String> data){
		if(validateProfileDetails(data.get("Profile Name"))){
			Button.click("Add Row", addRow, driver);
			if(Element.verify("EPG PopUp", popUpClass, driver)){
				if(Element.verify("EPG PopUp title", popUpHeader, "Create EPG", driver)){
					TextBox.enterValue("EPG Name", epgPopupName, data.get("EPG Name"), driver);
					TextBox.enterValue("EPG Description", epgPopupDesc, data.get("EPG Description"), driver);
					Button.click("Submit", epgPopupSubmit, driver);
					Sync.waitForObject(driver, "EPG Table", tableRows.get(0), 20);
					List<Object> appData = CommonUtil.validateNamePresent(driver, data.get("EPG Name"));
					if((Boolean) appData.get(0)){
						CATReports.add("Checking if given EPG has been created","EPG with name "+data.get("EPG Name")+" should be created","EPG with name "+data.get("EPG Name")+" has been created",LogAs.PASSED, null);
						if(!CommonUtil.isOrderPending(Integer.parseInt(appData.get(2).toString()),data.get("EPG Name"), driver)){
							CATReports.add("Checking if given EPG creation is done","EPG with name "+data.get("EPG Name")+" should be created","EPG with name "+data.get("EPG Name")+" has been created",LogAs.PASSED, null);
						}/*else{
							ATUReports.add("Checking if given EPG creation is done","EPG with name "+data.get("EPG Name")+" should be created","EPG with name "+data.get("EPG Name")+" is still in pending",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						}*/
					}else{
						CATReports.add("Checking if given EPG has been created","EPG with name "+data.get("EPG Name")+" should be created","EPG with name "+data.get("EPG Name")+" has not been created",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
				}else{
					CATReports.add("Checking the EPG PopUp Header Title","Create EPG",popUpHeader.getText(),LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
		}
	}
	
	/**
	 * To delete an EPG and validate if it has been deleted successfully
	 * @param data
	 */
	public void deleteAndValidateEPGRemoved(Map<String, String> data){
		if(validateProfileDetails(data.get("Profile Name"))){
			List<Object> appData = CommonUtil.validateNamePresent(driver, data.get("EPG Name"));
			if(!CommonUtil.isOrderPending(Integer.parseInt(appData.get(2).toString()),data.get("EPG Name"), driver)){
				WebElement rowElement = (WebElement)appData.get(1);
				rowElement.findElement(ApplicationConstants.appRadioBtn).click();
				Button.click("Delete EPG", deleteRow, driver);
				Sync.waitForObject(driver, "EPG Table", tableRows.get(0), 20);
				appData = CommonUtil.validateNamePresent(driver, data.get("EPG Name"));
				if(!(Boolean) appData.get(0)){
					CATReports.add("Checking if given EPG has been deleted","EPG with name "+data.get("EPG Name")+" should be deleted","EPG with name "+data.get("EPG Name")+" has been deleted",LogAs.PASSED, null);
				}else{
					CATReports.add("Checking if given EPG has been deleted","EPG with name "+data.get("EPG Name")+" should be deleted","EPG with name "+data.get("EPG Name")+" has not been deleted",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
		}
	}

}
