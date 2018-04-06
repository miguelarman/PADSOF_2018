package exceptions;

import application.users.RegisteredUser;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*
*/
public class UserIsBannedException extends Exception {

	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = -2807037870389388091L;
	
	/**
	 * Banned user
	 */
	private final RegisteredUser user;
	
	/**
	 * Constructor for the class UserIsBannedException
	 * @param user Banned user
	 */
	public UserIsBannedException(RegisteredUser user) {
		this.user = user;
	}
	
	/**
	 * Getter method for user
	 * @return Banned user
	 */
	public RegisteredUser getUser() {
		return user;
	}

	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "The user " + this.user.getName() + " has been banned";
	}
}
