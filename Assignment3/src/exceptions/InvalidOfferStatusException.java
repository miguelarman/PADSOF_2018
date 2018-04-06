package exceptions;

import application.offer.OfferStatus;

public class InvalidOfferStatusException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4643964575401719051L;
	private final String method;
	private final OfferStatus status;
	public InvalidOfferStatusException(OfferStatus status, String method) {
		this.method = method;
		this.status = status;
	}
	@Override
	public String toString() {
		return "A offer with the status " + this.status + " cannot perform the method " + this.method;
	}
}
