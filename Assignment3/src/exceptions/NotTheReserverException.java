package exceptions;

import application.offer.Reservation;
import application.users.RegisteredUser;

public class NotTheReserverException extends Exception {
	
	private static final long serialVersionUID = 5610869281967074035L;
	@SuppressWarnings("unused")
	private final Reservation reservation;
	private final RegisteredUser user;

	public NotTheReserverException(Reservation reservation, RegisteredUser user) {
		this.reservation = reservation;
		this.user= user;
	}
	
	@Override
	public String toString() {
		return "The user " + user.getName() + " is not who booked this offer";
	}

}
