/**
 * 
 */
package application.offer;
import java.util.*;

import application.users.Guest;

/**
 * @author eps
 *
 */
public class Reservation {
	
	private Date bookingDate;
	private Guest client;
	private Offer bookedOffer;
	/**
	 * @param bookingDate
	 * @param client
	 * @param bookedOffer
	 */
	public Reservation(Date bookingDate, Guest client, Offer bookedOffer) {
		this.bookingDate = bookingDate;
		this.client = client;
		this.bookedOffer = bookedOffer;
	}
	/**
	 * @return the bookingDate
	 */
	public Date getBookingDate() {
		return bookingDate;
	}
	/**
	 * @return the client
	 */
	public Guest getClient() {
		return client;
	}
	/**
	 * @return the bookedOffer
	 */
	public Offer getBookedOffer() {
		return bookedOffer;
	}
	
	public Boolean cancelReservation() {
		return true;
		
	}
	
	public Boolean payReservation() {
		return true;
	}
	

}
