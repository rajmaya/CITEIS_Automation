package com.cisco.citeis.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cisco.cat.reports.CATReports;
import com.cisco.cat.reports.logging.LogAs;
import com.cisco.cat.reports.sel.CaptureScreen;
import com.cisco.cat.reports.sel.CaptureScreen.ScreenshotOf;

public class Checkbox {	
	public static boolean check(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult = false;
		if (element != null) {
			try {
				if (element.isDisplayed()) {
					if (element.isEnabled()) {
						if (!element.isSelected()) {
							element.click();
							blResult = true;
							CATReports.add("Checking the "+strLogicalName+ " checkBox",strLogicalName+ " checkBox should be checked",strLogicalName+ " checkBox is checked",LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						}
					} else {
						CATReports.add("Checking the "+strLogicalName+ " checkBox",strLogicalName+ " checkBox should be checked","Unable to check" + strLogicalName+ " checkBox is disabled", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
				} else {
					CATReports.add("Checking the "+strLogicalName+ " checkBox",strLogicalName+ " checkBox should be checked","Unable to check" + strLogicalName+ " checkBox is not displayed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch (Exception e) {
				CATReports.add("Checking the "+strLogicalName+ " checkBox",strLogicalName+ " checkBox should be checked","Unable to check" + strLogicalName + " checkBox"+ "\n" + "Exception occurred: " + e.getMessage(),LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} else {
			CATReports.add("Checking the "+strLogicalName+ " checkBox",strLogicalName+ " checkBox should be checked","Unable to check" + strLogicalName+ " checkBox, element is null", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		}
		return blResult;
	}

	public static boolean unCheck(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if (element != null) {
			try {
				if (element.isDisplayed()) {
					if (element.isEnabled()) {
						if (element.isSelected()) {
							element.click();
							blResult = true;
							CATReports.add("UnChecking the "+strLogicalName+ " checkBox",strLogicalName+ " checkBox should be unchecked",strLogicalName+ " checkBox is unchecked",LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						}

					} else {
						CATReports.add("UnChecking the "+strLogicalName+ " checkBox",strLogicalName+ " checkBox should be unchecked","Unable to uncheck" + strLogicalName+ " checkBox is disabled", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
				} else {
					CATReports.add("UnChecking the "+strLogicalName+ " checkBox",strLogicalName+ " checkBox should be unchecked","Unable to uncheck" + strLogicalName+ " checkBox is not displayed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch (Exception e) {
				CATReports.add("UnChecking the "+strLogicalName+ " checkBox",strLogicalName+ " checkBox should be unchecked","Unable to uncheck" + strLogicalName + " checkBox"+ "\n" + "Exception occurred: "+ e.getMessage(), LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} else {
			CATReports.add("UnChecking the "+strLogicalName+ " checkBox",strLogicalName+ " checkBox should be unchecked","Unable to uncheck" + strLogicalName+ " checkBox, element is null", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		}
		return blResult;
	}

	public static boolean isChecked(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult = false;
		if (element != null) {
			try {
				if (element.isDisplayed()) {
					if (element.isSelected()) {
						blResult = true;
						CATReports.add("Checking if the " + strLogicalName+ " checkBox is checked", strLogicalName+ " checkBox should be checked", strLogicalName+ " checkBox is in checked status",LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
				} else {
					CATReports.add("Checking if the " + strLogicalName+ " checkBox is checked", strLogicalName+ " checkBox should be checked","Unable to verify checked status " + strLogicalName+ " checkbox does not exist", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch (Exception e) {
				CATReports.add("Checking if the " + strLogicalName+ " checkBox is checked", strLogicalName+ " checkBox should be checked", "Unable to verify"+ strLogicalName + " checkbox checked status" + "\n"+ "Exception occurred: " + e.getMessage(),LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} else {
			CATReports.add("Checking if the " + strLogicalName+ " checkBox is checked", strLogicalName+ " checkBox should be checked","Unable to verify checked status " + strLogicalName+ " checkbox, element is null", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}

	public static boolean isUnchecked(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult = false;
		if (element != null) {
			try {
				if (element.isDisplayed()) {
					if (!element.isSelected()) {
						blResult = true;
						CATReports.add("Checking if the " + strLogicalName+ " checkBox is unchecked", strLogicalName+ " checkBox should be unchecked",strLogicalName+ " checkBox is in unchecked status",LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
				} else {
					CATReports.add("Checking if the " + strLogicalName+ " checkBox is unchecked", strLogicalName+ " checkBox should be unchecked","Unable to verify unchecked status "+ strLogicalName+ " checkbox does not exist", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch (Exception e) {
				CATReports.add("Checking if the " + strLogicalName+ " checkBox is unchecked", strLogicalName+ " checkBox should be unchecked", "Unable to verify"+ strLogicalName + " checkbox unchecked status" + "\n"+ "Exception occurred: " + e.getMessage(),LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} else {
			CATReports.add("Checking if the " + strLogicalName+ " checkBox is unchecked", strLogicalName+ " checkBox should be unchecked","Unable to verify unchecked status " + strLogicalName+ " checkbox, element is null", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}

	public static boolean verify(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult = false;
		if (element != null) {
			try {
				if (element.isDisplayed()) {
					blResult = true;
					CATReports.add("Verifying of " + strLogicalName+ " checkbox", strLogicalName+ " checkbox should be displayed", "Verified"+ strLogicalName + " checkBox", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				} else {
					CATReports.add("Verifying of " + strLogicalName+ " checkbox", strLogicalName+ " checkbox should be displayed","Unable to verify unchecked status "+ strLogicalName+ " checkbox does not exist", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch (Exception e) {
				CATReports.add("Verifying of " + strLogicalName + " checkbox",strLogicalName + " checkbox should be displayed","Unable to verify" + strLogicalName + " checkbox"+ "\n" + "Exception occurred: "+ e.getMessage(), LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} else {
			CATReports.add("Verifying of " + strLogicalName + " checkbox",strLogicalName + " checkbox should be displayed","Unable to verify unchecked status " + strLogicalName+ " checkbox, element is null", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}

	public static boolean isEnabled(WebElement element, String strLogicalName,WebDriver driver){
		boolean blResult=false;
		if(element.isDisplayed()){
			if(element.isEnabled()){
				try{
					blResult=true;
				}
				catch(Exception e){
				}
			}
			else{
			}
		}
		else{
		}
		return blResult;
	}
	public static boolean isDisabled(WebElement element, String strLogicalName,WebDriver driver){
		boolean blResult=false;
		if(element.isDisplayed()){
			if(!element.isEnabled()){
				try{
					blResult=true;
				}
				catch(Exception e){
				}
			}
			else{
			}
		}
		else{
		}
		return blResult;
	}
}
