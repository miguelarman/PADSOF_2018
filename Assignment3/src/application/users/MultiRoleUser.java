package application.users;

import java.util.List;

import application.offer.*;

import exceptions.HouseAlreadyCreatedException;

/**
 * Class that contains all the data of a Registered User that is simulatneously
 * Host and Guest. In our implementation we decided that it stores two private
 * users, as to reutilize as much code as possible
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez
 *         (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public class MultiRoleUser extends RegisteredUser implements GuestI, HostI {
	
	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = -5855113958154503955L;
	
	/**
	 * Internal Guest, used to reuse as much code as possible
	 */
	private Guest guestRole;
	
	/**
	 * Internal Host, used to reuse as much code as possible
	 */
	private Host hostRole;
	
	
	/**
	 * Constructor of the class MultiRoleUser
	 * 
	 * @param name Name of the user
	 * @param surname SUrname of the user
	 * @param passwd Password of the user
	 * @param creditCard Credit card of the user
	 * @param NIF NIF of the user
	 */
	public MultiRoleUser(String name, String surname, String passwd, String creditCard, String NIF) {
		super(name, surname, passwd, creditCard, NIF);
		this.guestRole = new Guest(name, surname, passwd, creditCard, NIF);
		this.hostRole = new Host(name, surname, passwd, creditCard, NIF);
	}

	@Override
	/**
	 * Method that returns the houses which the Host has created in the app
	 * 
	 * @return List with all the houses the host has created
	 */
	public List<House> getHouses() {
		return this.hostRole.getHouses();
	}

	@Override
	/**
	 * Method that adds a House to the list of created houses of a Host
	 * 
	 * @param zipCode Zip code of the house
	 * @param city City of the house
	 * @throws HouseAlreadyCreatedException When a house has been created with the same data
	 */
	public void addHouse(Integer zipCode, String city) throws HouseAlreadyCreatedException {
		this.hostRole.addHouse(zipCode, city);
	}

	@Override
	/**
	 * Method that returns the list of reserved offers by the guest
	 * 
	 * @return List with all the reserved offers
	 */
	public List<Reservation> getReservedOffers() {
		return this.guestRole.getReservedOffers();
	}

	@Override
	/**
	 * Method that adds a reservation to the list of reservations of the user
	 * 
	 * @param reservation Reservation to be added
	 */
	public void addReservation(Reservation reservation) {
		this.guestRole.addReservation(reservation);
	}

	@Override
	/**
	 * Method that deletes a reservation from the list of reservations of the user
	 * 
	 * @param reservation Reservation to be deleted
	 */
	public void deleteReservation(Reservation reservation) {
		this.guestRole.deleteReservation(reservation);
	}

	@Override
	/**
	 * Method that returns the rol of the user. Inherited from the superclass
	 * RegisteredUser
	 * 
	 * @returns The rol of the user. Always multirol
	 */
	public Rol getRol() {
		return Rol.MULTIROL;
	}

}
