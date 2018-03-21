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
	private Offer reservedOffer;
	/**
	 * @param bookingDate
	 * @param client
	 * @param reservedOffer
	 */
	public Reservation(Date bookingDate, Guest client, Offer reservedOffer) {
		this.bookingDate = bookingDate;
		this.client = client;
		this.reservedOffer = reservedOffer;
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
	 * @return the reservedOffer
	 */
	public Offer getReservedOffer() {
		return reservedOffer;
	}
	
	public Boolean cancelReservation() {
		return true;
		
	}
	
	public Boolean payReservation() {
		return true;
	}
	

}
