package com.cisco.cat.reports.listeners;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.IConfigurationListener2;
import org.testng.ITestResult;

import com.cisco.cat.reports.utils.Directory;
import com.cisco.cat.reports.writers.TestCaseReportsPageWriter;

public class ConfigurationListener
  implements IConfigurationListener2
{
  static List<ITestResult> passedConfigurations = new ArrayList();
  static List<ITestResult> failedConfigurations = new ArrayList();
  static List<ITestResult> skippedConfigurations = new ArrayList();
  static List<ITestResult> infoCnfigurations = new ArrayList();
  
  public ConfigurationListener() {}
  
  public void onConfigurationFailure(ITestResult paramITestResult)
  {
    if (Directory.generateConfigReports) {
      failedConfigurations.add(paramITestResult);
    }
  }
  
  public void onConfigurationSkip(ITestResult paramITestResult)
  {
    if (Directory.generateConfigReports)
    {
      CATReportsListener.createReportDir(paramITestResult,1);
      skippedConfigurations.add(paramITestResult);
    }
  }
  
  public void onConfigurationSuccess(ITestResult paramITestResult)
  {
    if (Directory.generateConfigReports) {
      passedConfigurations.add(paramITestResult);
    }
  }
  
  public void onConfigurationInfo(ITestResult paramITestResult){
  
  	 if (Directory.generateConfigReports) {
      infoCnfigurations.add(paramITestResult);
    }
  
  }
  
  public static void startConfigurationMethodsReporting(int paramInt)
  {
    startReportingForPassedConfigurations(passedConfigurations, paramInt);
    startReportingForFailedConfigurations(failedConfigurations, paramInt);
    startReportingForSkippedConfigurations(skippedConfigurations, paramInt);
    startReportingForInfoConfigurations(infoCnfigurations, paramInt);
    
  
  }
  
  private static void startReportingForPassedConfigurations(List<ITestResult> paramList, int paramInt)
  {
    PrintWriter localPrintWriter = null;
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        ITestResult localITestResult = (ITestResult)localIterator.next();
        String str = null;
        str = localITestResult.getAttribute("reportDir").toString();
        try
        {
          localPrintWriter = new PrintWriter(str + Directory.SEP + localITestResult.getName() + ".html");
          TestCaseReportsPageWriter.header(localPrintWriter, localITestResult);
          TestCaseReportsPageWriter.menuLink(localPrintWriter, localITestResult, 0);
          TestCaseReportsPageWriter.content(localPrintWriter, localITestResult, paramInt);
          TestCaseReportsPageWriter.footer(localPrintWriter);
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
    }
  }
  
  private static void startReportingForFailedConfigurations(List<ITestResult> paramList, int paramInt)
  {
    PrintWriter localPrintWriter = null;
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        ITestResult localITestResult = (ITestResult)localIterator.next();
        String str = localITestResult.getAttribute("reportDir").toString();
        try
        {
          localPrintWriter = new PrintWriter(str + Directory.SEP + localITestResult.getName() + ".html");
          TestCaseReportsPageWriter.header(localPrintWriter, localITestResult);
          TestCaseReportsPageWriter.menuLink(localPrintWriter, localITestResult, 0);
          TestCaseReportsPageWriter.content(localPrintWriter, localITestResult, paramInt);
          TestCaseReportsPageWriter.footer(localPrintWriter);
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
    }
  }
  
  private static void startReportingForSkippedConfigurations(List<ITestResult> paramList, int paramInt)
  {
    PrintWriter localPrintWriter = null;
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        ITestResult localITestResult = (ITestResult)localIterator.next();
        String str = localITestResult.getAttribute("reportDir").toString();
        try
        {
          localPrintWriter = new PrintWriter(str + Directory.SEP + localITestResult.getName() + ".html");
          TestCaseReportsPageWriter.header(localPrintWriter, localITestResult);
          TestCaseReportsPageWriter.menuLink(localPrintWriter, localITestResult, 0);
          TestCaseReportsPageWriter.content(localPrintWriter, localITestResult, paramInt);
          TestCaseReportsPageWriter.footer(localPrintWriter);
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
    }
  }
  
  
  private static void startReportingForInfoConfigurations(List<ITestResult> paramList, int paramInt)
  {
    PrintWriter localPrintWriter = null;
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        ITestResult localITestResult = (ITestResult)localIterator.next();
        String str = localITestResult.getAttribute("reportDir").toString();
        try
        {
          localPrintWriter = new PrintWriter(str + Directory.SEP + localITestResult.getName() + ".html");
          TestCaseReportsPageWriter.header(localPrintWriter, localITestResult);
          TestCaseReportsPageWriter.menuLink(localPrintWriter, localITestResult, 0);
          TestCaseReportsPageWriter.content(localPrintWriter, localITestResult, paramInt);
          TestCaseReportsPageWriter.footer(localPrintWriter);
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
    }
  }
  
  public void beforeConfiguration(ITestResult paramITestResult)
  {
    CATReportsListener.createReportDir(paramITestResult,1);
  }
}

