package exceptions;

import application.App;

public class AUserIsAlreadyLoggedException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7270070045063599714L;

	public AUserIsAlreadyLoggedException() {}
	
	@Override
	public String toString() {
		return "The user " + App.getLoggedUser().getNIF() + " is currently logged.\nPlease log out before trying to access as another user.";
	}

}
