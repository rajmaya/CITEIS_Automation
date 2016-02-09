package com.cisco.citeis.actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
public class Link {
	public static boolean click(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						element.click();
						blResult=true;
						ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link",strLogicalName+" link is clicked", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
					catch(Exception e){
						ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}	
				}
				else{
					ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}catch(Exception e){
			ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Assert.fail(e.getMessage());
		}
		}else{
			ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Assert.fail();
		}
		
		return blResult;
	}
	
	public static boolean JSClick(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						((JavascriptExecutor)driver).executeScript("arguments[0].click", element);
						blResult=true;
						ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link",strLogicalName+" link is clicked", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
					catch(Exception e){
						ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}	
				}
				else{
					ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));				
			}
			}catch(Exception e){
				ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			}
		}else{
			ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
						ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link",strLogicalName+" link is clicked", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
					catch(Exception e){
						ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}	
				}
				else{
					ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));				
			}
			}catch(Exception e){
				ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			}
		}else{
			ATUReports.add("Clicking "+strLogicalName+" link","To Click "+strLogicalName+" link","Unable to click "+strLogicalName+" link, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
		return blResult;
	}
	
	public static boolean mouseOver(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Mouse mouse=((HasInputDevices)driver).getMouse();
						mouse.mouseMove(((Locatable)element).getCoordinates());
						blResult=true;
						ATUReports.add("Verifying mouse over on "+strLogicalName+" link","To Verify mouse over on "+strLogicalName+" link","Verified mouse over on"+strLogicalName+" link", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
					catch(Exception e){
						ATUReports.add("Verifying mouse over on "+strLogicalName+" link","To Verify mouse over on "+strLogicalName+" link","Unable to verify mouse over on "+strLogicalName+" link"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}	
				}
				else{
					ATUReports.add("Verifying mouse over on "+strLogicalName+" link","To Verify mouse over on "+strLogicalName+" link","Unable to verify mouse over on "+strLogicalName+" link is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Verifying mouse over on "+strLogicalName+" link","To Verify mouse over on "+strLogicalName+" link","Unable to verify mouse over on "+strLogicalName+" link is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));				
			}
			}catch(Exception e){
				ATUReports.add("Verifying mouse over on "+strLogicalName+" link","To Verify mouse over on "+strLogicalName+" link","Unable to verify mouse over on "+strLogicalName+" link"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			}
		}else{
			ATUReports.add("Verifying mouse over on "+strLogicalName+" link","To Verify mouse over on "+strLogicalName+" link","Unable to verify mouse over on"+strLogicalName+" link, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
		return blResult;
	}
	
	public static boolean mouseOver(String strLogicalName,WebElement element,int inWait,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Mouse mouse=((HasInputDevices)driver).getMouse();
						mouse.mouseMove(((Locatable)element).getCoordinates());
						try{Thread.sleep(inWait*1000);}catch(Exception e){}
						ATUReports.add("Verifying mouse over on "+strLogicalName+" link","To Verify mouse over on "+strLogicalName+" link","Verified mouse over on"+strLogicalName+" link", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
					catch(Exception e){
						ATUReports.add("Verifying mouse over on "+strLogicalName+" link","To Verify mouse over on "+strLogicalName+" link","Unable to verify mouse over on "+strLogicalName+" link"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}	
				}
				else{
					ATUReports.add("Verifying mouse over on "+strLogicalName+" link","To Verify mouse over on "+strLogicalName+" link","Unable to verify mouse over on "+strLogicalName+" link is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Verifying mouse over on "+strLogicalName+" link","To Verify mouse over on "+strLogicalName+" link","Unable to verify mouse over on "+strLogicalName+" link is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));				
			}
			}catch(Exception e){
				ATUReports.add("Verifying mouse over on "+strLogicalName+" link","To Verify mouse over on "+strLogicalName+" link","Unable to verify mouse over on "+strLogicalName+" link"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			}
		}else{
			ATUReports.add("Verifying mouse over on "+strLogicalName+" link","To Verify mouse over on "+strLogicalName+" link","Unable to verify mouse over on"+strLogicalName+" link, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
		return blResult;
	}
	
	public static boolean verify(String strLogicalName, WebElement element,String strValue,WebDriver driver){
		boolean blResult=false;
		if(element.isDisplayed()){
			if(element.isEnabled()){
				try{
					String textValue=element.getText();
					if(textValue.equalsIgnoreCase(strValue)){
						blResult=true;
						ATUReports.add("Verifying "+strLogicalName+" link","To Verify value in "+strLogicalName+" link","Verified value "+strLogicalName+" link", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
				}
				catch(Exception e){
					ATUReports.add("Verifying value in "+strLogicalName+" link","To Verify value in "+strLogicalName+" link","Unable to verify "+strLogicalName+" link"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					Assert.fail(e.getMessage());
				}	
			}
			else{
				ATUReports.add("Verifying value in "+strLogicalName+" link","To Verify value in "+strLogicalName+" link","Unable to verify, "+strLogicalName+" link is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
		else{
			ATUReports.add("Verifying value in "+strLogicalName+" link","To Verify value in "+strLogicalName+" link","Unable to verify, "+strLogicalName+" link is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));				
		}
		return blResult;
	}
	
public static boolean verify(String strLogicalName, WebElement element,WebDriver driver){
		
		boolean blResult=false;
		
		if(element.isDisplayed()){
			try{
				blResult=true;
				ATUReports.add("Verifying value in "+strLogicalName+" link","To Verify value in "+strLogicalName+" link","Verified value "+strLogicalName+" link", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			catch(Exception e){
				ATUReports.add("Verifying value in "+strLogicalName+" link","To Verify value in "+strLogicalName+" link","Unable to verify "+strLogicalName+" link"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			}
		}
		else{
			ATUReports.add("Verifying value in "+strLogicalName+" link","To Verify value in "+strLogicalName+" link","Unable to verify, "+strLogicalName+" link is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}

public static boolean mouseDown(String strLogicalName,WebElement element,int inWait,WebDriver driver){
	boolean blResult=false;
	if(element.isDisplayed()){
		if(element.isEnabled()){
			try{
				Actions actions=new Actions(driver);
				actions.click(element).build().perform();
				try{Thread.sleep(inWait*1000);}catch(Exception e){}
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
