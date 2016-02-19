package com.cisco.cat.reports.exceptions;

public class CATReporterStepFailedException extends RuntimeException
{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CATReporterStepFailedException() {}
	  
	  public CATReporterStepFailedException(String paramString) {}
	  
	  public String toString()
	  {
	    return "[CAT Custom Reporter Step Failed Exception]";
	  }

}
