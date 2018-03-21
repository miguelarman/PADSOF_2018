/**
 * 
 */
package application.users;
import java.util.*;

import application.offer.Reservation;

/**
 * @author eps
 *
 */
public class Guest extends RegisteredUser {
	
	private List<Reservation> reservedOffers;

	/**
	 * @param name
	 * @param surname
	 * @param passwd
	 * @param creditCard
	 */
	public Guest(String name, String surname, String passwd, String creditCard) {
		super(name, surname, passwd, creditCard);
		reservedOffers = new ArrayList<Reservation>();
	}
	
	public Rol getRol() {
		return Rol.GUEST;
	}

	/**
	 * @return the reservedOffers
	 */
	public List<Reservation> getReservedOffers() {
		return reservedOffers;
	}
	
	public Boolean addReservation(Reservation reservation) {
		return true;
	}
	
	public Boolean deleteReservation(Reservation reservation) {
		return true;
		
	}
	

}
