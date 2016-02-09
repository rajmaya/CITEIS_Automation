package com.cisco.citeis.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtil {
	
	public static Map<String,String> configMap;
	
	public void getProperties(){
		try{
			Properties prop = new Properties();
			prop.load(new FileInputStream("props/debug.properties"));
			configMap = new HashMap<String, String>();
			for (String key : prop.stringPropertyNames()) {
				configMap.put(key, prop.getProperty(key));
			}
		}
		catch(Exception e){
			
		}
	}
	
	
	

}
