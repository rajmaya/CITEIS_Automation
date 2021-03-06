package com.cisco.cat.reports.writers;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.cisco.cat.reports.CATReports;
import com.cisco.cat.reports.enums.ReportLabels;
import com.cisco.cat.reports.utils.Attributes;
import com.cisco.cat.reports.utils.Directory;
import com.cisco.cat.reports.utils.Platform;
import com.cisco.cat.reports.utils.Utils;

public class CurrentRunPageWriter
  extends ReportsPage
{
  public CurrentRunPageWriter() {}
  
  public static void menuLink(PrintWriter paramPrintWriter, int paramInt)
  {
    paramPrintWriter.println("\n            <tr id=\"container\">\n                <td id=\"menu\">\n                    <ul> \n");
    paramPrintWriter.println(" <li style=\"padding-top: 4px;\"><a href=\"../ConsolidatedPage.html\" >Consolidated Page</a></li>\n");
    if (paramInt == 1) {
      paramPrintWriter.println("\n <li class=\"menuStyle\"><a href=\"" + Directory.RUNName + paramInt + Directory.SEP + "CurrentRun.html\" >" + "Run " + paramInt + " </a></li>\n");
    } else {
      for (int i = 1; i <= paramInt; i++)
      {
        if (i == paramInt)
        {
          paramPrintWriter.println("\n <li style=\"padding-top: 4px;padding-bottom: 4px;\"><a href=\"" + Directory.RUNName + i + Directory.SEP + "CurrentRun.html\" >" + "Run " + i + " </a></li>\n");
          break;
        }
        paramPrintWriter.println("\n <li class=\"menuStyle\"><a href=\"" + Directory.RUNName + i + Directory.SEP + "CurrentRun.html\" >" + "Run " + i + " </a></li>\n");
      }
    }
    paramPrintWriter.println("\n                    </ul>\n                </td>\n\n");
  }
  
  public static String getExecutionTime(ITestResult paramITestResult)
  {
    long l = paramITestResult.getEndMillis() - paramITestResult.getStartMillis();
    if (l > 1000L)
    {
      l /= 1000L;
      return l + " Sec";
    }
    return l + " Milli Sec";
  }
  
  public static String getExecutionTime(long paramLong1, long paramLong2)
  {
    long l = paramLong2 - paramLong1;
    if (l > 1000L)
    {
      l /= 1000L;
      return l + " Sec";
    }
    return l + " Milli Sec";
  }
  
  public static void content(PrintWriter paramPrintWriter, List<ITestResult> paramList1, List<ITestResult> paramList2, List<ITestResult> paramList3, List<ITestResult> paramList4, List<ITestResult> paramList5, List<ITestResult> paramList6, int paramInt, long paramLong1, long paramLong2)
  {
    int i = paramList1.size() + paramList2.size() + paramList3.size();
    paramPrintWriter.println("<td id=\"content\">\n                    <div class=\"info\">\n                        The following pie chart demonstrates the percentage of Passed, Failed and Skipped Test Cases<br/>\n                        Time Taken for Executing below Test Cases: <b>" + getExecutionTime(paramLong1, paramLong2) + "</b> <br/>\n" + "                        Current Run Number: <b>Run " + paramInt + "</b>\n" + "                    </div>\n" + "<div class=\"info\">" + "<br/>" + "<b>Run Description</b><br/><br/><h>Test Suite Name : </h><b>'" + CATReports.currentRunDescription + "'</b></div>" + "                    <div>\n" + "                        <div class=\"chartStyle summary\" style=\"width: 32%;background-color: #3B9C9C;\">\n" + "                            <b>Summary</b><br/><br/>\n" + "                            <table>\n" + "                            <tr>\n" + "                                <td>Execution Date</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + Utils.getCurrentTime() + "</td>\n" + "                            </tr>\n" + "                            <tr>\n" + "                                <td>Total Test Cases</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + i + "</td>\n" + "                            </tr>\n" + "                            <tr>\n" + "                                <td>Passed</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + paramList1.size() + "</td>\n" + "                            </tr>\n" + "                            \n" + "                            <tr>\n" + "                                <td>Failed</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + paramList2.size() + "</td>\n" + "                            </tr>\n" + "\n" + "                            <tr>\n" + "                                <td>Skipped</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + paramList3.size() + "</td>\n" + "                            </tr>\n" + "                        </table> \n" + "                        </div>" + "                        <div class=\"chartStyle\" style=\"text-align: left;margin-left: 30px;float: left;width: 60%;\">                        \n" + "                            <div id=\"chart\" style=\"height:300px;color:black;\"></div>\n" + "                        </div>\n" + "                    </div>\n" + "                    <div>\n");
    if (Directory.recordSuiteExecution) {
      paramPrintWriter.println("<p id=\"showmenu\">Click Me to Show/Hide the Execution Video</p><div id=\"video\" class=\"video\"><object classid=\"clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921\" codebase=\"http://downloads.videolan.org/pub/videolan/vlc/latest/win32/axvlc.cab\" width=\"400\" height=\"300\" id=\"vlc\" events=\"True\">  <param name=\"Src\" value=\"Recording" + Directory.SEP + "CAT_CompleteSuiteRecording" + ".mov\"></param>" + "  <param name=\"ShowDisplay\" value=\"True\" ></param>" + "    <param name=\"AutoLoop\" value=\"no\"></param>" + "    <param name=\"AutoPlay\" value=\"no\"></param>" + "    <embed type=\"application/x-google-vlc-plugin\" name=\"vlcfirefox\" autoplay=\"no\" loop=\"no\" width=\"99%\"  height=\"100%\" target=\"" + "Recording" + Directory.SEP + "CAT_CompleteSuiteRecording" + ".mov\"></embed>" + " </object>" + "</div>");
    } else {
      //paramPrintWriter.println("<p id=\"showmenu\">No Video Recording Available</p>");
    }
    paramPrintWriter.println("<div style=\"float:left;  color: #585858; font-size: 14px;\">\t<select id=\"tcFilter\" class=\"filter\">\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"all\">All Methods</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"tests\">Test Methods</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"pass\">Passed Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"fail\">Failed Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"skip\">Skipped Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"config\">Configuration Methods</option>\n\t\t\t\t\t</select>\tFilter Methods &nbsp;</div>");
    paramPrintWriter.println("<div style=\"float:left;  color: #585858; font-size: 14px;\">\t<select id=\"suiteFilter\" class=\"filter\">\n<option class=\"filterOption\" value=\"all\">All Suites</option>\n");
    Iterator localIterator = Attributes.getSuiteNameMapperMap().keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      paramPrintWriter.println("<option class=\"filterOption\" value=\"" + Attributes.getSuiteNameMapperMap().get(str) + "\">" + str + "</option>\n");
    }
    paramPrintWriter.println("</select>Filter Suites&nbsp;&nbsp;</div>");
    paramPrintWriter.println("                        <table id=\"tableStyle\" class=\"chartStyle\" style=\"height:50px; float: left\">\n                            <tr>\n                                       <th>Machine</th>\n <th>Browser</th>\n <th>Class Name</th>\n                                <th>Method Type</th>\n                                <th>Test Case Name</th>\n                                <th>Time</th>\n                                <th style=\"width: 7%\">Status</th>\n                            </tr>\n");
    writePassedData(paramPrintWriter, paramList1, paramInt);
    writeFailedData(paramPrintWriter, paramList2, paramInt);
    writeSkippedData(paramPrintWriter, paramList3, paramInt);
    writePassedData(paramPrintWriter, paramList4, paramInt);
    writeFailedData(paramPrintWriter, paramList5, paramInt);
    writeSkippedData(paramPrintWriter, paramList6, paramInt);
    paramPrintWriter.print("                        </table>\n                    </div>\n                </td>\n            </tr>");
  }
  
  private static void writePassedData(PrintWriter paramPrintWriter, List<ITestResult> paramList, int paramInt)
  {
    String str = "pass";
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ITestResult localITestResult = (ITestResult)localIterator.next();
      str = "pass " + getSuiteNameMapper(localITestResult);
      if (!localITestResult.getMethod().isTest()) {
        str = "config " + getSuiteNameMapper(localITestResult);
      }
			paramPrintWriter
					.print("<tr class=\"all "
							+ str
							+ "\">\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ localITestResult.getAttribute(Platform.HOST_NAME_PROP)
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ localITestResult.getAttribute(
									Platform.BROWSER_NAME_PROP).toString()
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ getClassName(localITestResult)
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ getMethodType(localITestResult)
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ getTestCaseName(localITestResult)
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ getExecutionTime(localITestResult)
							+ "</a></td>\n"
							+ "<td><img  style=\"border: none; width: 25px\" src=\"../../HTML_Design_Files/IMG/pass.png\"></td>\n"
							+ "</tr>\n");
    }
  }
  
  private static void writeFailedData(PrintWriter paramPrintWriter, List<ITestResult> paramList, int paramInt)
  {
    String str = "fail";
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ITestResult localITestResult = (ITestResult)localIterator.next();
      str = "fail " + getSuiteNameMapper(localITestResult);
      if (!localITestResult.getMethod().isTest()) {
        str = "config " + getSuiteNameMapper(localITestResult);
      }
			paramPrintWriter
					.print("<tr class=\"all "+ str
							+ "\">\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ localITestResult.getAttribute(Platform.HOST_NAME_PROP)
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ localITestResult.getAttribute(
									Platform.BROWSER_NAME_PROP).toString()
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ getClassName(localITestResult)
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ getMethodType(localITestResult)
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ getTestCaseName(localITestResult)
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ getExecutionTime(localITestResult)
							+ "</a></td>\n"
							+ "<td><img  style=\"border: none;width: 25px\" src=\"../../HTML_Design_Files/IMG/fail.png\"></td>\n"
							+ "</tr>\n");
			
    }
  }
  
  private static void writeSkippedData(PrintWriter paramPrintWriter, List<ITestResult> paramList, int paramInt)
  {
    String str = "skip";
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ITestResult localITestResult = (ITestResult)localIterator.next();
      str = "skip " + getSuiteNameMapper(localITestResult);
      if (!localITestResult.getMethod().isTest()) {
        str = "config " + getSuiteNameMapper(localITestResult);
      }
			paramPrintWriter
					.print("<tr class=\"all "
							+ str
							+ "\">\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ localITestResult.getAttribute(Platform.HOST_NAME_PROP)
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ localITestResult.getAttribute(
									Platform.BROWSER_NAME_PROP).toString()
							+ "</a></td>\n"
							+ " <td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ getClassName(localITestResult)
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ getMethodType(localITestResult)
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ getTestCaseName(localITestResult)
							+ "</a></td>\n"
							+ "<td><a href=\""
							+ getTestCaseHTMLPath(localITestResult, paramInt)
							+ "\">"
							+ getExecutionTime(localITestResult)
							+ "</a></td>\n"
							+ "<td><img  style=\" border: none;width: 25px\" src=\"../../HTML_Design_Files/IMG/skip.png\"></td>\n"
							+ "</tr>\n");
    }
  }
  
  public static String getSuiteName(ITestResult paramITestResult)
  {
    return paramITestResult.getTestContext().getSuite().getName();
  }
  
  public static String getSuiteNameMapper(ITestResult paramITestResult)
  {
    return Attributes.getSuiteNameMapper(paramITestResult.getTestContext().getSuite().getName());
  }
  
  public static String getTestCaseHTMLPath(ITestResult paramITestResult, int paramInt)
  {
    String str1 = paramITestResult.getAttribute("reportDir").toString();
    int i = (Directory.RUNName + paramInt).length();
    String str2 = str1.substring(str1.indexOf(Directory.RUNName + paramInt) + (i + 1));
    return str2 + Directory.SEP + getTestCaseName(paramITestResult) + ".html";
  }
  
  public static String getPackageName(ITestResult paramITestResult)
  {
    try
    {
      return paramITestResult.getTestClass().getRealClass().getPackage().getName();
    }
    catch (NullPointerException localNullPointerException) {}
    return "";
  }
  
  public static String getClassName(ITestResult paramITestResult)
  {
    return paramITestResult.getTestClass().getRealClass().getSimpleName();
  }
  
  public static String getIteration(ITestResult paramITestResult)
  {
    return paramITestResult.getAttribute("iteration").toString();
  }
  
  public static String getTestCaseName(ITestResult paramITestResult)
  {
    return paramITestResult.getName();
  }
  
  public static String getReportDir(ITestResult paramITestResult)
  {
    String str1 = paramITestResult.getTestContext().getSuite().getName();
    String str2 = paramITestResult.getTestContext().getCurrentXmlTest().getName();
    String str3 = paramITestResult.getTestClass().getName().replace(".", Directory.SEP);
    String str4 = paramITestResult.getMethod().getMethodName();
    str4 = str4 + "_Iteration" + paramITestResult.getMethod().getCurrentInvocationCount();
    String str5 = str1 + Directory.SEP + str2 + Directory.SEP + str3 + Directory.SEP + str4;
    return str5;
  }
  
  public static String getMethodType(ITestResult paramITestResult)
  {
    ITestNGMethod localITestNGMethod = paramITestResult.getMethod();
    if (localITestNGMethod.isAfterClassConfiguration()) {
      return "After Class";
    }
    if (localITestNGMethod.isAfterGroupsConfiguration()) {
      return "After Groups";
    }
    if (localITestNGMethod.isAfterMethodConfiguration()) {
      return "After Method";
    }
    if (localITestNGMethod.isAfterSuiteConfiguration()) {
      return "After Suite";
    }
    if (localITestNGMethod.isAfterTestConfiguration()) {
      return "After Test";
    }
    if (localITestNGMethod.isBeforeClassConfiguration()) {
      return "Before Class";
    }
    if (localITestNGMethod.isBeforeGroupsConfiguration()) {
      return "Before Groups";
    }
    if (localITestNGMethod.isBeforeMethodConfiguration()) {
      return "Before Method";
    }
    if (localITestNGMethod.isBeforeSuiteConfiguration()) {
      return "Before Suite";
    }
    if (localITestNGMethod.isBeforeTestConfiguration()) {
      return "Before Test";
    }
    if (localITestNGMethod.isTest()) {
      return "Test Method";
    }
    return "Unknown";
  }
  
  public static void header(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.println("<!DOCTYPE html>\n\n<html>\n    <head>\n        <title>Current Run Reports</title>\n\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"../../HTML_Design_Files/CSS/design.css\" />\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"../../HTML_Design_Files/CSS/jquery.jqplot.css\" />\n\n        <script type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/jquery.min.js\"></script>\n        <script type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/jquery.jqplot.min.js\"></script>\n        <!--[if lt IE 9]>\n        <script language=\"javascript\" type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/excanvas.js\"></script>\n        <![endif]-->\n\n        <script language=\"javascript\" type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/jqplot.pieRenderer.min.js\"></script>\n        <script type=\"text/javascript\" src=\"pieChart.js\"></script>\n");
    paramPrintWriter.print("<script language=\"javascript\" type=\"text/javascript\">$(document).ready(function() { $(\".video\").hide();$(\"#showmenu\").show(); $('#showmenu').click(function(){ $('.video').toggle(\"slide\"); }); });</script><style>#showmenu{text-align:center; padding-top:350px;color: #585858; font-size: 14px;}#video{height: 550px;    margin-top: 5px;    width: 97%;    border-style: solid;    border-width: 1px;    border-color: #21ABCD;    /* Shadow for boxes */    -moz-box-shadow: 0 0 10px #CCCCCC;    -ms-box-shadow: 0 0 10px #CCCCCC;    -webkit-box-shadow: 0 0 10px #CCCCCC;    box-shadow: 0 0 10px #CCCCCC;    zoom: 1;    filter: progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=0),        progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=90),        progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=180),        progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=270);    background-color: white;}</style>");
    paramPrintWriter.println("<script language=\"javascript\" type=\"text/javascript\">\n$(document).ready(function() {\n\t$('#tcFilter').on('change',function(){\n    if($(this).val()=='pass'){\n        $('.pass').show();\n\t\t$('.fail').hide();\n\t\t$('.skip').hide();\n\t\t$('.config').hide();\n    }\n\tif($(this).val()=='fail'){\n        $('.pass').hide();\n\t\t$('.fail').show();\n\t\t$('.skip').hide();\n\t\t$('.config').hide();\n    }\n\t\n\tif($(this).val()=='skip'){\n        $('.pass').hide();\n\t\t$('.fail').hide();\n\t\t$('.skip').show();\n\t\t$('.config').hide();\n    }\n\t\nif($(this).val()=='tests'){ $('.pass').show(); $('.fail').show(); $('.skip').show(); $('.config').hide(); }\tif($(this).val()=='config'){\n        $('.pass').hide();\n\t\t$('.fail').hide();\n\t\t$('.skip').hide();\n\t\t$('.config').show();\n    }\n\t\n\tif($(this).val()=='all'){\n        $('.pass').show();\n\t\t$('.fail').show();\n\t\t$('.skip').show();\n\t\t$('.config').show();\n    }\n  });\n});       \n</script>");
    paramPrintWriter.print("<script language=\"javascript\" type=\"text/javascript\">$(document).ready(function() {\t$('#suiteFilter').on('change',function(){if($(this).val()=='all'){      $('.all').show();  }");
    Iterator localIterator = Attributes.getSuiteNameMapperMap().keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      paramPrintWriter.print("if($(this).val()=='" + (String)Attributes.getSuiteNameMapperMap().get(str) + "'){" + "      $('.all').hide();" + "$('." + (String)Attributes.getSuiteNameMapperMap().get(str) + "').show();" + "" + " }");
    }
    paramPrintWriter.println("  }); });</script>");
    paramPrintWriter.println("    </head>\n    <body>\n        <table id=\"mainTable\">\n            <tr id=\"header\" >\n                <td id=\"logo\"></td>\n" + "                <td id=\"headertext\">\n" + "                    " + ReportLabels.HEADER_TEXT.getLabel() + "<div style=\"padding-right:20px;float:right\"><img src=\"../../HTML_Design_Files/IMG/" + ReportLabels.PROJ_LOGO.getLabel() + "\" height=\"70\" width=\"140\" /> </i></div>" + "</td>\n" + "\n" + "            </tr>");
  }
}

