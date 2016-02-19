package com.cisco.cat.reports.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class MethodListener
  implements IInvokedMethodListener
{
  public MethodListener() {}
  
  public void afterInvocation(IInvokedMethod paramIInvokedMethod, ITestResult paramITestResult) {}
  
  public void beforeInvocation(IInvokedMethod paramIInvokedMethod, ITestResult paramITestResult)
  {
    if ((!paramIInvokedMethod.isConfigurationMethod()) || (paramIInvokedMethod.isTestMethod()))
    {
      CATReportsListener.createReportDir(paramITestResult,1);
      CATReportsListener.setPlatfromBrowserDetails(paramITestResult);
    }
  }
}
