package exceptions;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*
*/
public class TimeIsUpException extends Exception {

	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = 6220347829579357136L;
	
	/**
	 * Method that failed
	 */
	private final String method;
	
	/**
	 * Constructor for the class TimeIsUpException
	 * @param method Name of the method that failed
	 */
	public TimeIsUpException(String method) {
		this.method = method;
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
	public String toString() {
		return "The period of 5 days to execute the action " + this.method + " is already over";
	}

}
