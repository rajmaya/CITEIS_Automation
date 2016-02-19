package com.cisco.cat.reports.exceptions;

public class CATReporterException extends Exception
{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	  
	  public CATReporterException() {}
	  
	  public CATReporterException(String paramString)
	  {
	    this.message = paramString;
	  }
	  
	  public String toString()
	  {
	    return "[CAT Custom Reporter Exception] " + this.message;
	  }

}
