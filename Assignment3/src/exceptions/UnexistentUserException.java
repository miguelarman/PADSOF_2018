package exceptions;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*
*/
public class UnexistentUserException extends Exception {
	
	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = -3279063414211584956L;
	
	/**
	 * ID that is not in our app
	 */
	private final String id;

	/**
	 * Constructor for the class UnexistentUserException
	 * @param id ID that is not in our app
	 */
	public UnexistentUserException(String id) {
		this.id = id;
	}
	
	/**
	 * Getter method for id
	 * @return ID that is not in our app
	 */
	public String getId() {
		return id;
	}


	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "No user with id " + this.id + " could be found in our system";
	}
}
