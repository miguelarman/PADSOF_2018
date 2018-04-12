package exceptions;

import application.users.RegisteredUser;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*
*/
public class NotTheReserverException extends Exception {
	
	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = 5610869281967074035L;
	
	/**
	 * User that is not the reserver
	 */
	private final RegisteredUser user;

	/**
	 * Constructor for the class NotTheReserverException
	 * @param user The user logged at that moment
	 */
	public NotTheReserverException(RegisteredUser user) {
		this.user= user;
	}
	
	/**
	 * Getter method for user
	 * @return User that is not the reserver
	 */
	public RegisteredUser getUser() {
		return user;
	}
	
	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "The user " + user.getNIF() + " has not booked this offer";
	}

}
