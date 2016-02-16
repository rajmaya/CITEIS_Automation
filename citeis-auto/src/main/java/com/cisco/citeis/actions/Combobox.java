package com.cisco.citeis.actions;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.cisco.citeis.customatu.reports.ATUReports;
import com.cisco.citeis.customatu.reports.logging.LogAs;
import com.cisco.citeis.customatu.reports.sel.CaptureScreen;
import com.cisco.citeis.customatu.reports.sel.CaptureScreen.ScreenshotOf;

public class Combobox {

	public static boolean selectItemByVisbleText(String strLogicalName,WebElement element,String strValue,WebDriver driver){
		boolean blResult=false;
		if (element != null) {
			try {
				if (element.isDisplayed()) {
					if (element.isEnabled()) {
						Select sel = new Select(element);
						sel.selectByVisibleText(strValue);
						blResult = true;
						ATUReports.add("Selecting " + strValue + " text in "+ strLogicalName + " comboBox","Should select " + strValue + " text in "+ strLogicalName + " comboBox","Selected " + strValue + " text in "+ strLogicalName + " comboBox",LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					} else {
						ATUReports.add("Selecting " + strValue + " text in "+ strLogicalName + " comboBox","Should select " + strValue + " text in "+ strLogicalName + " comboBox","Unable to select " + strValue + " text in "+ strLogicalName+ " comboBox, is disabled",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
				} else {
					ATUReports.add("Selecting " + strValue + " text in "+ strLogicalName + " comboBox", "Should select "+ strValue + " text in " + strLogicalName+ " comboBox", "Unable to select " + strValue+ " text in " + strLogicalName+ " comboBox, is not displayed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch (Exception e) {
				ATUReports.add("Selecting " + strValue + " text in " + strLogicalName+ " comboBox","Should select " + strValue + " text in "+ strLogicalName + " comboBox","Unable to select " + strValue + " text in "+ strLogicalName+ " comboBox \n Exception occurred: "+ e.getMessage(), LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} else {
			ATUReports.add("Selecting " + strValue + " text in "+ strLogicalName + " comboBox", "Should select " + strValue+ " text in " + strLogicalName + " comboBox","Unable to select " + strValue + " text in "+ strLogicalName + " comboBox, element is null",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}

	public static boolean selectItemByIndex(String strLogicalName,WebElement element,int itemIndex,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
						Select sel = new Select(element);
						sel.selectByIndex(itemIndex);
						blResult=true;
						ATUReports.add("Selecting " + itemIndex + " value in "+ strLogicalName + " comboBox", "Should select " + itemIndex+ " value in " + strLogicalName + " comboBox","Selected "+itemIndex+" value in "+strLogicalName+" comboBox", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
				else{
					ATUReports.add("Selecting " + itemIndex + " value in "+ strLogicalName + " comboBox", "Should select " + itemIndex+ " value in " + strLogicalName + " comboBox","Unable to select "+itemIndex+" value in "+strLogicalName+" comboBox, is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Selecting " + itemIndex + " value in "+ strLogicalName + " comboBox", "Should select " + itemIndex+ " value in " + strLogicalName + " comboBox","Unable to select "+itemIndex+" value in "+strLogicalName+" comboBox, is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		 }
		 catch(Exception e){
			ATUReports.add("Selecting " + itemIndex + " value in "+ strLogicalName + " comboBox", "Should select " + itemIndex+ " value in " + strLogicalName + " comboBox","Unable to select "+itemIndex+" value in "+strLogicalName+" comboBox"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		 }
		}else{
			ATUReports.add("Selecting " + itemIndex + " value in "+ strLogicalName + " comboBox", "Should select " + itemIndex+ " value in " + strLogicalName + " comboBox","Unable to select "+itemIndex+" value in "+strLogicalName+" comboBox, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}

	public static boolean selectFirstItem(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					Select sel = new Select(element);
					sel.selectByIndex(1);
					blResult=true;
					ATUReports.add("Selecting first value in "+strLogicalName+" combobox","First value in "+strLogicalName+" combobox should be selected","Selected first value in "+strLogicalName+" combobox", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
				else{
					ATUReports.add("Selecting first value in "+strLogicalName+" combobox","First value in "+strLogicalName+" combobox should be selected","Selected first value in "+strLogicalName+" combobox","Unable to select value,  "+strLogicalName+" combobox is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Selecting first value in "+strLogicalName+" combobox","First value in "+strLogicalName+" combobox should be selected","Selected first value in "+strLogicalName+" combobox","Unable to select value,  "+strLogicalName+" combobox is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		  }catch(Exception e){
			ATUReports.add("Selecting first value in "+strLogicalName+" combobox","First value in "+strLogicalName+" combobox should be selected","Selected first value in "+strLogicalName+" combobox","Unable to select first value in "+strLogicalName+" combobox"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}	
		}else{
			ATUReports.add("Selecting first value in "+strLogicalName+" combobox","First value in "+strLogicalName+" combobox should be selected","Selected first value in "+strLogicalName+" combobox","Unable to select value in "+strLogicalName+" combobox, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}

	public static boolean selectLastItem(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;

		int size =0;
		if(element!=null){
		try{	
		 if(element.isDisplayed()){
			if(element.isEnabled()){
					Select sel = new Select(element);
					sel.selectByIndex(sel.getOptions().size());
					blResult=true;
					ATUReports.add("Selecting last value in "+strLogicalName+" combobox","Last value in "+strLogicalName+" combobox should be selected","Selected last value in "+strLogicalName+" combobox", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Selecting last value in "+strLogicalName+" combobox","Last value in "+strLogicalName+" combobox should be selected","Unable to select value,  "+strLogicalName+" combobox is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		   }
		  else{
				ATUReports.add("Selecting last value in "+strLogicalName+" combobox","Last value in "+strLogicalName+" combobox should be selected","Unable to select value,  "+strLogicalName+" combobox is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
		catch(Exception e){
			ATUReports.add("Selecting last value in "+strLogicalName+" combobox","Last value in "+strLogicalName+" combobox should be selected","Unable to select last value in "+strLogicalName+" combobox"+
					"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}	 
	}else{
		ATUReports.add("Selecting last value in "+strLogicalName+" combobox","Last value in "+strLogicalName+" combobox should be selected","Unable to select value in "+strLogicalName+" combobox, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}
		return blResult;
	}

	public static boolean selectRandomItem(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		int size =0;
		int index = 0;
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Select sel = new Select(element);
						index=getRandomIndex(sel.getOptions().size());
						sel.selectByIndex(index);
						blResult=true;
						ATUReports.add("Selecting random value in "+strLogicalName+" combobox","Random value in "+strLogicalName+" combobox should be selected","Selected random value in "+strLogicalName+" combobox", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
					catch(Exception e){
						ATUReports.add("Selecting random value in "+strLogicalName+" combobox","Random value in "+strLogicalName+" combobox should be selected","Unable to select last value in "+strLogicalName+" combobox"+
								"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}

				}
				else{
					ATUReports.add("Selecting random value in "+strLogicalName+" combobox","Random value in "+strLogicalName+" combobox should be selected","Unable to select value,  "+strLogicalName+" combobox is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Selecting random value in "+strLogicalName+" combobox","Random value in "+strLogicalName+" combobox should be selected","Unable to select value,  "+strLogicalName+" combobox is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}else{
			ATUReports.add("Selecting random value in "+strLogicalName+" combobox","Random value in "+strLogicalName+" combobox should be selected","Unable to select value in "+strLogicalName+" combobox, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
		return blResult;
	}

	public static boolean isEnabled(WebElement element , String strLogicalName,WebDriver driver){
		boolean blResult=false;
		if(element.isDisplayed()){
			if(element.isEnabled()){
				try{
					blResult = true;
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

	public static boolean isDisabled(WebElement element , String strLogicalName,WebDriver driver){
		boolean blResult=false;
		if(element.isDisplayed()){
			if(!element.isEnabled()){
				try{
					blResult = true;
				}
				catch(Exception e){
				}

			}
			else{
				System.out.println();
			}
		}
		else{
		}
		return blResult;
	}

	public static int getRandomIndex(int size){
		int random =new Random().nextInt(size+1);
		if(random==0){random++;}
		return random;
	}

	public static boolean verifySelectedItem(String strLogicalName,WebElement element,String strValue,WebDriver driver){
		boolean blResult=false;
		if(element.isDisplayed()){
			try{
				Select sel = new Select(element);
				if(sel.getFirstSelectedOption().getText().equalsIgnoreCase(strValue)){
					blResult=true;
				}
				else{
				}
			}
			catch(Exception e){
			}
		}
		else{
		}
		return blResult;
	}

}
