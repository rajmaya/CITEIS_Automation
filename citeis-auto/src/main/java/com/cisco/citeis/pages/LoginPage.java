package com.cisco.citeis.pages;

import org.openqa.selenium.WebDriver;

public class LoginPage {
	
/*	@FindBy(how  = How.ID, using = ApplicationConstants.username)
	private WebElement username;
	
	@FindBy(how  = How.ID, using = ApplicationConstants.password)
	private WebElement password;
	
	@FindBy(how  = How.ID, using = ApplicationConstants.signinButton)
	private WebElement signinBtn;*/
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/*public HomePage login(String userNameText, String passwordText){
		TextBox.enterValue("Username", username, userNameText, driver);
		TextBox.enterKeys("Username", username, Keys.TAB, driver);
		ATUReports.add("Entered Username", userNameText,LogAs.INFO,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		Sync.waitForObject(driver, "password", "ID", password, 20);
		TextBox.enterValue("Password", password, passwordText, driver);
		ATUReports.add("Entered Password", LogAs.INFO,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		Button.click("SignIn Button", signinBtn, driver);
		return PageFactory.initElements(driver, HomePage.class);
	}
*/
}
