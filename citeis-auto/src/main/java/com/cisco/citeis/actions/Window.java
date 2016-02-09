package com.cisco.citeis.actions;

import org.openqa.selenium.WebDriver;

public class Window {
public static boolean focusWindow(String strLogicalName,WebDriver driver,String strWindowHandle){
	boolean blResult=false;
	
	try{
		driver.switchTo().window(strWindowHandle);
		blResult=true;
	}
	catch(Exception e){
	}
	return blResult;
}
}
