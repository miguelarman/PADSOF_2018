package exceptions;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*
*/
public class OfferIsPendingForChangesExceptions extends Exception {

	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = -8280695311967688040L;
	
	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "The offer must not be pending for changes in order to be approved";
	}

}
