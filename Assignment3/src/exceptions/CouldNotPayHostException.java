package exceptions;

import application.users.RegisteredUser;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*         
*/
public class CouldNotPayHostException extends Exception {

	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = 3677339894465064943L;
	
	/**
	 * Host that failed to pay to
	 */
	private RegisteredUser host; // Must be registered user as there are multirole users
	
	/**
	 * Amount to be paid
	 */
	private Double amount;

	/**
	 * Constructor of the class CouldNotPayHostException
	 * @param host
	 * @param amount
	 */
	public CouldNotPayHostException(RegisteredUser host, Double amount) {
		this.host = host;
		this.amount = amount;
	}
	
	/**
	 * Getter method for amount
	 * @return the amount that the host has not received
	 */
	public Double getAmount() {
		return this.amount;
	}
	
	/**
	 * Getter method for host
	 * @return host that was not paid
	 */
	public RegisteredUser getHost() {
		return this.host;
	}
	
	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "" + this.amount + " euros could not be paid to host with NIF " + this.host.getNIF();
	}
	
}
