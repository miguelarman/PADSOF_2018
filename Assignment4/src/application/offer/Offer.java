package application.offer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import application.App;
import application.opinion.*;
import application.users.RegisteredUser;
import exceptions.*;

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
	 * List of suggestions made by the admins
	 */
	private List<String> suggestedChanges;
	/**
	 * All the opinions about the offer
	 */
	private List<Opinion> opinions;
	
	/**
	 * List of users that cannot book this offer, because they did not pay the reservation within 5 days
	 */
	private List<RegisteredUser> restrictedUsers;
	
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
		this.description = description;
		this.status = OfferStatus.PENDING_FOR_APPROVAL;
		this.offeredHouse = offeredHouse;
		this.opinions = new ArrayList<Opinion>();
		this.restrictedUsers = new ArrayList<RegisteredUser>();
		this.suggestedChanges = new ArrayList<String>();
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
	 * Getter method for the deposit field
	 * 
	 * @return The deposit of the offer
	 */
	public Double getDeposit() {
		return this.deposit;
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
	 * Getter method for all the comments
	 * @return List of comments
	 */
	public List<Opinion> getComments() {
		List<Opinion> aux = new ArrayList<Opinion>();
		for(Opinion o: opinions) {
			if (o.getClass() == Comment.class) {
				aux.add(o);
			}
		}
		return aux;
	}
	
	/**
	 * Getter method for the changes suggestions of the offer
	 * 
	 * @return Suggestions made to the offer
	 */
	public List<String> getSuggestedChanges() {
		return this.suggestedChanges;
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
	 * Getter method for the replies attribute
	 * 
	 * @return Opinions of the offer
	 */
	public List<Opinion> getOpinions() {
		return this.opinions;
	}
	
/**

 * Getter method for the restrictedUsers attribute
 * @return List of restricted users
 */
	public List<RegisteredUser> getRestrictedUsers() {
		return restrictedUsers;
	}

	/**
	 * Method used to modify the starting date of the offer
	 * 
	 * @param startingDate New starting date of the offer
	 * @throws NotTheOwnerException When the user trying to modify the offer is not the owner of the house
	 * @throws InvalidOfferStatusException When you try to modify a offer that is not pending for changes
	 */
	public void modifyOffer(LocalDate startingDate) throws InvalidOfferStatusException, NotTheOwnerException{
		if(!this.status.equals(OfferStatus.PENDING_FOR_CHANGES)) {
			throw new InvalidOfferStatusException(this.status, "modifyOffer");
		}
		else if(!App.getLoggedUser().equals(this.offeredHouse.getHost())) {
			throw new NotTheOwnerException(App.getLoggedUser());
		}
		else {
			this.startingDate = startingDate;
		}
	}
	
	/**
	 * Method used to modify the price and deposit of the offer
	 * 
	 * @param price New price of the offer
	 * @param deposit New deposit of the offer
	 * @throws NotTheOwnerException When the user trying to modify the offer is not the owner of the house
	 * @throws InvalidOfferStatusException When you try to modify a offer that is not pending for changes
	 */
	public void modifyOffer(Double price, Double deposit) throws InvalidOfferStatusException, NotTheOwnerException {
		if(!this.status.equals(OfferStatus.PENDING_FOR_CHANGES)) {
			throw new InvalidOfferStatusException(this.status, "modifyOffer");
		}
		else if(!App.getLoggedUser().equals(this.offeredHouse.getHost())) {
			throw new NotTheOwnerException(App.getLoggedUser());
		}
		else {
			this.price = price;
			this.deposit = deposit;
		}
	}
	
	/**
	 * Method used to modify the description date of the offer
	 * 
	 * @param description New description of the offer
	 * @throws NotTheOwnerException When the user trying to modify the offer is not the owner of the house
	 * @throws InvalidOfferStatusException When you try to modify a offer that is not pending for changes
	 */
	public void modifyOffer(String description) throws InvalidOfferStatusException, NotTheOwnerException {
		
		if(!this.status.equals(OfferStatus.PENDING_FOR_CHANGES)) {
			throw new InvalidOfferStatusException(this.status, "modifyOffer");
		}
		else if(!App.getLoggedUser().equals(this.offeredHouse.getHost())) {
			throw new NotTheOwnerException(App.getLoggedUser());
		}
		else {
			this.description = description;
		}

	}
	
	/**
	 * Method used to modify the status of the offer
	 * 
	 * @param status New status of the offer
	 */
	public void modifyOffer(OfferStatus status){
		this.status = status;
	}
	
	/**
	 * Method used to pay for an offer
	 * 
	 * @throws NoUserLoggedException When no user is logged in the app
	 * @throws CouldNotPayHostException When the system could not pay the host
	 * @throws InvalidCardNumberException When the card number of the guest is not 16 digits long
	 */
	public void payOffer() throws NoUserLoggedException, CouldNotPayHostException, InvalidCardNumberException {
		Double amount = this.getAmount();
		
		String subject = "------------";
		
		RegisteredUser user = App.getLoggedUser();
		if (user == null) {
			throw new NoUserLoggedException();
		}
		
		String ccard = user.getCreditCard();
		
		try {
			TeleChargeAndPaySystem.charge(ccard, subject, amount); 
		} catch (InvalidCardNumberException e) {
			throw e;
		} catch (FailedInternetConnectionException e) {
			System.out.println(e);
		} catch (OrderRejectedException e) {
			System.out.println(e);
		}
		
		modifyOffer(OfferStatus.PAID);
		this.payHost();
	}
	
	/**
	 * Method used to pay the host what has been paid by the client minus the system
	 * fees
	 * 
	 * @throws CouldNotPayHostException When the app could not pay the host (invalid card number)
	 */
	public abstract void payHost() throws CouldNotPayHostException;

	/**
	 * Method used to add an opinion about the offer
	 * 
	 * @param opinion Comment about the offer
	 * @throws NoUserLoggedException When a non-logged user tries to comment an offer 
	 */
	public void rateOffer(String opinion) throws NoUserLoggedException {
		Opinion o = new Comment(opinion);
		
		if(App.getLoggedUser() == null) {
			throw new NoUserLoggedException();
		}
		
		this.opinions.add(o);
	}
	
	/**
	 * Method used to add a rating to the offer
	 * 
	 * @param rating Rating of the offer
	 * @throws NoUserLoggedException When a non-logged user tries to rate a offer 
	 */
	public void rateOffer(Double rating) throws NoUserLoggedException {
		Opinion o = new Rating(rating);
		if(App.getLoggedUser() == null) {
			throw new NoUserLoggedException();
		}
		
		this.opinions.add(o);
	}
	
	
	/**
	 * Method that calculates the average rating of the offer
	 * 
	 * @return The average rating of the offer. Calculated from the rating.
	 */
	public Double getAvgRating() {
		
		Double rating = 0.0;
		int amount = 0;
		
		for (Opinion o : this.opinions) {
			if (o.getClass() == Rating.class) {
				rating += ((Rating) o).getRating();
				
				amount++;
			}
		}
		
		if(amount == 0) {
			return 0.0;
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
	 * Offer in a printable and readable format. It prints the data in common
	 * amongst all the types of offers
	 * 
	 * @return Information stored in the Offer in a printable format
	 */
	public String toString() {
		String string = "";
		
		string += "Offer for the house in " + this.getHouse().getZipCode() + " (" + this.getHouse().getCity() + ")";
		string += "\n Description: " + this.description;
		string += "\nStatus: " + this.status;
		string += "\nPrice: " + this.price;
		string += "\nDeposit: " + this.deposit;
		string += "\nStarting date: " + this.startingDate;

		
		return string;
	}

}
