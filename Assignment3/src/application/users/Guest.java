package application.users;
import java.util.*;

import application.offer.*;

/**
 * Class that stores all the data of a Guest. It is a subclass of RegisteredUser,
 * and implements GuestI
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez
 *         (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public class Guest extends RegisteredUser implements GuestI{
	
	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = 4989719272790707017L;

	/**
	 * Constructor of the class Guest
	 * 
	 * @param name Name of the guest
	 * @param surname Surname of the guest
	 * @param passwd Password of the guest
	 * @param creditCard Credit card of the guest
	 * @param NIF NIF of the guest
	 */
	public Guest(String name, String surname, String passwd, String creditCard, String NIF) {
		super(name, surname, passwd, creditCard, NIF);
		reservedOffers = new ArrayList<Reservation>();
	}
	
	
	/**
	 * Method that returns the role of the user. Inherited from the superclass
	 * RegisteredUser
	 * 
	 * @return The role of the user. Always guest
	 */
	public Role getRole() {
		return Role.GUEST;
	}
	
	@Override
	/**
	 * Method that adds a reservation to the list of reservations of the user
	 * 
	 * @param reservation Reservation to be added
	 */
	public void addReservation(Reservation reservation) {
		this.reservedOffers.add(reservation);
		reservation.getBookedOffer().modifyOffer(OfferStatus.BOOKED);
	}
	
	@Override
	/**
	 * Method that deletes a reservation from the list of reservations of the user
	 * 
	 * @param reservation Reservation to be deleted
	 */
	public void deleteReservation(Reservation reservation) {		
		reservation.getBookedOffer().modifyOffer(OfferStatus.APPROVED);
		
		this.reservedOffers.remove(reservation);		
	}
	

	@Override
	/**
	 * Method that returns the list of reserved offers by the guest
	 * 
	 * @return List with all the reserved offers
	 */
	public List<Reservation> getReservedOffers() {
		return this.reservedOffers;
	}
	
}
