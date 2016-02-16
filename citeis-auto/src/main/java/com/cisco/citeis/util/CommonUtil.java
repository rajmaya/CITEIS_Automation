package com.cisco.citeis.util;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.cisco.citeis.actions.Link;
import com.cisco.citeis.actions.Sync;
import com.cisco.citeis.common.ApplicationConstants;
import com.cisco.citeis.customatu.reports.ATUReports;
import com.cisco.citeis.customatu.reports.logging.LogAs;
import com.cisco.citeis.customatu.reports.sel.CaptureScreen;
import com.cisco.citeis.customatu.reports.sel.CaptureScreen.ScreenshotOf;

public class CommonUtil {
	
	public static List<Object> validateNamePresent(WebDriver driver,String expectedName){
		//0 - if Row Present
		//1 - Selected Row Webelement
		//2 - row number
		List<Object> appData = new ArrayList<Object>();
		appData.add(0, false);
		Integer totalPages = Integer.parseInt(driver.findElement(ApplicationConstants.totalPages).getText());
		for(int pages=1;pages<= totalPages;pages++){
			List<WebElement> tableRows = driver.findElements(ApplicationConstants.tableRowNames);
			int i = 1;
			for (WebElement appName : tableRows) {
				if (appName.getText().equals(expectedName)) {
					appData.add(0, true);
					appData.add(1, appName);
					appData.add(2, i);
					ATUReports.add("Checking the application name in 'My Applications'",expectedName,appName.getText(),LogAs.PASSED, null);
					break;
				}
				i++;
			}
			if((Boolean) appData.get(0)){
				break;
			}else if(pages < totalPages){
				Link.click("Next", driver.findElement(ApplicationConstants.tableNext), driver);
				Sync.waitForSeconds(3, driver);
			}
		}
		
		if(!(Boolean) appData.get(0)){
			ATUReports.add("Checking the application name in 'My Applications'",expectedName,"Application not present",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Assert.fail("'"+expectedName+"' Application not present");
		}
		
		return appData;
	}
	
	public static boolean isOrderPending(int row,String appName,WebDriver driver){
		boolean isPending = true;
		WebElement appRow = driver.findElements(ApplicationConstants.tableRows).get(row-1);
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
