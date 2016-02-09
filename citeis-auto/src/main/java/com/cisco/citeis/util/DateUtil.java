/*
 * FileName         : DateUtil 
 * File Description : To get access of Date Utils
 * Company          : GAVS
 * Author           : UdayBhaskar Donda
 * Manager          : Anand Kumar M C
 * 
 */

package com.cisco.citeis.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
	//static Logger log = Logger.getLogger(DateUtil.class.getName());

		public static String getCurrentDate(String strFormat) {
		DateFormat dateFormat = new SimpleDateFormat(strFormat);
		Date date = new Date();
		return dateFormat.format(date);
	}

	
	public static long compareDate(String strDate1, String strDate2,
			String strFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		java.util.Date d1 = null;
		java.util.Date d2 = null;

		try {
			d1 = sdf.parse(strDate1);
			d2 = sdf.parse(strDate2);
		} catch (ParseException e) {
			//log.error(e.getMessage());
		}
		return d1.getTime() - d2.getTime();
	}

	
	public static boolean IsDate(String strDate, String strFormat) {
		boolean blResult = false;
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(strDate);
			blResult = true;
		} catch (ParseException e) {
			//log.error(e.getMessage());
			blResult = false;
		}
		return blResult;
	}
}
