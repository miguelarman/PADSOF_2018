package application.offer;

import java.time.LocalDate;


public class LivingOffer extends Offer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7159402346910731073L;
	private int numberOfMonths;

	public LivingOffer(LocalDate startingDate, Double price, Double deposit, String description, House offeredHouse, int numberOfMonths) {
		super(startingDate, price, deposit, description, offeredHouse);
		this.numberOfMonths = numberOfMonths;
		// TODO Auto-generated constructor stub
	}

	@Override
	public OfferType getType() {
		return OfferType.LIVING;
	}
	
	
	public int getNumberOfMonths() {
		return numberOfMonths;
	}

	public void setNumberOfMonths(int numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}

	@Override
	public String toString() {
		// TODO
		
		return null;
	}
}
