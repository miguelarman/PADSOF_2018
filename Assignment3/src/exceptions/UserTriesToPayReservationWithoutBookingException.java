package exceptions;

public class UserTriesToPayReservationWithoutBookingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1480868648417992778L;
	
	public UserTriesToPayReservationWithoutBookingException () {
		super("In order to pay a reservation you must be the one that has booked it");
	}
	
	// TODO no se si hace falta algo mas
}
