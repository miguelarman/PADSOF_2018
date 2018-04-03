package application.offer;

import java.time.LocalDate;

public class HolidayOffer extends Offer {

	private LocalDate finishDate;

	public HolidayOffer(LocalDate startingLocalDate, Double price, Double deposit, String description, House offeredHouse, LocalDate finishLocalDate) {
		super(startingLocalDate, price, deposit, description, offeredHouse);
		this.finishDate = finishLocalDate;
		// TODO Auto-generated constructor stub
	}

	@Override
	public OfferType getType() {
		return OfferType.HOLIDAY;
	}
	
	
	
	public LocalDate getFinishLocalDate() {
		return finishDate;
	}

	public void setFinishDate(LocalDate finishDate) {
		this.finishDate = finishDate;
	}

}
