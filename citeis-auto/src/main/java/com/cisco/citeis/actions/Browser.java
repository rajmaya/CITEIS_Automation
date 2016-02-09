package com.cisco.citeis.actions;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser {
public static WebDriver launchURL(String strMachine,String strPort,String strBrowser,String strURL){
	WebDriver driver=null;
	DesiredCapabilities capability=null;
	URL url=null;
	try{
		url=new URL("http://"+strMachine+":"+strPort+"/wd/hub");
		if(strBrowser.equalsIgnoreCase("FIREFOX")){
			capability=DesiredCapabilities.firefox();
		}
		else if(strBrowser.equalsIgnoreCase("IE")){
			capability=DesiredCapabilities.internetExplorer();
			capability.setCapability("ignoreZoomSetting", true);
		}
		else if(strBrowser.equalsIgnoreCase("CHROME")){
			capability=DesiredCapabilities.chrome();
		}
		else if(strBrowser.equalsIgnoreCase("SAFARI")){
			capability=DesiredCapabilities.safari();
		}
		driver=new RemoteWebDriver(url,capability);
		
		driver=new Augmenter().augment(driver);
		driver.get(strURL);
		Sync.waitForSeconds(5,driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	catch(Exception e){
		
	}
	return driver;
}

public static WebDriver navigateURL(WebDriver driver,String strURL){
	try{
		driver.manage().deleteAllCookies();
		driver.navigate().to(strURL);
		
		Sync.waitForSeconds(5,driver);
	}
	catch(Exception e){
		
	}
	return driver;
}

public static boolean runScript(WebDriver driver,String strScript){
	boolean blResult=false;
	try{
		JavascriptExecutor javaScriptExecutor=((JavascriptExecutor)driver);
		javaScriptExecutor.executeScript(strScript);
		
		blResult=true;
	}
	catch(Exception e){
		
	}
	return blResult;
}

public static boolean scrollToObject(String strLogicalName,WebElement element,WebDriver driver){
	boolean blResult=false;
	if(element.isDisplayed()){
		if(element.isEnabled()){
			try{
				int y = ((Locatable)element).getCoordinates().onPage().getY();
			    ((JavascriptExecutor)driver). executeScript("window.scrollBy(0,"+y+");");
			    try{Thread.sleep(1000);}catch(Exception e){}
				blResult=true;
				//ResultUtil.report("PASS", "Scrolled to <<"+strLogicalName+">> object",driver);
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

//Close the driver
public static void closeDriver(WebDriver driver){
	driver.close();
}

public static void clearAllCookies(WebDriver driver){
	 //Clear cookies
	 driver.manage().deleteAllCookies();
}

public static boolean horizontalScrollToObject(String strLogicalName,WebElement element,WebDriver driver){
	boolean blResult=false;
	if(element.isDisplayed()){
		if(element.isEnabled()){
			try{
				int x = ((Locatable)element).getCoordinates().onPage().getX();
			    ((JavascriptExecutor)driver). executeScript("window.scrollBy(0,"+x+");");
			    try{Thread.sleep(1000);}catch(Exception e){}
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
