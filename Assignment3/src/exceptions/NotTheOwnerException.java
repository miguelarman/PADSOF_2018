package exceptions;

import application.offer.House;
import application.users.RegisteredUser;

public class NotTheOwnerException extends Exception {
	private static final long serialVersionUID = 5610869281967074035L;
	@SuppressWarnings("unused")
	private final House house;
	private final RegisteredUser user;

	public NotTheOwnerException(House house, RegisteredUser user) {
		this.house = house;
		this.user= user;
	}
	
	@Override
	public String toString() {
		return "The user " + user.getNIF() + " does not own this house";
	}

}
