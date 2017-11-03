package by.wolterskluwer.ws.exceptions;

import javax.xml.ws.WebFault;

import by.wolterskluwer.rs.exceptions.ExceptionTrace;


@SuppressWarnings("serial")
@WebFault
public class WSWebserviceException extends Exception {
		 

	 private ExceptionTrace exceptionTrace = new ExceptionTrace();
	
	public WSWebserviceException(String message) {
      super(message);
  }

  public WSWebserviceException(String message, Throwable cause) {
      super(message, cause);
  }

  public WSWebserviceException(Throwable cause) {
      super(cause);
  }

  public ExceptionTrace getExceptionTrace() {
      exceptionTrace.setTrace(getStringFromTrace(getStackTrace()));
      return exceptionTrace;
  }

  private String getStringFromTrace(StackTraceElement[] stackTrace) {
      StringBuilder builder = new StringBuilder();
      builder.append("\n");
      for (StackTraceElement element : stackTrace) {
          builder.append(String.valueOf(element)).append("\n");
      }
      return builder.toString();
  }

}
