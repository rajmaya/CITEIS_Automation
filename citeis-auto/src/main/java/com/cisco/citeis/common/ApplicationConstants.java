package com.cisco.citeis.common;

import org.openqa.selenium.By;

public class ApplicationConstants {
	
	//Application Start  Page
	public static final String accHeader = "//h1[text()='The Application-Centric Cloud']";
	public static final String manageBtn = "//a[contains(@class,'btn btn-green') and text()='Manage']";

	
	//Home Page
	public static final String myApplicationsLink = ".myApplications";
	public static final String appRow = "tr[class='ng-scope']";
	
	
	//My Applications Page
	public static final String myApplicationshdr = "//span[@class = 'ng-binding ng-scope' and text()='My Applications']";
	public static final String appNames = "div[class='ciTruncate']>span";
	public static final String appDetailsName = "a[ci-output='application.displayName']";
	public static final String epgLink = "//h4[@class='panel-title']/a[contains(text(),'EPG')]";
	public static final String noAppDisplay = "//span[text()='No Application was found in your name']";
	public static final String currentProfileTab = ".inner-info-panel.column-details.selected-tab";
	public static final String instanceLink = "//h4[@class='panel-title']/a[contains(text(),'Instance')]";
	public static final String profileName = "a[ci-output='profile.displayName']";
	public static final By appRadioBtn = By.cssSelector("td:nth-of-type(1)>label>input");
	public static final By appPendingBtn = By.cssSelector("td:nth-of-type(1)>label>div");
	public static final String totalAppPages = "tablePagination_totalPages";
	public static final String tableNext = "tablePagination_nextPage";
}
