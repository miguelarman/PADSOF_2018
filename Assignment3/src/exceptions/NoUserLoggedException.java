package exceptions;

public class NoUserLoggedException extends Exception {

	private static final long serialVersionUID = -4763300900451782846L;

	@Override
	public String toString() {
		return "There is not a user logged at this moment";
	}
}
