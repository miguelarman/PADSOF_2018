package application.offer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import application.app.App;
import application.opinion.*;
import application.users.RegisteredUser;

import exceptions.NoUserLoggedException;

import es.uam.eps.padsof.telecard.*;

/**
 * This class stores the basic data needed for an Offer. It is abstract, as it
 * is used to create HolidayOffer and LivingOffer
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public abstract class Offer implements Serializable{

	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = -8222183165622361142L;
	
	/**
	 * Starting date of the offer
	 */
	private LocalDate startingDate;
	
	/**
	 * Price of the offer
	 */
	private Double price;
	
	/**
	 * Deposit to be paid for the offer
	 */
	private Double deposit;
	
	/**
	 * Description of the offer
	 */
	private String description;
	
	/**
	 * Status in which the offer is
	 */
	private OfferStatus status;
	
	/**
	 * House in which the offer takes place
	 */
	private House offeredHouse;
	
	/**
	 * All the opinions about the offer
	 */
	private List<Opinion> opinions;
	
	/**
	 * Constructor of the class Offer
	 * 
	 * @param startingDate Starting date of the offer
	 * @param price Price of the offer
	 * @param deposit Deposit to be paid for the offer
	 * @param description Description of the offer
	 * @param offeredHouse House in which the offer takes place
	 */
	public Offer(LocalDate startingDate, Double price, Double deposit, String description, House offeredHouse) {
		this.startingDate = startingDate;
		this.price = price;
		this.deposit = deposit;
		this.status = OfferStatus.PENDING_FOR_APPROVAL;
		this.offeredHouse = offeredHouse;
		this.opinions = new ArrayList<Opinion>();
	}
	
	/**
	 * Getter method for the offeredHouse attribute
	 * 
	 * @return House of the offer
	 */
	public House getHouse() {
		return this.offeredHouse;
	}
	
	/**
	 * Getter method for the status attribute
	 * 
	 * @return Status of the offer
	 */
	public OfferStatus getStatus() {
		return this.status;
	}
	
	/**
	 * Getter method for the startingDate attribute
	 * 
	 * @return Starting date of the offer
	 */
	public LocalDate getDate() {
		return this.startingDate;
	}
	
	/**
	 * Getter method for the description attribute
	 * 
	 * @return Description of the offer
	 */
	public String getDescription() {
		return description;
	}
	
	
	/**
	 * Method used to modify the starting date of the offer
	 * 
	 * @param startingDate New starting date of the offer
	 */
	public void modifyOffer(LocalDate startingDate) {
		this.startingDate = startingDate;
	}
	
	/**
	 * Method used to modify the price and deposit of the offer
	 * 
	 * @param price New price of the offer
	 * @param deposit New deposit of the offer
	 */
	public void modifyOffer(Double price, Double deposit) {
		this.price = price;
		this.deposit = deposit;
	}
	
	/**
	 * Method used to modify the description date of the offer
	 * 
	 * @param description New description of the offer
	 */
	public void modifyOffer(String description) {
		this.description = description;
	}
	
	/**
	 * Method used to modify the status of the offer
	 * 
	 * @param status New status of the offer
	 */
	public void modifyOffer(OfferStatus status) {
		this.status = status;
	}
	
	/**
	 * Method used to pay for an offer
	 * 
	 * @throws NoUserLoggedException When no user is logged in the app
	 */
	public void payOffer() throws NoUserLoggedException {
		Double amount = this.getAmount();
		
		// TODO rellenar el asunto
		String subject = "------------";
		
		RegisteredUser user = App.getLoggedUser();
		if (user == null) {
			throw new NoUserLoggedException();
		}
		
		String ccard = user.getCreditCard();
		
		try {
			TeleChargeAndPaySystem.charge(ccard, subject, amount); // Igual en los catch siguentes debemos lanzar un PaymentException
		} catch (InvalidCardNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FailedInternetConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OrderRejectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO pagar al host
	}
	
	/**
	 * Method used to add an opinion about the offer
	 * 
	 * @param opinion Comment about the offer
	 */
	public void rateOffer(String opinion) {
		Opinion o = new Comment(opinion);
		
		this.opinions.add(o);
	}
	
	/**
	 * Method used to add a rating to the offer
	 * 
	 * @param rating Rating of the offer
	 */
	public void rateOffer(Float rating) {
		Opinion o = new Rating(rating);
		
		this.opinions.add(o);
	}
	
	/**
	 * Method used to add an opinion and a rating about the offer
	 * 
	 * @param opinion Opinion about the offer
	 * @param rating Rating of the offer
	 */
	public void rateOffer(String opinion, int rating) {
		// TODO
		
		// no se como hacer para poner a la vez un comentario de texto y numerico
	}
	
	
	/**
	 * Method that calculates the average rating of the offer
	 * 
	 * @return The average rating of the offer. Calculated from the rating
	 */
	public Float getAvgRating() {
		
		Float rating = (float) 0;
		int amount = 0;
		
		for (Opinion o : this.opinions) {
			if (o.getClass() == Rating.class) {
				rating += ((Rating) o).getRating();
				
				amount++;
			}
		}
		
		return rating / amount;
	}
	
	
	/**
	 * Method that calculates the amount to be paid
	 * 
	 * @return Amount to be paid
	 */
	public Double getAmount() {
		return this.price + this.deposit;
	}
	
	/**
	 * Method that returns the type of the offer. It is abstract as an Offer has no
	 * type unless it is from a subclass
	 * 
	 * @return Type of the offer
	 */
	public abstract OfferType getType();
	
	
	@Override
	/**
	 * Method that returns all the information stored in an object of the class
	 * Offer in a printable and readable format
	 * 
	 * @return Information stored in the Offer in a printable format
	 */
	public String toString() {
		// TODO
		
		return null;
	}

}
