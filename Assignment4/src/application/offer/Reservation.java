package application.offer;

import java.io.Serializable;
import java.time.LocalDate;

import application.App;
import application.dates.ModifiableDate;
import application.users.Guest;
import application.users.MultiRoleUser;
import application.users.RegisteredUser;
import application.users.RegisteredUser.Role;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import exceptions.*;

/**
 * Class that stores all the data needed to book an offer. It uses the classes
 * Offer and Guest, as the are needed to perform the actions of this class
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public class Reservation implements Serializable{
	
	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = 738403020803075461L;
	
	/**
	 * Date in which the offer was booked
	 */
	private LocalDate bookingDate;
	
	/**
	 * Client that booked the offer
	 */
	private RegisteredUser client;
	
	/**
	 * Offer that was booked
	 */
	private Offer bookedOffer;
	
	/**
	 * Constructor of the class Reservation
	 * 
	 * @param client Client that is booking the offer
	 * @param bookedOffer Offer that is being booked
	 */
	public Reservation(RegisteredUser client, Offer bookedOffer) {
		this.bookingDate = ModifiableDate.getModifiableDate();
		this.client = client;
		this.bookedOffer = bookedOffer;
	}
	
	/**
	 * Getter method for the bookingDate attribute
	 * 
	 * @return Booking date of the reservation
	 */
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	
	/**
	 * Getter method for the client attribute
	 * 
	 * @return Client of the reservation
	 */
	public RegisteredUser getClient() {
		return client;
	}
	
	/**
	 * Getter method for the bookedOffer attribute
	 * 
	 * @return Offer of the reservation
	 */
	public Offer getBookedOffer() {
		return bookedOffer;
	}
	
	/**
	 * Method that cancels a Reservation
	 */
	public void cancelReservation() {
		
		if(this.client.getRole().equals(Role.GUEST)) {
			Guest user = (Guest)this.client;
			user.deleteReservation(this);
		}
		else if(this.client.getRole().equals(Role.MULTIROLE)) {
			MultiRoleUser user = (MultiRoleUser)this.client;
			user.deleteReservation(this);
		}		
		// This assigning prevent errors in the future
		this.bookedOffer = null;
		this.client = null;
		this.bookingDate = null;
	}
	
	/**
	 * Method that pays the reservation. It calls the method payOffer()
	 * 
	 * @throws NotTheReserverException When the user trying to pay is not the reserver
	 * @throws InvalidCardNumberException When the credit card is not valid
	 * @throws CouldNotPayHostException When the app could not pay host
	 * @throws TimeIsUpException When the five-day period to pay a reservation has passed
	 * @throws RestrictedUserException 
	 */
	public void payReservation() throws NotTheReserverException, InvalidCardNumberException, CouldNotPayHostException, TimeIsUpException, RestrictedUserException {

		LocalDate changesDate = getBookingDate();
		LocalDate currentDate = ModifiableDate.getModifiableDate();
		
		if (currentDate.minusDays(5).isEqual(changesDate) || currentDate.minusDays(5).isAfter(changesDate)) { // User has exceeded 5 days without paying
			throw new TimeIsUpException("payReservation");
		}
		// We check if the user trying to pay the reservation is the one that booked it
		if (!this.client.equals(App.getLoggedUser())) {
			throw new NotTheReserverException(App.getLoggedUser());
		} else {
			
			for (RegisteredUser u : this.getBookedOffer().getRestrictedUsers()) {
				if (u.getNIF().equals(App.getLoggedUser().getNIF())) {
					throw new RestrictedUserException(u.getNIF());
				}
			}
			
			try {
				this.bookedOffer.payOffer();
			} catch (NoUserLoggedException e) {
				System.out.println(e);
			}
		}
	}
	
	@Override
	/**
	 * Method that returns all the information stored in an object of the class
	 * Reservation in a printable and readable format
	 * 
	 * @return Information stored in the Reservation in a printable format
	 */
	public String toString() {
		return "Reservation for " + this.getBookedOffer().getHouse().getZipCode() + " (" + this.getBookedOffer().getHouse().getCity() + ") booked on " + this.getBookingDate() + " by " + this.getClient().getNIF();
	}

}
