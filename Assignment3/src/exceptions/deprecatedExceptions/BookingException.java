package exceptions.deprecatedExceptions;

public class BookingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1480868648417992778L;
	
	public BookingException (String description) {
		super(description);
	}
	
	// TODO no se si hace falta algo mas
}
