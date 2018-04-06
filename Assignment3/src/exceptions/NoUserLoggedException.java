package exceptions;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*
*/
public class NoUserLoggedException extends Exception {
	
	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = -4763300900451782846L;


	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "There is not a user logged at this moment";
	}
}
