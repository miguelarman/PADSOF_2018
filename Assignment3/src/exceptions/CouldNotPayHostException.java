package exceptions;

import application.users.RegisteredUser;

public class CouldNotPayHostException extends Exception {

	private static final long serialVersionUID = 3677339894465064943L;
	private RegisteredUser host;
	private Double amount;

	public CouldNotPayHostException(RegisteredUser host, Double amount) {
		this.host = host;
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "" + this.amount + " euros could not be paid to host with NIF " + this.host.getNIF();
	}
	
}
