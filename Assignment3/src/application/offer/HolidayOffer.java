package application.offer;

import java.time.LocalDate;

/**
 * Class that stores the data of a holiday offer. It is created from the class Offer
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public class HolidayOffer extends Offer {
	
	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = -4991181617749951314L;
	
	/**
	 * Date when the holiday offer ends
	 */
	private LocalDate finishDate;
	
	
	/**
	 * Constructor of the class HolidayOffer. It uses the constructor of the superclass Offer
	 * 
	 * @param startingLocalDate Starting date of the offer
	 * @param price Price of the offer
	 * @param deposit Deposit to be paid for the offer
	 * @param description Description of the offer
	 * @param offeredHouse House in which the offer takes place
	 * @param finishLocalDate Finishing date of the offer
	 */
	public HolidayOffer(LocalDate startingLocalDate, Double price, Double deposit, String description, House offeredHouse, LocalDate finishLocalDate) {
		super(startingLocalDate, price, deposit, description, offeredHouse);
		this.finishDate = finishLocalDate;
	}

	@Override
	/**
	 * Method that returns the type of the offer
	 * 
	 * @returns It always returns OfferType.HOLIDAY
	 */
	public OfferType getType() {
		return OfferType.HOLIDAY;
	}
	
	/**
	 * Getter for the attribute finishDate
	 * 
	 * @return finish date of the offer
	 */
	
	public LocalDate getFinishLocalDate() {
		return finishDate;
	}
	
	/**
	 * Setter method for the attribute finish date
	 * @param finishDate new finish date of the offer
	 */
	public void setFinishDate(LocalDate finishDate) {
		this.finishDate = finishDate;
	}
}
