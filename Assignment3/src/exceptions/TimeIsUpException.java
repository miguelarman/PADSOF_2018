package exceptions;

public class TimeIsUpException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6220347829579357136L;
	private final String method;
	
	public TimeIsUpException(String method) {
		this.method = method;
	}
	
	public String toString() {
		return "The period of 5 days to execute the action " + this.method + " is already over";
	}

}
