package com.cisco.cat.reports.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public Utils() {}
	  
	  public static String getCurrentTime()
	  {
	    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
	    Date localDate = new Date();
	    return localSimpleDateFormat.format(localDate);
	  }
}
