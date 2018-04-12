package exceptions;

import application.users.RegisteredUser.Role;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*
*/
public class InvalidRolException extends Exception{
	
	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = -1293386080345139163L;

	/**
	 * NIF of the user
	 */
	private final String NIF;
	
	/**
	 * Role of the user
	 */
	private final Role role;
	
	/**
	 * Method that failed
	 */
	private final String method;
	
	/**
	 * Constructor for the class InvalidRolException
	 * @param NIF NIF of the logged user
	 * @param role Role of the logged user
	 * @param method Method that was being called
	 */
	public InvalidRolException(String NIF, Role role, String method) {
		this.NIF = NIF;
		this.role = role;
		this.method = method;
	}
	
	/**
	 * Getter method for NIF
	 * @return The NIF of the user
	 */
	public String getNIF() {
		return NIF;
	}
	
	/**
	 * Getter method for role
	 * @return The role of the user
	 */
	public Role getRole() {
		return role;
	}
	
	/**
	 * Getter method for method
	 * @return Name of the method that failed
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "The user " + this.NIF + " with rol " + this.role + " cannot execute the method " + this.method;
	}

}
