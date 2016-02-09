package com.cisco.citeis.actions;

import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class Listbox {
	public static boolean selectItemByValue(String strLogicalName,WebElement element,String strValue,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Select sel = new Select(element);
						sel.selectByVisibleText(strValue);
						blResult=true;
						ATUReports.add("Selecting "+strValue+" value in "+strLogicalName+" listbox","To Select "+strValue+" value in "+strLogicalName+" listbox","Selected "+strValue+" value "+strLogicalName+" listbox", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

					}
					catch(Exception e){
						ATUReports.add("Selecting "+strValue+" value in "+strLogicalName+" listbox","To Select "+strValue+" value in "+strLogicalName+" listbox","Unable to select "+strValue+" value in "+strLogicalName+" listbox"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}

				}
				else{
					ATUReports.add("Selecting "+strValue+" value in "+strLogicalName+" listbox","To Select "+strValue+" value in "+strLogicalName+" listbox","Unable to select "+strValue+" value,  "+strLogicalName+" listbox is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Selecting "+strValue+" value in "+strLogicalName+" listbox","To Select "+strValue+" value in "+strLogicalName+" listbox","Unable to select "+strValue+" value,  "+strLogicalName+" listbox is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			}catch(Exception e){
				ATUReports.add("Selecting "+strValue+" value in "+strLogicalName+" listbox","To Select "+strValue+" value in "+strLogicalName+" listbox","Unable to select "+strValue+" value in "+strLogicalName+" listbox"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			}
		}else{
			ATUReports.add("Selecting "+strValue+" value in "+strLogicalName+" listbox","To Select "+strValue+" value in "+strLogicalName+" listbox","Unable to select "+strValue+" value in "+strLogicalName+" listbox, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

		return blResult;
	}

	public static boolean selectItems(String strLogicalName,WebElement element,String strItems,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Select sel = new Select(element);
						element.sendKeys(Keys.CONTROL);
						String[] arrItems=strItems.split("\\;");
						for(int inItem=0;inItem<=arrItems.length-1;inItem++){
							sel.selectByVisibleText(arrItems[inItem]);
						}

						blResult=true;
						ATUReports.add("Selecting "+strItems+" value in "+strLogicalName+" listbox","To Select "+strItems+" value in "+strLogicalName+" listbox","Selected "+strItems+" value "+strLogicalName+" listbox", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
					catch(Exception e){
						ATUReports.add("Selecting "+strItems+" value in "+strLogicalName+" listbox","To Select "+strItems+" value in "+strLogicalName+" listbox","Unable to select "+strItems+" value in "+strLogicalName+" listbox"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}

				}
				else{
					ATUReports.add("Selecting "+strItems+" value in "+strLogicalName+" listbox","To Select "+strItems+" value in "+strLogicalName+" listbox","Unable select strItems value, "+strLogicalName+" listbox is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Selecting "+strItems+" value in "+strLogicalName+" listbox","To Select "+strItems+" value in "+strLogicalName+" listbox","Unable select strItems value, "+strLogicalName+" listbox is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			}catch(Exception e){
				ATUReports.add("Selecting "+strItems+" value in "+strLogicalName+" listbox","To Select "+strItems+" value in "+strLogicalName+" listbox","Unable to select "+strItems+" value in "+strLogicalName+" listbox"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			}
		}else{
			ATUReports.add("Selecting "+strItems+" value in "+strLogicalName+" listbox","To Select "+strItems+" value in "+strLogicalName+" listbox","Unable to select strItems value in "+strLogicalName+" listbox, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}


		return blResult;
	}

	public static boolean selectItemByIndex(String strLogicalName,WebElement element,int itemIndex,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Select sel = new Select(element);
						sel.selectByIndex(itemIndex);
						blResult=true;
						ATUReports.add("Selecting "+itemIndex+" value in "+strLogicalName+" listbox","To Select "+itemIndex+" value in "+strLogicalName+" listbox","Selected "+itemIndex+" value "+strLogicalName+" listbox", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
					catch(Exception e){
						ATUReports.add("Selecting "+itemIndex+" value in "+strLogicalName+" listbox","To Select "+itemIndex+" value in "+strLogicalName+" listbox","Unable to select "+itemIndex+" value in "+strLogicalName+" listbox"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}

				}
				else{
					ATUReports.add("Selecting "+itemIndex+" value in "+strLogicalName+" listbox","To Select "+itemIndex+" value in "+strLogicalName+" listbox","Unable to select "+itemIndex+" value in "+strLogicalName+" listbox is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Selecting "+itemIndex+" value in "+strLogicalName+" listbox","To Select "+itemIndex+" value in "+strLogicalName+" listbox","Unable to select "+itemIndex+" value in "+strLogicalName+" listbox is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			}catch(Exception e){
				ATUReports.add("Selecting "+itemIndex+" value in "+strLogicalName+" listbox","To Select "+itemIndex+" value in "+strLogicalName+" listbox","Unable to select "+itemIndex+" value in "+strLogicalName+" listbox"+"\n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			}
		}else{
			ATUReports.add("Selecting "+itemIndex+" value in "+strLogicalName+" listbox","To Select "+itemIndex+" value in "+strLogicalName+" listbox","Unable to select "+itemIndex+" value in "+strLogicalName+" listbox, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return blResult;
	}

	public static boolean selectFirstItem(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Select sel = new Select(element);
						sel.selectByIndex(1);
						blResult=true;
						ATUReports.add("Selecting first value in "+strLogicalName+" listbox","To Select first value in "+strLogicalName+" listbox","Selected first value in, "+strLogicalName+" listbox", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
					catch(Exception e){
						ATUReports.add("Selecting first value in "+strLogicalName+" listbox","To Select first value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox"+"/n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}

				}
				else{
					ATUReports.add("Selecting first value in "+strLogicalName+" listbox","To Select first value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Selecting first value in "+strLogicalName+" listbox","To Select first value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			}catch(Exception e){
				ATUReports.add("Selecting first value in "+strLogicalName+" listbox","To Select first value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox"+"/n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			}
		}else{
			ATUReports.add("Selecting first value in "+strLogicalName+" listbox","To Select first value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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
					try{
						Select sel = new Select(element);
						sel.selectByIndex(sel.getOptions().size());
						blResult=true;
						ATUReports.add("Selecting last value in "+strLogicalName+" listbox","To Select last value in "+strLogicalName+" listbox","Selected last value in, "+strLogicalName+" listbox", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
					catch(Exception e){
						ATUReports.add("Selecting last value in "+strLogicalName+" listbox","To Select last value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox"+"/n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}

				}
				else{
					ATUReports.add("Selecting last value in "+strLogicalName+" listbox","To Select last value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Selecting last value in "+strLogicalName+" listbox","To Select last value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			}catch(Exception e){
				ATUReports.add("Selecting last value in "+strLogicalName+" listbox","To Select last value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox"+"/n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			}
		}else{
			ATUReports.add("Selecting last value in "+strLogicalName+" listbox","To Select last value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
		return blResult;
	}

	public static boolean selectRandomItem(String strLogicalName,WebElement element,WebDriver driver){
		boolean blResult=false;
		int size =0;
		int index = 0;
		if(element!=null){
			try{
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Select sel = new Select(element);
						index=getRandomIndex(sel.getOptions().size());
						sel.selectByIndex(index);
						blResult=true;
						ATUReports.add("Selecting random value in "+strLogicalName+" listbox","To Select random value in "+strLogicalName+" listbox","Selected random value in, "+strLogicalName+" listbox", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
					catch(Exception e){
						ATUReports.add("Selecting value in "+strLogicalName+" listbox","To Select value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox"+"/n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						Assert.fail(e.getMessage());
					}

				}
				else{
					ATUReports.add("Selecting value in "+strLogicalName+" listbox","To Select value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox is disabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			else{
				ATUReports.add("Selecting value in "+strLogicalName+" listbox","To Select value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			}catch(Exception e){
				ATUReports.add("Selecting value in "+strLogicalName+" listbox","To Select value in "+strLogicalName+" listbox","Unable select value in, "+strLogicalName+" listbox"+"/n"+"Exception occurred: "+e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				Assert.fail(e.getMessage());
			}
		}else{
			ATUReports.add("Selecting value in "+strLogicalName+" listbox","To Select value in "+strLogicalName+" listbox","Unable select value in "+strLogicalName+" listbox, element is null", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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

	public static boolean deselectAll(WebElement element , String strLogicalName,WebDriver driver){
		boolean blResult=false;
		if(element.isDisplayed()){
			if(element.isEnabled()){
				try{
					Select sel = new Select(element);
					sel.deselectAll();
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

}
