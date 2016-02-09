package com.cisco.citeis.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Object {
public static WebElement getElement(WebDriver driver,String strLocatorType,String strLocatorValue){
		WebElement element=null;
		
		try{
			if(strLocatorType.equalsIgnoreCase("ID")){
				element=driver.findElement(By.id(strLocatorValue));
			}
			else if(strLocatorType.equalsIgnoreCase("NAME")){
				element=driver.findElement(By.name(strLocatorValue));
			}
			else if(strLocatorType.equalsIgnoreCase("XPATH")){
				element=driver.findElement(By.xpath(strLocatorValue));
			}
			else if(strLocatorType.equalsIgnoreCase("CSS")){
				element=driver.findElement(By.cssSelector(strLocatorValue));
			}
			else if(strLocatorType.equalsIgnoreCase("CLASS")){
				element=driver.findElement(By.className(strLocatorValue));
			}
		}
		catch(Exception e){}
		
		return element;
}
}
