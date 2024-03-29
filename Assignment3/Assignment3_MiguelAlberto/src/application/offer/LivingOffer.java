package application.offer;

import java.time.LocalDate;

import es.uam.eps.padsof.telecard.*;

import exceptions.CouldNotPayHostException;

/**
 * Class that stores the data of a holiday offer. It is a subclass of Offer
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public class LivingOffer extends Offer {

	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = -7159402346910731073L;
	
	/**
	 * Length in months of the offer
	 */
	private int numberOfMonths;
	
	/**
	 * Constructor of the class LivingOffer
	 * 
	 * @param startingDate Starting date of the offer
	 * @param price Price of the offer
	 * @param deposit Deposit to be paid for the offer
	 * @param description Description of the offer
	 * @param offeredHouse House in which the offer takes place
	 * @param numberOfMonths Length of the offer
	 */
	public LivingOffer(LocalDate startingDate, Double price, Double deposit, String description, House offeredHouse, int numberOfMonths) {
		super(startingDate, price, deposit, description, offeredHouse);
		this.numberOfMonths = numberOfMonths;
	}

	@Override
	/**
	 * Method that returns the type of the offer
	 * 
	 * @returns It always returns OfferType.LIVING
	 */
	public OfferType getType() {
		return OfferType.LIVING;
	}
	
	/**
	 * Getter method for the attribute numberOfMonths
	 * 
	 * @return Length of the offer
	 */
	public int getNumberOfMonths() {
		return numberOfMonths;
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
		
		String subject = "------------";
		
		
		String ccard = this.getHouse().getHost().getCreditCard();
		
		amount *= (0.999); // 0.1% fees
		
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
	 * LivingOffer in a printable and readable format
	 * 
	 * @return Information stored in the LivingOffer in a printable format
	 */
	public String toString() {
		String string = super.toString();
		
		string += "\nNumber of months: " + this.numberOfMonths;
		
		return string;
	}
}
