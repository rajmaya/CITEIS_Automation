package com.cisco.cat.reports.listeners;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.IExecutionListener;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.cisco.cat.reports.CATReports;
import com.cisco.cat.reports.exceptions.CATReporterException;
import com.cisco.cat.reports.exceptions.CATReporterStepFailedException;
import com.cisco.cat.reports.utils.Attributes;
import com.cisco.cat.reports.utils.Directory;
import com.cisco.cat.reports.utils.FTPConnect;
import com.cisco.cat.reports.utils.Platform;
import com.cisco.cat.reports.utils.SendMail;
import com.cisco.cat.reports.utils.SettingsFile;
import com.cisco.cat.reports.writers.ConsolidatedReportsPageWriter;
import com.cisco.cat.reports.writers.CurrentRunPageWriter;
import com.cisco.cat.reports.writers.HTMLDesignFilesJSWriter;
import com.cisco.cat.reports.writers.IndexPageWriter;
import com.cisco.cat.reports.writers.TestCaseReportsPageWriter;
import com.cisco.cat.reports.*;

public class CATReportsListener
  implements ITestListener, IExecutionListener, IReporter, ISuiteListener
{
  int runCount = 0;
  List<ITestResult> passedTests = new ArrayList();
  List<ITestResult> failedTests = new ArrayList();
  List<ITestResult> skippedTests = new ArrayList();
  //private ATUTestRecorder recorder;
  public static boolean suiteStarted = false;
  
  public String suiteName;
  
  public CATReportsListener() {}
  
  public String getSuiteName() {
	return suiteName;
}

public void onStart(ITestContext paramITestContext) {
	  suiteName = paramITestContext.getSuite().getName();
	  CATReports.currentRunDescription = "Execution of Test Suite '"+suiteName+"'";
  }
  
  public void onFinish(ITestContext paramITestContext) {}
  
  public void onTestFailedButWithinSuccessPercentage(ITestResult paramITestResult) {}
  
  public void onTestFailure(ITestResult paramITestResult)
  {
    this.failedTests.add(paramITestResult);
  }
  
  public void onTestSkipped(ITestResult paramITestResult)
  {
    if ((paramITestResult.getThrowable() instanceof SkipException))
    {
      this.skippedTests.add(paramITestResult);
      return;
    }
    createReportDir(paramITestResult,1);
    this.skippedTests.add(paramITestResult);
  }
  
  public void onTestStart(ITestResult paramITestResult) {}
  
  public void onTestSuccess(ITestResult paramITestResult)
  {
    try
    {
      if (paramITestResult.getAttribute("passedButFailed").equals("passedButFailed"))
      {
        paramITestResult.setStatus(2);
        paramITestResult.setThrowable(new CATReporterStepFailedException());
        this.failedTests.add(paramITestResult);
        return;
      }
    }
    catch (NullPointerException localNullPointerException) {}
    this.passedTests.add(paramITestResult);
  }
  
  public static void setPlatfromBrowserDetails(ITestResult paramITestResult)
  {
    Platform.prepareDetails(CATReports.getWebDriver());
    paramITestResult.setAttribute(Platform.BROWSER_NAME_PROP, Platform.BROWSER_NAME);
    paramITestResult.setAttribute(Platform.BROWSER_VERSION_PROP, Platform.BROWSER_VERSION);
    paramITestResult.setAttribute(Platform.HOST_NAME_PROP, Platform.HOST_NAME);
  }
  
  public static void createReportDir(ITestResult paramITestResult,int i)
  {
    String str = getReportDir(paramITestResult,i);
    boolean fileExist = Directory.mkDirs(str);
    if(fileExist && str.contains("Iteration")){
    	String foldernum = str.split("Iteration")[str.split("Iteration").length-1];
        createReportDir(paramITestResult,Integer.parseInt(foldernum)+1);
    }
    
    
    Directory.mkDirs(str + Directory.SEP + Directory.SCREENSHOT_DIRName);
  }
  
  public static String getRelativePathFromSuiteLevel(ITestResult paramITestResult,int i)
  {
    String str1 = paramITestResult.getTestContext().getSuite().getName();
    String str2 = paramITestResult.getTestContext().getCurrentXmlTest().getName();
    String str3 = paramITestResult.getTestClass().getName().replace(".", Directory.SEP);
    String str4 = paramITestResult.getMethod().getMethodName();
    str4 = str4 + "_Iteration" + (paramITestResult.getMethod().getCurrentInvocationCount() + i);
    return str1 + Directory.SEP + str2 + Directory.SEP + str3 + Directory.SEP + str4;
  }
  
  public static String getReportDir(ITestResult paramITestResult,int i)
  {
    String str1 = getRelativePathFromSuiteLevel(paramITestResult,i);
    paramITestResult.setAttribute("relativeReportDir", str1);
    String str2 = Directory.RUNDir + Directory.SEP + str1;
    paramITestResult.setAttribute("iteration", Integer.valueOf(paramITestResult.getMethod().getCurrentInvocationCount() + 1));
    paramITestResult.setAttribute("reportDir", str2);
    return str2;
  }
  
  public void setTickInterval(List<ITestResult> paramList1, List<ITestResult> paramList2, List<ITestResult> paramList3)
    throws CATReporterException
  {
    int i = SettingsFile.getHighestTestCaseNumber();
    int j = SettingsFile.getBiggestNumber(new int[] { i, paramList1.size(), paramList2.size(), paramList3.size() });
    int k = j / 10;
    if (k > 1) {
      HTMLDesignFilesJSWriter.TICK_INTERVAL = k;
    }
  }
  
  public void onFinish()
  {
    try
    {
      String str1 = SettingsFile.get("passedList") + this.passedTests.size() + ';';
      String str2 = SettingsFile.get("failedList") + this.failedTests.size() + ';';
      String str3 = SettingsFile.get("skippedList") + this.skippedTests.size() + ';';
      SettingsFile.set("passedList", str1);
      SettingsFile.set("failedList", str2);
      SettingsFile.set("skippedList", str3);
      setTickInterval(this.passedTests, this.failedTests, this.skippedTests);
      HTMLDesignFilesJSWriter.lineChartJS(str1, str2, str3, this.runCount);
      HTMLDesignFilesJSWriter.barChartJS(str1, str2, str3, this.runCount);
      HTMLDesignFilesJSWriter.pieChartJS(this.passedTests.size(), this.failedTests.size(), this.skippedTests.size(), this.runCount);
      //generateIndexPage();
      long l = ((Long)Attributes.getAttribute("startExecution")).longValue();
      generateConsolidatedPage();
      generateCurrentRunPage(this.passedTests, this.failedTests, this.skippedTests, l, System.currentTimeMillis());
      startReportingForPassed(this.passedTests);
      startReportingForFailed(this.failedTests);
      startReportingForSkipped(this.skippedTests);
      int totaltest=this.passedTests.size()+this.failedTests.size()+this.skippedTests.size();
      String strLocalPath = Directory.CURRENTDir+"\\results";
      FTPConnect.uploadToFTP("10.197.64.122", "anonymous", "anonymous", "/Gopal/results", strLocalPath);
      SendMail.sendReportToMail(totaltest, this.passedTests.size(), this.failedTests.size(),this.skippedTests.size());
     
      if (Directory.generateExcelReports) {
        //ExcelReports.generateExcelReport(Directory.RUNDir + Directory.SEP + "(" + Directory.REPORTSDIRName + ") " + Directory.RUNName + this.runCount + ".xlsx", this.passedTests, this.failedTests, this.skippedTests);
      }
      if (Directory.generateConfigReports) {
        ConfigurationListener.startConfigurationMethodsReporting(this.runCount);
      }
    }
    catch (Exception localException)
    {
      throw new IllegalStateException(localException);
    }
  }
  
  public void startCreatingDirs(ISuite paramISuite)
  {
    Directory.mkDirs(Directory.RUNDir + Directory.SEP + paramISuite.getName());
    Iterator localIterator = paramISuite.getXmlSuite().getTests().iterator();
    while (localIterator.hasNext())
    {
      XmlTest localXmlTest = (XmlTest)localIterator.next();
      Directory.mkDirs(Directory.RUNDir + Directory.SEP + paramISuite.getName() + Directory.SEP + localXmlTest.getName());
    }
  }
  
  public void generateIndexPage()
  {
    PrintWriter localPrintWriter = null;
    try
    {
      localPrintWriter = new PrintWriter(Directory.REPORTSDir + Directory.SEP + "index.html");
      IndexPageWriter.header(localPrintWriter);
      IndexPageWriter.content(localPrintWriter, CATReports.indexPageDescription);
      //IndexPageWriter.footer(localPrintWriter);
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
    }
    finally
    {
      try
      {
        localPrintWriter.close();
      }
      catch (Exception localException3)
      {
        localPrintWriter = null;
      }
    }
  }
  
  public void generateCurrentRunPage(List<ITestResult> paramList1, List<ITestResult> paramList2, List<ITestResult> paramList3, long paramLong1, long paramLong2)
  {
    PrintWriter localPrintWriter = null;
    try
    {
      localPrintWriter = new PrintWriter(Directory.RUNDir + Directory.SEP + "CurrentRun.html");
      CurrentRunPageWriter.header(localPrintWriter);
      CurrentRunPageWriter.menuLink(localPrintWriter, 0);
      CurrentRunPageWriter.content(localPrintWriter, paramList1, paramList2, paramList3, ConfigurationListener.passedConfigurations, ConfigurationListener.failedConfigurations, ConfigurationListener.skippedConfigurations, this.runCount, paramLong1, paramLong2);
      //CurrentRunPageWriter.footer(localPrintWriter);
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
    }
    finally
    {
      try
      {
        localPrintWriter.close();
      }
      catch (Exception localException3)
      {
        localPrintWriter = null;
      }
    }
  }
  
  public void generateConsolidatedPage()
  {
    PrintWriter localPrintWriter = null;
    try
    {
      localPrintWriter = new PrintWriter(Directory.RESULTSDir + Directory.SEP + "ConsolidatedPage.html");
      ConsolidatedReportsPageWriter.header(localPrintWriter);
      ConsolidatedReportsPageWriter.menuLink(localPrintWriter, this.runCount);
      ConsolidatedReportsPageWriter.content(localPrintWriter);
      //ConsolidatedReportsPageWriter.footer(localPrintWriter);
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
    }
    finally
    {
      try
      {
        localPrintWriter.close();
      }
      catch (Exception localException3)
      {
        localPrintWriter = null;
      }
    }
  }
  
  public void startReportingForPassed(List<ITestResult> paramList)
  {
    PrintWriter localPrintWriter = null;
    Iterator localIterator = paramList.iterator();
    /*for (;;)
    {*/
    	while (localIterator.hasNext())
      {
        ITestResult localITestResult = (ITestResult)localIterator.next();
        String str = localITestResult.getAttribute("reportDir").toString();
        try
        {
          localPrintWriter = new PrintWriter(str + Directory.SEP + localITestResult.getName() + ".html");
          TestCaseReportsPageWriter.header(localPrintWriter, localITestResult);
          TestCaseReportsPageWriter.menuLink(localPrintWriter, localITestResult, 0);
          TestCaseReportsPageWriter.content(localPrintWriter, localITestResult, this.runCount);
          //TestCaseReportsPageWriter.footer(localPrintWriter);
          try
          {
            localPrintWriter.close();
          }
          catch (Exception localException1)
          {
            localPrintWriter = null;
          }
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          localFileNotFoundException.printStackTrace();
        }
        finally
        {
          try
          {
            localPrintWriter.close();
          }
          catch (Exception localException3)
          {
            localPrintWriter = null;
          }
        }
      }
   // }
  }
  
  public void startReportingForFailed(List<ITestResult> paramList)
  {
    PrintWriter localPrintWriter = null;
    Iterator localIterator = paramList.iterator();
   // for (;;)
   // {
      while (localIterator.hasNext())
      {
        ITestResult localITestResult = (ITestResult)localIterator.next();
        String str = localITestResult.getAttribute("reportDir").toString();
        try
        {
          localPrintWriter = new PrintWriter(str + Directory.SEP + localITestResult.getName() + ".html");
          TestCaseReportsPageWriter.header(localPrintWriter, localITestResult);
          TestCaseReportsPageWriter.menuLink(localPrintWriter, localITestResult, 0);
          TestCaseReportsPageWriter.content(localPrintWriter, localITestResult, this.runCount);
          //TestCaseReportsPageWriter.footer(localPrintWriter);
          try
          {
            localPrintWriter.close();
          }
          catch (Exception localException1)
          {
            localPrintWriter = null;
          }
        }
        catch (FileNotFoundException localFileNotFoundException) {}finally
        {
          try
          {
            localPrintWriter.close();
          }
          catch (Exception localException3)
          {
            localPrintWriter = null;
          }
        }
      }
    //}
  }
  
  public void startReportingForSkipped(List<ITestResult> paramList)
  {
    PrintWriter localPrintWriter = null;
    Iterator localIterator = paramList.iterator();
    //for (;;)
    //{
      while (localIterator.hasNext())
      {
        ITestResult localITestResult = (ITestResult)localIterator.next();
        String str = localITestResult.getAttribute("reportDir").toString();
        try
        {
          localPrintWriter = new PrintWriter(str + Directory.SEP + localITestResult.getName() + ".html");
          TestCaseReportsPageWriter.header(localPrintWriter, localITestResult);
          TestCaseReportsPageWriter.menuLink(localPrintWriter, localITestResult, 0);
          TestCaseReportsPageWriter.content(localPrintWriter, localITestResult, this.runCount);
          //TestCaseReportsPageWriter.footer(localPrintWriter);
          try
          {
            localPrintWriter.close();
          }
          catch (Exception localException1)
          {
            localPrintWriter = null;
          }
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          localFileNotFoundException.printStackTrace();
        }
        finally
        {
          try
          {
            localPrintWriter.close();
          }
          catch (Exception localException3)
          {
            localPrintWriter = null;
          }
        }
      }
    //}
  }
  
  public void onExecutionFinish()
  {
    Attributes.setAttribute("endExecution", Long.valueOf(System.currentTimeMillis()));
    if (Directory.recordSuiteExecution) {
      try
      {
        //this.recorder.stop();
      }
      catch (Throwable localThrowable)
      {
        throw new IllegalStateException(localThrowable);
      }
    }
  }
  
  private void initChecking()
  {
    try
    {
      Directory.verifyRequiredFiles();
      SettingsFile.correctErrors();
      this.runCount = (Integer.parseInt(SettingsFile.get("run").trim()) + 1);
      SettingsFile.set("run", "" + this.runCount);
      Directory.RUNDir += this.runCount;
      Directory.mkDirs(Directory.RUNDir);
      if (Directory.recordSuiteExecution) {
        try
        {
          //this.recorder = new ATUTestRecorder(Directory.RUNDir, "ATU_CompleteSuiteRecording", Boolean.valueOf(false));
          //this.recorder.start();
        }
        catch (Throwable localThrowable)
        {
          throw new IllegalStateException(localThrowable);
        }
      }
    }
    catch (Exception localException)
    {
      throw new IllegalStateException(localException);
    }
  }
  
  public void onExecutionStart()
  {
    Attributes.setAttribute("startExecution", Long.valueOf(System.currentTimeMillis()));
    initChecking();
  }
  
  public void generateReport(List<XmlSuite> paramList, List<ISuite> paramList1, String paramString)
  {
    Iterator localIterator = paramList1.iterator();
    while (localIterator.hasNext())
    {
      ISuite localISuite = (ISuite)localIterator.next();
      Attributes.setSuiteNameMapper(localISuite.getName());
      startCreatingDirs(localISuite);
      onFinish();
    }
  }
  
  public void onFinish(ISuite paramISuite) {}
  
  public void onStart(ISuite paramISuite) {
  }
  
  
}


