package com.cisco.citeis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cisco.cat.reports.CATReports;
import com.cisco.cat.reports.logging.LogAs;
import com.cisco.cat.reports.sel.CaptureScreen;
import com.cisco.cat.reports.sel.CaptureScreen.ScreenshotOf;
import com.cisco.citeis.actions.Element;
import com.cisco.citeis.actions.Link;
import com.cisco.citeis.common.ApplicationConstants;

public class HomePage {
	
	@FindBy(how = How.XPATH,using = ApplicationConstants.myApplicationshdr)
	private WebElement myApplicationshdr;
	
	@FindBy(how = How.CSS,using = ApplicationConstants.myApplicationsLink)
	private WebElement myApplicationsLink;

	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Checks if 'My Applications' page is displayed , if not clicks on 'My Applications' link from the left panel
	 * @return
	 */
	public MyApplicationsPage clickMyApplications() {
		if(Element.verify("My Applications header", myApplicationshdr, driver)){
			CATReports.add("Checking for 'My Applications' to be displayed","My Applications Page should be displayed","My Applications Page is displayed", LogAs.PASSED, null);
			return PageFactory.initElements(driver, MyApplicationsPage.class);
		}else{
			if(Link.click("My Applications", myApplicationsLink, driver)){
				if(Element.verify("My Applications header", myApplicationshdr, driver)){
					CATReports.add("Checking for 'My Applications' to be displayed","My Applications Page should be displayed","My Applications Page is displayed", LogAs.PASSED, null);
				}else{
					CATReports.add("Checking for 'My Applications' to be displayed","My Applications Page should be displayed","My Applications Page is not displayed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
		}
		return PageFactory.initElements(driver, MyApplicationsPage.class);
	}

}
