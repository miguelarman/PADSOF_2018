package exceptions;

import application.users.RegisteredUser;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*         
*/
public class IncorrectPasswordException extends Exception {
	
	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = -5815002540314877203L;
	/**
	 * User whose password did not match their id
	 */
	private final RegisteredUser user;
	
	/**
	 * Wrong password
	 */
	private final String password;
	
	/**
	 * Constructor for the class IncorrectPasswordException
	 * @param user The user whose password was incorrect
	 * @param passwd Incorrect password
	 */
	public IncorrectPasswordException(RegisteredUser user, String passwd) {
		this.user = user;
		this.password = passwd;
	}
	
	/**
	 * Getter method for user
	 * @return The user whose password was incorrect
	 */
	public RegisteredUser getUser() {
		return user;
	}
	
	/**
	 * Getter method for password
	 * @return Incorrect password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "The password " + this.password + " is not correct for the user " + this.user.getNIF();
	}
}
