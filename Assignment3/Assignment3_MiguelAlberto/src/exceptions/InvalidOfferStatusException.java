package exceptions;

import application.offer.OfferStatus;


/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*
*/
public class InvalidOfferStatusException extends Exception{

	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = -4643964575401719051L;
	
	/**
	 * Name of the method
	 */
	private final String method;
	
	/**
	 * Current offer status
	 */
	private final OfferStatus status;
	
	/**
	 * Constructor of the class InvalidOfferStatusException
	 * @param status Current status of the offer
	 * @param method Method that is being executed
	 */
	public InvalidOfferStatusException(OfferStatus status, String method) {
		this.method = method;
		this.status = status;
	}
	
	/**
	 * Getter method for status
	 * @return The status of the offer
	 */
	public OfferStatus getStatus() {
		return this.status;
	}
	
	/**
	 * Getter method for method
	 * @return The method that failed
	 */
	public String getMethod() {
		return this.method;
	}
	
	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "A offer with the status " + this.status + " cannot perform the method " + this.method;
	}
}
