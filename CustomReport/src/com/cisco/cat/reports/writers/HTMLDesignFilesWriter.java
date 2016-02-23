package com.cisco.cat.reports.writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.stream.FileImageOutputStream;

import com.cisco.cat.reports.utils.Directory;

public class HTMLDesignFilesWriter
{
  public HTMLDesignFilesWriter() {}
  
  public static void writeCSS()
  {
    copyFile("styles/design.css", Directory.CSSDir);
    copyFile("styles/jquery.jqplot.css", Directory.CSSDir);
    copyFile("styles/jquery-ui.min.css", Directory.CSSDir);
  }
  
  public static void writeJS()
  {
    copyFile("js/excanvas.js", Directory.JSDir);
    copyFile("js/jqplot.barRenderer.min.js", Directory.JSDir);
    copyFile("js/jqplot.categoryAxisRenderer.min.js", Directory.JSDir);
    copyFile("js/jqplot.highlighter.min.js", Directory.JSDir);
    copyFile("js/jqplot.pieRenderer.min.js", Directory.JSDir);
    copyFile("js/jqplot.pointLabels.min.js", Directory.JSDir);
    copyFile("js/jquery.jqplot.min.js", Directory.JSDir);
    copyFile("js/jquery.min.js", Directory.JSDir);
    copyFile("js/jquery-ui.min.js", Directory.JSDir);
  }
  
  public static void writeIMG()
  {
    copyImage("images/fail.png", Directory.IMGDir);
    copyImage("images/fail_mail.png", Directory.IMGDir);
    copyImage("images/pass.png", Directory.IMGDir);
    copyImage("images/pass_mail.png", Directory.IMGDir);
    copyImage("images/skip.png", Directory.IMGDir);
    copyImage("images/skip_mail.png", Directory.IMGDir);
    copyImage("images/cat.jpg", Directory.IMGDir);
    copyImage("images/loginfo.png", Directory.IMGDir);
    copyImage("images/logpass.png", Directory.IMGDir);
    copyImage("images/logfail.png", Directory.IMGDir);
    copyImage("images/logwarning.png", Directory.IMGDir);
  }
  
  private static void copyImage(String paramString1, String paramString2)
  {
    File localFile = new File(paramString1);
    InputStream localInputStream = HTMLDesignFilesWriter.class.getClassLoader().getResourceAsStream(paramString1);
    FileImageOutputStream localFileImageOutputStream = null;
    try
    {
      localFileImageOutputStream = new FileImageOutputStream(new File(paramString2 + Directory.SEP + localFile.getName()));
      int i = 0;
      while ((i = localInputStream.read()) >= 0) {
        localFileImageOutputStream.write(i);
      }
      localFileImageOutputStream.close();
      return;
    }
    catch (Exception localException2) {
    	System.out.println("HTMLDesignFilesWriter.copyImage()");
    }finally
    {
      try
      {
        localInputStream.close();
        localFileImageOutputStream.close();
        localFile = null;
      }
      catch (Exception localException4)
      {
        localInputStream = null;
        localFileImageOutputStream = null;
        localFile = null;
      }
    }
  }
  
  private static void copyFile(String paramString1, String paramString2)
  {
    File localFile = new File(paramString1);
    InputStream localInputStream = HTMLDesignFilesWriter.class.getClassLoader().getResourceAsStream(paramString1);
    FileOutputStream localFileOutputStream = null;
    try
    {
      localFileOutputStream = new FileOutputStream(paramString2 + Directory.SEP + localFile.getName());
      int i = 0;
      while ((i = localInputStream.read()) >= 0) {
        localFileOutputStream.write(i);
      }
      return;
    }
    catch (FileNotFoundException localFileNotFoundException) {}catch (IOException localIOException) {}finally
    {
      try
      {
        localInputStream.close();
        localFileOutputStream.close();
        localFile = null;
      }
      catch (Exception localException4)
      {
        localInputStream = null;
        localFileOutputStream = null;
        localFile = null;
      }
    }
  }
}

