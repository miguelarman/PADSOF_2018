package exceptions;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*
*/
public class OfferAlreadyCreatedException extends Exception{

	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = -8425575652000587826L;
	
	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "The offer has aready been created";
	}

}
