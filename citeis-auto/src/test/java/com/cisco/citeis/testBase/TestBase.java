package com.cisco.citeis.testBase;

import java.io.File;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;

import com.cisco.cat.reports.CATReports;
import com.cisco.cat.reports.exceptions.CATReporterException;
import com.cisco.cat.reports.listeners.CATReportsListener;
import com.cisco.cat.reports.listeners.ConfigurationListener;
import com.cisco.cat.reports.listeners.MethodListener;
import com.cisco.cat.reports.logging.LogAs;
import com.cisco.cat.reports.sel.CaptureScreen;
import com.cisco.cat.reports.sel.CaptureScreen.ScreenshotOf;
import com.cisco.cat.reports.utils.Directory;
import com.cisco.cat.reports.utils.Platform;
import com.cisco.cat.reports.utils.SettingsFile;
import com.cisco.citeis.actions.Sync;
import com.cisco.citeis.pages.AppStartPage;
import com.cisco.citeis.pages.ApplicationDetailsPage;
import com.cisco.citeis.pages.CurrentProfilePage;
import com.cisco.citeis.pages.HomePage;
import com.cisco.citeis.pages.MyApplicationsPage;
import com.cisco.citeis.util.PropertyUtil;
import com.cisco.citeis.util.SendMail;

@Listeners({ CATReportsListener.class, ConfigurationListener.class,
		MethodListener.class })
public class TestBase{

	protected static WebDriver driver;
	public String suiteName;
	public static String username, password;
	public HomePage homePage;
	public AppStartPage appStartPage;
	public MyApplicationsPage myApplicationsPage;
	public ApplicationDetailsPage applicationDetailsPage;
	public CurrentProfilePage currentProfilePage;
	//Logger log = LoggerUtil.getLogger(getClass().getSimpleName());

	{
		System.setProperty("cat.reporter.config", "props" + File.separator
				+ "cat.properties");
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
			driver = new RemoteWebDriver(url, capability);
			CATReports.setWebDriver(driver);
			CATReports.add("Browser is launched", LogAs.PASSED, null);
			
			driver.manage().window().maximize();
			Sync.waitForSeconds(5,driver);
			//Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\inputs\\autoiteg.exe");
			Sync.waitForSeconds(5,driver);
     		driver.get(strUrl);
			Sync.waitForSeconds(5,driver);
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			CATReports.add("Browser is not launched \n " + e.getMessage(),
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		return driver;
		
	}
	
	
	/*public void afterExecution() throws CATReporterException{
	String resultPath = Directory.CURRENTDir+"\\results\\CAT Reporter\\Results\\"+Directory.RUNName+Directory.RUNDir.split("_")[1]+"\\"+"CurrentRun.html" ;
	//int testno = SettingsFile.getHighestTestCaseNumber();
	String[] arrayOfString1 = SettingsFile.get("passedList").split(";");
	String[] arrayOfString2 = SettingsFile.get("failedList").split(";");
	String[] arrayOfString3 = SettingsFile.get("skippedList").split(";");
	int[] arrayOfInt1 = SettingsFile.getIntArrayFromStringArray(arrayOfString1);
    int[] arrayOfInt2 = SettingsFile.getIntArrayFromStringArray(arrayOfString2);
    int[] arrayOfInt3 = SettingsFile.getIntArrayFromStringArray(arrayOfString3);
    int passed = arrayOfInt1[(arrayOfInt1.length)-1];
    System.out.println("Array of passed  :"+passed);
    int failed = arrayOfInt2[(arrayOfInt2.length)-1];
    System.out.println("Array of failed  :"+failed);
    int skipped = arrayOfInt3[(arrayOfInt3.length)-1];
    System.out.println("Array of skipped  :"+skipped);
    int totaltest = passed+failed+skipped;
   	//SendMail.sendReportToMail(resultPath, totaltest, passed, failed, skipped);
    }*/
	
}
