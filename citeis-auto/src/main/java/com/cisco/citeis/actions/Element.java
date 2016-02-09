package com.cisco.citeis.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class Element {
	public static boolean verify(String strLogicalName,WebElement element,String strValue,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
						String textValue=element.getText();
						if(textValue.equalsIgnoreCase(strValue)){
							blResult=true;
							ATUReports.add("Verifying text of "+strLogicalName,"To verify text '"+strValue+"'","Verified text"+strValue, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						}
				}
				else{
					ATUReports.add("Verifying text of "+strLogicalName,"To verify text '"+strValue+"'","Unable to verify "+strLogicalName+" element, element is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Verifying text of "+strLogicalName,"To verify text '"+strValue+"'","Unable to verify "+strLogicalName+" element, element is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}		
			}
			catch(Exception e){
				ATUReports.add("Verifying text of "+strLogicalName,"To verify text '"+strValue+"'","Unable to verify "+strLogicalName+" \n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}else{
			ATUReports.add("Verifying text of "+strLogicalName,"To verify text '"+strValue+"'","Unable to verify "+strLogicalName+" element, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

		return blResult;
	}

	public static boolean mouseOver(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
						Mouse mouse=((HasInputDevices)driver).getMouse();
						mouse.mouseMove(((Locatable)element).getCoordinates());
						blResult=true;
						ATUReports.add("Verifying mouse over of "+strLogicalName,"To verify mouse over of "+strLogicalName,"Verified "+strLogicalName+" element", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
				else{
					ATUReports.add("Verifying mouse over of "+strLogicalName,"To verify mouse over of "+strLogicalName,"Unable to verify "+strLogicalName+" element, element is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Verifying mouse over of "+strLogicalName,"To verify mouse over of "+strLogicalName,"Unable to verify "+strLogicalName+" element, element is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			}				
			catch(Exception e){
				ATUReports.add("Verifying mouse over of "+strLogicalName,"To verify mouse over of "+strLogicalName,"Unable to verify "+strLogicalName+" element"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
		else{
			ATUReports.add("Verifying mouse over of "+strLogicalName,"To verify mouse over of "+strLogicalName,"Unable to verify "+strLogicalName+" element, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

		return blResult;
	}

	public static boolean mouseOver(String strLogicalName,WebElement element,int inWait,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
						Mouse mouse=((HasInputDevices)driver).getMouse();
						mouse.mouseMove(((Locatable)element).getCoordinates());
						try{Thread.sleep(inWait*1000);}catch(Exception e){}
						blResult=true;
						ATUReports.add("Verifying mouse over of "+strLogicalName,"To verify mouse over of "+strLogicalName,"Verified "+strLogicalName+" element", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
				else{
					ATUReports.add("Verifying mouse over of "+strLogicalName,"To verify mouse over of "+strLogicalName,"Unable to verify "+strLogicalName+" element, element is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Verifying mouse over of "+strLogicalName,"To verify mouse over of "+strLogicalName,"Unable to verify "+strLogicalName+" element, element is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}	
			}				
			catch(Exception e){
				ATUReports.add("Verifying mouse over of "+strLogicalName,"To verify mouse over of "+strLogicalName,"Unable to verify "+strLogicalName+" element"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
		else{
			ATUReports.add("Verifying mouse over of "+strLogicalName,"To verify mouse over of "+strLogicalName,"Unable to verify "+strLogicalName+" element, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

		return blResult;
	}

	public static boolean verify(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
				if(element.isDisplayed()){
						blResult=true;
						ATUReports.add("Verifying if "+strLogicalName+ " is displayed",strLogicalName+" should be displayed","Verified "+strLogicalName, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
				else{
					ATUReports.add("Verifying if "+strLogicalName+ " is displayed",strLogicalName+" should be displayed","Unable to verify "+strLogicalName+", element is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch(Exception e){
				ATUReports.add("Verifying if "+strLogicalName+ " is displayed",strLogicalName+" should be displayed","Unable to verify "+strLogicalName+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail();
			}
		}
		else{
			ATUReports.add("Verifying if "+strLogicalName+ " is displayed",strLogicalName+" should be displayed","Unable to verify "+strLogicalName+" , element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

		return blResult;
	}

	/*public static boolean verify(String strLogicalName,WebDriver driver,String strLocatorType,String strLocatorValue){
		boolean blResult=false;

		WebElement element=Object.getElement(driver, strLocatorType, strLocatorValue);

		if(!(element==null)){
			if(element.isDisplayed()){
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
	}*/

	public static boolean notVerify(String strLogicalName,WebDriver driver,String strLocatorType,String strLocatorValue){
		boolean blResult=false;

		WebElement element=Object.getElement(driver, strLocatorType, strLocatorValue);

		if(element==null){
		}
		else{
		}

		return blResult;
	}
}
