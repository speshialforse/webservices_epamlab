package by.wolterskluwer.rs.exceptions;

@SuppressWarnings("serial")
public class ServiceException extends Exception{

	 private ExceptionTrace exceptionTrace = new ExceptionTrace();
	
	public ServiceException(String message) {
       super(message);
   }

   public ServiceException(String message, Throwable cause) {
       super(message, cause);
   }

   public ServiceException(Throwable cause) {
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

