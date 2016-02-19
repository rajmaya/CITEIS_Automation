package com.cisco.cat.reports.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import com.cisco.cat.reports.enums.RecordingFor;
import com.cisco.cat.reports.enums.ReportLabels;
import com.cisco.cat.reports.exceptions.CATReporterException;
import com.cisco.cat.reports.writers.HTMLDesignFilesWriter;

public class Directory
{
  public static final String CAT_VERSION = "v1.0 BETA";
  public static final String CURRENTDir = System.getProperty("user.dir");
  public static final String SEP = System.getProperty("file.separator");
  public static String REPORTSDIRName = "CAT Reports";
  public static String REPORTSDir = CURRENTDir + SEP + REPORTSDIRName;
  public static String RESULTSDir = REPORTSDir + SEP + "Results";
  public static String HTMLDESIGNDIRName = "HTML_Design_Files";
  public static String HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
  public static String CSSDIRName = "CSS";
  public static String CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
  public static String IMGDIRName = "IMG";
  public static String IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
  public static String JSDIRName = "JS";
  public static String JSDir = HTMLDESIGNDir + SEP + JSDIRName;
  public static String RUNName = "Run_";
  public static String RUNDir = RESULTSDir + SEP + RUNName;
  public static String SETTINGSFile = RESULTSDir + SEP + "Settings.properties";
  public static final char JS_SETTINGS_DELIM = ';';
  public static final String REPO_DELIM = "##@##@##";
  public static final char JS_FILE_DELIM = ',';
  public static final String MENU_LINK_NAME = "Run ";
  public static final String SCREENSHOT_TYPE = "PNG";
  public static final String SCREENSHOT_EXTENSION = ".PNG";
  private static String logo = null;
  public static String SCREENSHOT_DIRName = "img";
  public static boolean generateConfigReports = true;
  public static boolean generateExcelReports = false;
  public static boolean takeScreenshot = false;
  public static boolean continueExecutionAfterStepFailed = false;
  public static boolean recordExecutionAvailable = false;
  public static boolean recordSuiteExecution = false;
  public static boolean recordTestMethodExecution = false;
  public static final String MAIN_RECORD_FILE_NAME = "CAT_CompleteSuiteRecording";
  
  public Directory() {}
  
  public static void init()
    throws CATReporterException
  {
    if (getCustomProperties() != null)
    {
      Properties localProperties = new Properties();
      try
      {
        localProperties.load(new FileReader(getCustomProperties()));
        String str1 = localProperties.getProperty("cat.reports.dir").trim();
        String str2 = localProperties.getProperty("cat.proj.header.text").trim();
        logo = localProperties.getProperty("cat.proj.header.logo").trim();
        String str3 = localProperties.getProperty("cat.proj.description").trim();
        String str4 = localProperties.getProperty("cat.reports.takescreenshot").trim();
        String str5 = localProperties.getProperty("cat.reports.configurationreports").trim();
        String str6 = localProperties.getProperty("cat.reports.excel").trim();
        String str7 = localProperties.getProperty("cat.reports.continueExecutionAfterStepFailed").trim();
        String str8 = localProperties.getProperty("cat.reports.recordExecution").trim();
        try
        {
          if ((str2 != null) && (str2.length() > 0)) {
            ReportLabels.HEADER_TEXT.setLabel(str2);
          }
          if ((str4 != null) && (str4.length() > 0)) {
            try
            {
              takeScreenshot = Boolean.parseBoolean(str4);
            }
            catch (Exception localException1) {}
          }
          if ((str5 != null) && (str5.length() > 0)) {
            try
            {
              generateConfigReports = Boolean.parseBoolean(str5);
            }
            catch (Exception localException2) {}
          }
          if ((str6 != null) && (str6.length() > 0)) {
            try
            {
              generateExcelReports = Boolean.parseBoolean(str6);
            }
            catch (Exception localException3) {}
          }
          if ((str7 != null) && (str7.length() > 0)) {
            try
            {
              continueExecutionAfterStepFailed = Boolean.parseBoolean(str7);
            }
            catch (Exception localException4) {}
          }
          if ((str8 != null) && (str8.length() > 0)) {
            try
            {
              RecordingFor localRecordingFor = RecordingFor.valueOf(str8.toUpperCase());
              if (localRecordingFor == RecordingFor.SUITE) {
                recordSuiteExecution = true;
              } else if (localRecordingFor == RecordingFor.TESTMETHOD) {
                recordTestMethodExecution = true;
              }
            }
            catch (Throwable localThrowable) {}
          }
          if ((str3 != null) && (str3.length() > 0)) {
        	  com.cisco.cat.reports.CATReports.indexPageDescription = str3;
          }
          if ((str1 != null) && (str1.length() > 0))
          {
            REPORTSDir = str1;
            REPORTSDIRName = new File(REPORTSDir).getName();
            RESULTSDir = REPORTSDir + SEP + "Results";
            HTMLDESIGNDIRName = "HTML_Design_Files";
            HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
            CSSDIRName = "CSS";
            CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
            IMGDIRName = "IMG";
            IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
            JSDIRName = "JS";
            JSDir = HTMLDESIGNDir + SEP + JSDIRName;
            RUNName = "Run_";
            RUNDir = RESULTSDir + SEP + RUNName;
            SETTINGSFile = RESULTSDir + SEP + "Settings.properties";
          }
        }
        catch (Exception localException5)
        {
          throw new CATReporterException(localException5.toString());
        }
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        throw new CATReporterException("The Path for the Custom Properties file could not be found");
      }
      catch (IOException localIOException)
      {
        throw new CATReporterException("Problem Occured while reading the cat Reporter Config File");
      }
    }
  }
  
  public static boolean mkDirs(String paramString)
  {
    File localFile = new File(paramString);
    boolean fileExist = false;
    if (!localFile.exists()) {
      localFile.mkdirs();
    }else{
      fileExist = true;	
    }
    
    return fileExist;
  }
  
  public static boolean exists(String paramString)
  {
    File localFile = new File(paramString);
    return localFile.exists();
  }
  
  public static void verifyRequiredFiles()
    throws CATReporterException
  {
    init();
    mkDirs(REPORTSDir);
    if (!exists(RESULTSDir))
    {
      mkDirs(RESULTSDir);
      SettingsFile.initSettingsFile();
    }
    if (!exists(HTMLDESIGNDir))
    {
      mkDirs(HTMLDESIGNDir);
      mkDirs(CSSDir);
      mkDirs(JSDir);
      mkDirs(IMGDir);
      HTMLDesignFilesWriter.writeCSS();
      HTMLDesignFilesWriter.writeIMG();
      HTMLDesignFilesWriter.writeJS();
    }
    if ((logo != null) && (logo.length() > 0))
    {
      String str = new File(logo).getName();
      if (!new File(IMGDir + SEP + str).exists()) {
        copyImage(logo);
      }
      ReportLabels.PROJ_LOGO.setLabel(str);
    }
  }
  
  private static void copyImage(String paramString)
    throws CATReporterException
  {
    File localFile = new File(paramString);
    if (!localFile.exists()) {
      return;
    }
    FileImageInputStream localFileImageInputStream = null;
    FileImageOutputStream localFileImageOutputStream = null;
    try
    {
      localFileImageInputStream = new FileImageInputStream(new File(paramString));
      localFileImageOutputStream = new FileImageOutputStream(new File(IMGDir + SEP + localFile.getName()));
      int i = 0;
      while ((i = localFileImageInputStream.read()) >= 0) {
        localFileImageOutputStream.write(i);
      }
      localFileImageOutputStream.close();
      return;
    }
    catch (Exception localException2) {}finally
    {
      try
      {
        localFileImageInputStream.close();
        localFileImageOutputStream.close();
        localFile = null;
      }
      catch (Exception localException4)
      {
        localFileImageInputStream = null;
        localFileImageOutputStream = null;
        localFile = null;
      }
    }
  }
  
  private static String getCustomProperties()
  {
    return System.getProperty("cat.reporter.config");
  }
}


