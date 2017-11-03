package by.wolterskluwer.rs.exceptions;

public class ExceptionTrace {
	
	public ExceptionTrace() {
	}

	public ExceptionTrace(String trace) {
		this.trace = trace;
	}

	private String trace;
	 
    public String getTrace() {
        return trace;
    }
 
    public void setTrace(String trace) {
        this.trace = trace;
    }
}
