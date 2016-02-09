package com.cisco.citeis.util;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LoggerUtil {
	
	static Logger log;
	
	static{
		DOMConfigurator.configure("props/log4j.xml");
	}
	
	public static Logger getLogger(String className){
		log = Logger.getLogger(className);
		return log;
	}

}
