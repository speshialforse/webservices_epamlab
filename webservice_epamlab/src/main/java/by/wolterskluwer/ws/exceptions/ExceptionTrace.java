package by.wolterskluwer.ws.exceptions;

public class ExceptionTrace {
	public ExceptionTrace(String trace) {
		this.trace = trace;
	}

	public ExceptionTrace() {
	}
	
	private String trace;
	 
    public String getTrace() {
        return trace;
    }
 
    public void setTrace(String trace) {
        this.trace = trace;
    }
}
