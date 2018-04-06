package exceptions;

import application.users.RegisteredUser;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*
*/
public class NotTheOwnerException extends Exception {
	
	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = 5610869281967074035L;
	
	/**
	 * The user that does not own the house
	 */
	private final RegisteredUser user;

	/**
	 * Constructor for the class NotTheOwnerException
	 * @param user The logged user at that moment 
	 */
	public NotTheOwnerException(RegisteredUser user) {
		this.user= user;
	}
	
	/**
	 * Getter method for user
	 * @return The user that does not own the house
	 */
	public RegisteredUser getUser() {
		return user;
	}

	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "The user " + user.getNIF() + " does not own this house";
	}

}
