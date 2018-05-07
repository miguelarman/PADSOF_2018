package application.users;

import java.util.List;

import application.offer.Reservation;

/**
 * Interface that models the functionality required for Guests in the app
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez
 *         (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public interface GuestI {
	
	/**
	 * Method that returns the list of reserved offers by the guest
	 * 
	 * @return List with all the reserved offers
	 */
	public List<Reservation> getReservedOffers();
	
	/**
	 * Method that adds a reservation to the list of reservations of the user
	 * 
	 * @param reservation Reservation to be added
	 */
	public void addReservation(Reservation reservation);
	
	/**
	 * Method that deletes a reservation from the list of reservations of the user
	 * 
	 * @param reservation Reservation to be deleted
	 */
	public void deleteReservation(Reservation reservation);
}
