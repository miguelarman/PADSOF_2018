package application.offer;

import java.time.LocalDate;

import es.uam.eps.padsof.telecard.*;

import exceptions.CouldNotPayHostException;
import exceptions.InvalidOfferStatusException;
import exceptions.NotTheOwnerException;

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

	@Override
	/**
	 * Method used to pay the host what has been paid by the client minus the system
	 * fees
	 * 
	 * @throws CouldNotPayHostException When the app could not pay the host
	 */
	public void payHost() throws CouldNotPayHostException {
		Double amount = this.getAmount();
		
		String subject = "Payment to host of the house in " + this.getHouse().getZipCode() + " (" + this.getHouse().getCity() + ").Amount: " + amount;
		
		
		String ccard = this.getHouse().getHost().getCreditCard();
		
		amount *= (0.98); // 2% fees
		
		try {
			TeleChargeAndPaySystem.charge(ccard, subject, -amount); // It is negative to pay the host, not charge
		} catch (InvalidCardNumberException e) {
			throw new CouldNotPayHostException(this.getHouse().getHost(), amount);
		} catch (FailedInternetConnectionException e) {
			throw new CouldNotPayHostException(this.getHouse().getHost(), amount);
		} catch (OrderRejectedException e) {
			throw new CouldNotPayHostException(this.getHouse().getHost(), amount);
		}
	}
	
	@Override
	/**
	 * Method that returns all the information stored in an object of the class
	 * HolidayOffer in a printable and readable format.
	 * 
	 * @return Information stored in the HolidayOffer in a printable format
	 */
	public String toString() {
		String string = super.toString();
		
		string += "\nFinish date: " + this.finishDate;
		
		return string;
	}

	/**
	 * Method used to modify both the starting date of the offer. It has the two to
	 * differenciate from the method that modifies the starting date
	 * 
	 * @param startingDate New starting date
	 * @param finishDate New finish date
	 * @throws InvalidOfferStatusException When the offer cannot be modified because it is not pending for changes
	 * @throws NotTheOwnerException When the logged user is not the creator of the offer
	 */
	public void modifyOffer(LocalDate startingDate, LocalDate finishDate) throws InvalidOfferStatusException, NotTheOwnerException {
		this.modifyOffer(startingDate);
		this.finishDate = finishDate;
	}
}