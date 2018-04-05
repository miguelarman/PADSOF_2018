package application.offer;

import java.io.Serializable;
import java.time.LocalDate;

import application.App;
import application.users.Guest;

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
	private Guest client;
	
	/**
	 * Offer that was booked
	 */
	private Offer bookedOffer;
	
	
	/**
	 * Constructor of the class Reservation
	 * 
	 * @param bookingDate Date in which the reservation is created
	 * @param client Client that is booking the offer
	 * @param bookedOffer Offer that is being booked
	 */
	public Reservation(LocalDate bookingDate, Guest client, Offer bookedOffer) {
		this.bookingDate = bookingDate;
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
	public Guest getClient() {
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
				
		Guest user = this.client;
		
		user.deleteReservation(this);
		
		// This assigning prevent errors in the future
		this.bookedOffer = null;
		this.client = null;
		this.bookingDate = null;
	}
	
	
	/**
	 * Method that pays for the reservation. It call the method payOffer()
	 * 
	 * @throws NotTheReserverException When the user trying to pay is not the reserver
	 */
	public void payReservation() throws NotTheReserverException {

		// We check if the user trying to pay the reservation is the one that booked it
		if (!this.client.equals(App.getLoggedUser())) {
			throw new NotTheReserverException(this, App.getLoggedUser());
		} else {
			try {
				this.bookedOffer.payOffer();
			} catch (NoUserLoggedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		// TODO
		
		return null;
	}

}
