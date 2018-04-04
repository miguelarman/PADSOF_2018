package exceptions;

import application.users.RegisteredUser;

public class UserIsBannedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2807037870389388091L;
	private final RegisteredUser user;
	
	
	public UserIsBannedException(RegisteredUser user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "The user " + this.user.getName() + " has been banned";
	}
}
