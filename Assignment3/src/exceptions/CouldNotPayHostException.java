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

	public CouldNotPayHostException(RegisteredUser host, Double amount) {
		this.host = host;
		this.amount = amount;
	}
	
	public Double getAmount() {
		return this.amount;
	}
	
	public RegisteredUser getHost() {
		return this.host;
	}
	
	@Override
	public String toString() {
		return "" + this.amount + " euros could not be paid to host with NIF " + this.host.getNIF();
	}
	
}
