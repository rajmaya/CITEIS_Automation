package com.cisco.citeis.testBase;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

import com.cisco.citeis.pages.AppStartPage;
import com.cisco.citeis.pages.HomePage;
import com.cisco.citeis.pages.MyApplicationsPage;
import com.cisco.citeis.util.LoggerUtil;
import com.cisco.citeis.util.PropertyUtil;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
		MethodListener.class })
public class TestBase {

	protected static WebDriver driver;
	public static String username, password;
	public HomePage homePage;
	public AppStartPage appStartPage;
	public MyApplicationsPage myApplicationsPage;
	Logger log = LoggerUtil.getLogger(getClass().getSimpleName());

	{
		System.setProperty("atu.reporter.config", "props" + File.separator
				+ "atu.properties");
	}

	@BeforeClass
	public void init() {
		new PropertyUtil().getProperties();
		String browserName = PropertyUtil.configMap.get("BROWSER");

		try {
			if (browserName.equalsIgnoreCase("internet explorer")) {
				File IEDriver = new File("ExtnLib\\IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver",
						IEDriver.getAbsolutePath());
				driver = new InternetExplorerDriver();
				driver.manage().timeouts()
						.implicitlyWait(30, TimeUnit.MILLISECONDS);

			} else if (browserName.equalsIgnoreCase("safari")) {
				driver = new SafariDriver();
				driver.manage().timeouts()
						.implicitlyWait(30, TimeUnit.MILLISECONDS);

			} else if (browserName.equalsIgnoreCase("chrome")) {
				File chromeDriver = new File("ExtnLib\\chromedriver.exe");
				System.setProperty("webdriver.chrome.driver",
						chromeDriver.getAbsolutePath());

				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("download.default_directory",
						System.getProperty("user.dir") + "\\Downloads");
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);
				driver.manage().timeouts()
						.implicitlyWait(30, TimeUnit.MILLISECONDS);
			}

			else if (browserName.equalsIgnoreCase("firefox")) {
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile ffprofile = profile.getProfile("default");
				driver = new FirefoxDriver(ffprofile);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().timeouts()
						.pageLoadTimeout(120, TimeUnit.SECONDS);
				driver.manage().timeouts()
						.setScriptTimeout(120, TimeUnit.SECONDS);
			}
			ATUReports.setWebDriver(driver);
			ATUReports.add("Browser is launched", LogAs.PASSED, null);
			log.info("Browser is launched");
			/*driver.get(PropertyUtil.configMap.get("URL"));
			driver.manage().window().maximize();*/
			
		} catch (Exception e) {
			ATUReports.add("Browser is not launched \n " + e.getMessage(),
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

}
