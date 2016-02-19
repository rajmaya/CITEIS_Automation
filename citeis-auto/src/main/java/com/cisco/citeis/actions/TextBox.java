package com.cisco.citeis.actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.cisco.cat.reports.CATReports;
import com.cisco.cat.reports.logging.LogAs;
import com.cisco.cat.reports.sel.CaptureScreen;
import com.cisco.cat.reports.sel.CaptureScreen.ScreenshotOf;

public class TextBox {
	
	public static boolean enterValue(String strLogicalName,WebElement element,String strValue,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						element.sendKeys(strValue);
						blResult=true;
						CATReports.add("Entering value in "+strLogicalName+" Textbox","To Enter in "+strLogicalName+" TextBox","Entered in "+strLogicalName+" TextBox", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE)); 
					}
					catch(Exception e){
						CATReports.add("Entering value in "+strLogicalName+" Textbox","To Enter in "+strLogicalName+" Textbox","Unable to Enter in, "+strLogicalName+" Textbox"+ "\n"+"Exception occurred:"+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}				
				}
				else{
					CATReports.add("Entering value in "+strLogicalName+"Textbox is disabled","To Enter in "+strLogicalName+" Textbox","Unable to Enter in,  "+strLogicalName+" Textbox is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				}
			}
			else{
				
				CATReports.add("Entering value in "+strLogicalName+"Textbox","To Enter in "+strLogicalName+" Textbox","Unable to Enter in, "+strLogicalName+" Textbox is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}catch(Exception e){
			CATReports.add("Entering in "+strLogicalName+" Textbox","To Enter in "+strLogicalName+" Testbox","Unable to Enter in, "+strLogicalName+" Textbox"+ "\n"+ "Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Assert.fail(e.getMessage());
		}
		}else{
			CATReports.add("Entering value in "+strLogicalName+"Textbox","To Enter in "+strLogicalName+" Textbox","Unable to Enter in, "+strLogicalName+" Textbox, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}	

	
	public static boolean verifyValue(String strLogicalName,WebElement element,String strValue,WebDriver driver){
		boolean blResult=false;
		if(element!= null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						String textValue=element.getAttribute("value");
						if(textValue.equalsIgnoreCase(strValue)){
							blResult=true;
							CATReports.add("Verifying value in "+strLogicalName+" Textbox","To Verify value in "+strLogicalName+" TextBox","Verified value "+strLogicalName+" TextBox", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						}
					}
					catch(Exception e){
						CATReports.add("Verifying value in "+strLogicalName+" Textbox","To Verify value in "+strLogicalName+" TextBox","Unable to verify, "+strLogicalName+" Textbox"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}

				}
				else{
					CATReports.add("Verifying value in "+strLogicalName+" Textbox","To Verify value in "+strLogicalName+" TextBox","Unable to verify, "+strLogicalName+" Textbox is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				CATReports.add("Verifying value in "+strLogicalName+" Textbox","To Verify value in "+strLogicalName+" TextBox","Unable to verify, "+strLogicalName+" Textbox is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			}catch(Exception e){
				CATReports.add("Verifying value in "+strLogicalName+" Textbox","To Verify value in "+strLogicalName+" TextBox","Unable to verify, "+strLogicalName+" Textbox"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());	
			}
		}else{

			CATReports.add("Verifying value in "+strLogicalName+" Textbox","To Verify value in "+strLogicalName+" TextBox","Unable to verify, "+strLogicalName+" Textbox,element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}
	
	public static boolean clearValue(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						element.clear();
						blResult=true;

					}
					catch(Exception e){
						CATReports.add("Clearing value "+strLogicalName+" Textbox","To Clear value in "+strLogicalName+" TextBox","Unable to Clear, "+strLogicalName+" Textbox"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}

				}
				else{
				}
			}
			else{
			}
		}catch(Exception e){
			CATReports.add("Clearing value "+strLogicalName+" Textbox","To Clear value in "+strLogicalName+" TextBox","Unable to Clear, "+strLogicalName+" Textbox"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Assert.fail(e.getMessage());
		}
		}
		return blResult;
	}
	
	public static boolean JSEnterValue(String strLogicalName,WebDriver driver,WebElement element,String strValue){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						JavascriptExecutor javascriptExecutor=((JavascriptExecutor)driver);
						javascriptExecutor.executeScript("arguments[0].value=arguments[1]", element,strValue);
						element.sendKeys(strValue);
						blResult=true;
						CATReports.add("Entered value in "+strLogicalName+" Textbox","To Enter value in "+strLogicalName+" TextBox","Entered value "+strLogicalName+" TextBox", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					}
					catch(Exception e){
						CATReports.add("Entering value in "+strLogicalName+" Textbox","To Enter in "+strLogicalName+" Textbox","Unable to Enter in, "+strLogicalName+" Textbox"+ "\n"+"Exception occurred:"+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}				
				}
				else{
					
					CATReports.add("Entering value in "+strLogicalName+"Textbox is disabled","To Enter in "+strLogicalName+" Textbox","Unable to Enter in,  "+strLogicalName+" Textbox is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				}
			}
			else{

				CATReports.add("Entering value in "+strLogicalName+"Textbox","To Enter in "+strLogicalName+" Textbox","Unable to Enter in, "+strLogicalName+" Textbox is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}catch(Exception e){
			CATReports.add("Entering value in "+strLogicalName+" Textbox","To Enter in "+strLogicalName+" Textbox","Unable to Enter in, "+strLogicalName+" Textbox"+ "\n"+"Exception occurred:"+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Assert.fail(e.getMessage());	
	       }
		}else{
			CATReports.add("Entering value in "+strLogicalName+"Textbox","To Enter in "+strLogicalName+" Textbox","Unable to Enter in, "+strLogicalName+" Textbox, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}
	
	
	/*public static boolean enterKeys(String strLogicalName,WebElement element,Keys key,WebDriver driver){
	boolean blResult=false;
	
	if(element.isDisplayed()){
		if(element.isEnabled()){
			try{
				element.sendKeys(key);
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

}
