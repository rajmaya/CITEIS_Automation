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

public class Radiobutton {
	public static boolean select(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						element.click();
						blResult=true;
						ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Selected "+strLogicalName+" radiobutton", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
					catch(Exception e){
						ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select, "+strLogicalName+" radiobutton"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}
				}
				else{
					ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select value, "+strLogicalName+" radiobutton is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select value, "+strLogicalName+" radiobutton is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			}catch(Exception e){
				ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select value, "+strLogicalName+" radiobutton"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			  }
		}else{
			
			ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select value, "+strLogicalName+" radiobutton, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
		return blResult;
	}
	
	public static boolean JSSelect(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
		if(element.isDisplayed()){
			if(element.isEnabled()){
				try{
					JavascriptExecutor javaScriptExecutor=((JavascriptExecutor)driver);
					javaScriptExecutor.executeScript("arguments[0].click",element);
					blResult=true;
					ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Selected "+strLogicalName+" radiobutton", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
				catch(Exception e){
					ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select, "+strLogicalName+" radiobutton"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					Assert.fail(e.getMessage());
				}
			}
			else{
				ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select value, "+strLogicalName+" radiobutton is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
		else{
			ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select value, "+strLogicalName+" radiobutton is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));;
		}
		}catch(Exception e){
			ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select value, "+strLogicalName+" radiobutton"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Assert.fail(e.getMessage());
		  }
	}else{
		
		ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select value, "+strLogicalName+" radiobutton, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));;
	}
		return blResult;
	}

	public static boolean mouseClick(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
			if(element.isEnabled()){
				try{
					Mouse mouse=((HasInputDevices)driver).getMouse();
					mouse.mouseDown(((Locatable)element).getCoordinates());
					try{Thread.sleep(500);}catch(Exception e){}
					mouse.mouseUp(((Locatable)element).getCoordinates());
					blResult=true;
					ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Selected "+strLogicalName+" radiobutton", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
				catch(Exception e){
					ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select, "+strLogicalName+" radiobutton"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					Assert.fail(e.getMessage());
				}
			}
			else{
				ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select value, "+strLogicalName+" radiobutton is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
		else{
			ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select value, "+strLogicalName+" radiobutton is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
			}catch(Exception e){
		  }
	}else{
		
		ATUReports.add("Selecting Value in "+strLogicalName+" radiobutton","To Select value in "+strLogicalName+" radiobutton","Unable to select value, "+strLogicalName+" radiobutton, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));;
	}
		return blResult;
	}
	
	public static boolean verify(String strLogicalName,WebElement element,WebDriver driver){		
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				try{
					element.click();
					blResult=true;
					ATUReports.add("Verifying value in "+strLogicalName+" radiobutton","To Verify value in "+strLogicalName+" radiobutton","Verified value "+strLogicalName+" radiobutton", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
				catch(Exception e){
					ATUReports.add("Verifying value in "+strLogicalName+" radiobutton","To Verify value in "+strLogicalName+" radiobutton","Unable to verify, "+strLogicalName+" radiobutton"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					Assert.fail(e.getMessage());
				}
			}
			else{
				ATUReports.add("Verifying value in "+strLogicalName+" radiobutton","To Verify value in "+strLogicalName+" radiobutton","Unable to verify, "+strLogicalName+" radiobutton is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			}catch(Exception e){
				ATUReports.add("Verifying value in "+strLogicalName+" radiobutton","To Verify value in "+strLogicalName+" radiobutton","Unable to verify, "+strLogicalName+" radiobutton"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			  }
		}else{
			
			ATUReports.add("Verifying value in "+strLogicalName+" radiobutton","To Verify value in "+strLogicalName+" radiobutton","Unable to verify, "+strLogicalName+" radiobutton,element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}
	
	public static boolean isSelected(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult = false;
		if(element!=null){
			try{
			if (element.isDisplayed()) {
				try {
					if (element.isSelected()) {
						blResult = true;
						ATUReports.add("Verifying value in "+strLogicalName+" radiobutton","To Verify value in "+strLogicalName+" radiobutton","Verified value "+strLogicalName+" radiobutton", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
				} catch (Exception e) {
					ATUReports.add("Verifying value in "+strLogicalName+" radiobutton","To Verify value in "+strLogicalName+" radiobutton","Unable to verify, "+strLogicalName+" radiobutton"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					Assert.fail(e.getMessage());
				}
			} else {
				ATUReports.add("Verifying value in "+strLogicalName+" radiobutton","To Verify value in "+strLogicalName+" radiobutton","Unable to verify, "+strLogicalName+" radiobutton is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			}catch(Exception e){
				ATUReports.add("Verifying value in "+strLogicalName+" radiobutton","To Verify value in "+strLogicalName+" radiobutton","Unable to verify, "+strLogicalName+" radiobutton"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			  }
		}else{
			ATUReports.add("Verifying value in "+strLogicalName+" radiobutton","To Verify value in "+strLogicalName+" radiobutton","Unable to verify, "+strLogicalName+" radiobutton,element is nulll", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
}
