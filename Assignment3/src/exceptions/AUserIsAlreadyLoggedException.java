package exceptions;

import application.App;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*
*/
public class AUserIsAlreadyLoggedException extends Exception {
	
	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = 7270070045063599714L;

	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "The user " + App.getLoggedUser().getNIF() + " is currently logged.\nPlease log out before trying to access as another user.";
	}

}
