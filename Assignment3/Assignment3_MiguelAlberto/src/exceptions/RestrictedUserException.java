package exceptions;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*
*/
public class RestrictedUserException extends Exception {

	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = -1335242060942261503L;
	
	/**
	 * NIF of the restricted user
	 */
	private final String nif;
	
	/**
	 * Constructor for the class RestrictedUserException
	 * @param nif NIF of the user that is restricted in the offer
	 */
	public RestrictedUserException(String nif) {
		this.nif = nif;
	}
	
	/**
	 * Getter method for nif
	 * @return NIF of the restricted user
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * Method to generate the string to print the exception message
	 */
	public String toString() {
		return "The user " + this.nif + " cannot book or pay this offer anymore";
	}
}
