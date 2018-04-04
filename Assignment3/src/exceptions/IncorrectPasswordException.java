package exceptions;

import application.users.RegisteredUser;

public class IncorrectPasswordException extends Exception {

	private final RegisteredUser user;
	private final String password;
	
	
	private static final long serialVersionUID = -5815002540314877203L;
	
	public IncorrectPasswordException(RegisteredUser user, String passwd) {
		this.user = user;
		this.password = passwd;
	}
	
	@Override
	public String toString() {
		return "The password " + this.password + " is not correct for the user " + this.user.getName();
	}
}
