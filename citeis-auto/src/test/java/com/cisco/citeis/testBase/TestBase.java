package com.cisco.citeis.testBase;

import java.io.File;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Listeners;

import com.cisco.citeis.actions.Sync;
import com.cisco.citeis.customatu.reports.ATUReports;
import com.cisco.citeis.customatu.reports.listeners.ATUReportsListener;
import com.cisco.citeis.customatu.reports.listeners.ConfigurationListener;
import com.cisco.citeis.customatu.reports.listeners.MethodListener;
import com.cisco.citeis.customatu.reports.logging.LogAs;
import com.cisco.citeis.customatu.reports.sel.CaptureScreen;
import com.cisco.citeis.customatu.reports.sel.CaptureScreen.ScreenshotOf;
import com.cisco.citeis.customatu.reports.utils.Platform;
import com.cisco.citeis.pages.AppStartPage;
import com.cisco.citeis.pages.ApplicationDetailsPage;
import com.cisco.citeis.pages.CurrentProfilePage;
import com.cisco.citeis.pages.HomePage;
import com.cisco.citeis.pages.MyApplicationsPage;
import com.cisco.citeis.util.PropertyUtil;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
		MethodListener.class })
public class TestBase {

	protected static WebDriver driver;
	public static String username, password;
	public HomePage homePage;
	public AppStartPage appStartPage;
	public MyApplicationsPage myApplicationsPage;
	public ApplicationDetailsPage applicationDetailsPage;
	public CurrentProfilePage currentProfilePage;
	//Logger log = LoggerUtil.getLogger(getClass().getSimpleName());

	{
		System.setProperty("atu.reporter.config", "props" + File.separator
				+ "atu.properties");
	}

	
	
	public WebDriver init(Map<String,String>dataMap) {

		new PropertyUtil().getProperties();
		//String browserName = PropertyUtil.configMap.get("BROWSER");
		String strMachine= dataMap.get("MACHINE");
		Platform.setHostName(strMachine);
		String strPort= dataMap.get("PORT");
		String browserName = dataMap.get("BROWSER");
		DesiredCapabilities capability=null;
		String strUrl = PropertyUtil.configMap.get("URL");

		try{
			URL url=new URL("http://"+strMachine+":"+strPort+"/wd/hub");
			if(browserName.equalsIgnoreCase("FIREFOX")){
				capability=DesiredCapabilities.firefox();
				
			}
			else if(browserName.equalsIgnoreCase("IE")){
				capability=DesiredCapabilities.internetExplorer();
				capability.setCapability("ignoreZoomSetting", true);
			}
			else if(browserName.equalsIgnoreCase("CHROME")){
				//System.setProperty("webdriver.chrome.driver", "ExtnLib//chromedriver");
				capability=DesiredCapabilities.chrome();
			}
			else if(browserName.equalsIgnoreCase("SAFARI")){
				capability=DesiredCapabilities.safari();
			}
			//driver=new RemoteWebDriver(url,capability);
			driver = new RemoteWebDriver(url, capability);
			ATUReports.setWebDriver(driver);
			ATUReports.add("Browser is launched", LogAs.PASSED, null);
			driver.get(strUrl);
			Sync.waitForSeconds(5,driver);
			driver.manage().window().maximize();
			Sync.waitForSeconds(5,driver);
			
			
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			ATUReports.add("Browser is not launched \n " + e.getMessage(),
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return driver;
		
	}

}
