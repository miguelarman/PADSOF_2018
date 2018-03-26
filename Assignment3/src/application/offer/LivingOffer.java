package application.offer;

import java.util.Date;


public class LivingOffer extends Offer {

	private int numberOfMonths;

	public LivingOffer(Date startingDate, Double price, Double deposit, String description, OfferStatus status,
			House offeredHouse, int numberOfMonths) {
		super(startingDate, price, deposit, description, status, offeredHouse);
		this.numberOfMonths = numberOfMonths;
		// TODO Auto-generated constructor stub
	}

	@Override
	public OfferType getType() {
		return OfferType.LIVING;
	}
	
	
	@Override
	public String toString() {
		// TODO
		
		return null;
	}
}
