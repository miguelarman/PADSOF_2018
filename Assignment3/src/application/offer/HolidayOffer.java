package application.offer;

import java.util.Date;

public class HolidayOffer extends Offer {

	private Date finishDate;

	public HolidayOffer(Date startingDate, Double price, Double deposit, String description, OfferStatus status,
			House offeredHouse, Date finishDate) {
		super(startingDate, price, deposit, description, status, offeredHouse);
		this.finishDate = finishDate;
		// TODO Auto-generated constructor stub
	}

	@Override
	public OfferType getType() {
		return OfferType.HOLIDAY;
	}
}
