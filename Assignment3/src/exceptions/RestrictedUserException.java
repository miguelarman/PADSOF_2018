package exceptions;

public class RestrictedUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1335242060942261503L;
	private final String nif;
	
	public RestrictedUserException(String nif) {
		this.nif = nif;
	}
	
	public String toString() {
		return "The user " + this.nif + " cannot book or pay this offer anymore";
	}
}
