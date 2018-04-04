package exceptions;

public class UnexistentUserException extends Exception {

	private static final long serialVersionUID = -3279063414211584956L;
	private final String id;

	public UnexistentUserException(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "No user with id " + this.id + " could be found in our system";
	}
}
