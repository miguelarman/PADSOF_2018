package application.offer;

import java.time.LocalDate;

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
		// TODO Auto-generated constructor stub
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

	/**
	 * Setter method for the attribute numberOfMonths
	 * 
	 * @param numberOfMonths Number of months of the offer
	 */
	public void setNumberOfMonths(int numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
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
