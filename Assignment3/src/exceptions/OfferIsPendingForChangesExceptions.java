package exceptions;

import application.offer.Offer;

public class OfferIsPendingForChangesExceptions extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8280695311967688040L;
	public final Offer o;

	public OfferIsPendingForChangesExceptions(Offer o) {
		this.o = o;
	}

	@Override
	public String toString() {
		return "The offer must not be pending for changes in order to be approved";
	}

}
