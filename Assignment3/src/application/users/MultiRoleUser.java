package application.users;

import java.util.List;

import application.offer.*;

import exceptions.HouseAlreadyCreatedException;

public class MultiRoleUser extends RegisteredUser implements GuestI, HostI {
	
	// Internal private users used to reuse as much code as possible
	private Guest guestRole;
	private Host hostRole;
	
	public MultiRoleUser(String name, String surname, String passwd, String creditCard, String NIF) {
		super(name, surname, passwd, creditCard, NIF);
		this.guestRole = new Guest(name, surname, passwd, creditCard, NIF);
		this.hostRole = new Host(name, surname, passwd, creditCard, NIF);
	}

	@Override
	public List<House> getHouses() {
		return this.hostRole.getHouses();
	}

	@Override
	public void addHouse(Integer zipCode, String city) throws HouseAlreadyCreatedException {
		this.hostRole.addHouse(zipCode, city);
	}

	@Override
	public List<Reservation> getReservedOffers() {
		return this.guestRole.getReservedOffers();
	}

	@Override
	public void addReservation(Reservation reservation) {
		this.guestRole.addReservation(reservation);
	}

	@Override
	public void deleteReservation(Reservation reservation) {
		this.guestRole.deleteReservation(reservation);
	}

	@Override
	public Rol getRol() {
		return Rol.MULTIROL;
	}

}
