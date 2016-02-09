package com.cisco.citeis.util;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.cisco.citeis.util.ExcelUtil;



public class DataProviderUtil {
	
	public static class StaticProviderClass{
	
	@DataProvider(name="CiteisData",parallel=false)
	public static Iterator<Object[]> citeisData(){
		Iterator<Object[]> testData=ExcelUtil.getTestData(System.getProperty("user.dir")+"/inputs/TestData.xlsx", "AppFlow");
		return testData;
	}

	}
}
