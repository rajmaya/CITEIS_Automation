package com.cisco.citeis.actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;

import com.cisco.citeis.customatu.reports.ATUReports;
import com.cisco.citeis.customatu.reports.logging.LogAs;
import com.cisco.citeis.customatu.reports.sel.CaptureScreen;
import com.cisco.citeis.customatu.reports.sel.CaptureScreen.ScreenshotOf;
public class Button {
	public static boolean click(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					element.click();
					blResult=true;
					ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Clicked "+strLogicalName+" Button", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
				else{
					ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Unable to click,  "+strLogicalName+" Button is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Unable to click, "+strLogicalName+" Button is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			}catch(Exception e){
				ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Unable to click "+strLogicalName+" Button \n Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			}
		}else{
			ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Unable to click "+strLogicalName+" Button, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}
   
	public static boolean mouseClick(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if (element != null) {
			try {
				if (element.isDisplayed()) {
					if (element.isEnabled()) {
						/*int y = ((Locatable)element).getCoordinates().onPage().getY();
					    ((JavascriptExecutor)driver). executeScript("window.scrollBy(0,"+y+");");
					    try{Thread.sleep(1000);}catch(Exception e){}
					    Actions actions=new Actions(driver);
						actions.click(element).build().perform();*/
						Mouse mouse = ((HasInputDevices) driver).getMouse();
						mouse.mouseDown(((Locatable) element).getCoordinates());
						try {
							Thread.sleep(500);
						} catch (Exception e) {
						}
						mouse.mouseUp(((Locatable) element).getCoordinates());
						blResult = true;
						ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Clicked " + strLogicalName + " Button",	LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					} else {
						ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Unable to click,  " + strLogicalName+ " Button is disabled", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
				} else {
					ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Unable to click,  " + strLogicalName+ " Button is not displayed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch (Exception e) {
				ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Unable to click " + strLogicalName + " Button"+ "\n" + "Exception occurred: " + e.getMessage(),LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} else {
			ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Unable to click " + strLogicalName+ " Button, element is null", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}
	
   public static boolean JSClick(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult = false;
		if (element != null) {
			try {
				if (element.isDisplayed()) {
					if (element.isEnabled()) {
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].click", element);
						blResult = true;
						ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Clicked " + strLogicalName + " Button",LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					} else {
						ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Unable to click,  " + strLogicalName+ " Button is disabled", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
				} else {
					ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Unable to click,  " + strLogicalName+ " Button is not displayed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch (Exception e) {
				ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Unable to click " + strLogicalName + " Button"+ "\n" + "Exception occurred: " + e.getMessage(),LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} else {
			ATUReports.add("Clicking on "+strLogicalName+" Button","To click on "+strLogicalName+" Button","Unable to click " + strLogicalName+ " Button, element is null", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
   }
   
	public static boolean verifyText(String strLogicalName,WebElement element,String strValue,WebDriver driver){
		boolean blResult=false;
		try {
			if (element.isDisplayed()) {
				String btnName = element.getText();
				if (btnName.equalsIgnoreCase(strValue)) {
					blResult = true;
					ATUReports.add("Verifying Text of " + strLogicalName + " Button",strValue, btnName, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} else {
				ATUReports.add("Verifying Text of " + strLogicalName+ " button, Element is not displayed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (Exception e) {
			ATUReports.add("Verifying Text of " + strLogicalName + " Button",strValue,"Unable to verify " + strLogicalName + " button"+ "\n" + "Exception occurred: " + e.getMessage(),LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}
	
	public static boolean verify(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult = false;
		if (element != null) {
			try {
				if (element.isDisplayed()) {
					blResult = true;
					ATUReports.add("Verifying of " + strLogicalName + " Button",strLogicalName + " button should be displayed","Verified " + strLogicalName + " Button",LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				} else {
					ATUReports.add("Verifying of " + strLogicalName + " Button",strLogicalName + " button should be displayed","Unable to verify, " + strLogicalName+ " button is not displayed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch (Exception e) {
				ATUReports.add("Verifying of " + strLogicalName + " Button",strLogicalName + " button should be displayed","Unable to verify " + strLogicalName + " button"+ "\n" + "Exception occurred: " + e.getMessage(),LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} else {
			ATUReports.add("Verifying of " + strLogicalName + " Button",strLogicalName + " button should be displayed","Unable to verify, " + strLogicalName+ " button, element is null", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
