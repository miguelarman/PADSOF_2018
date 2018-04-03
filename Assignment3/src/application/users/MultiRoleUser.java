package application.users;

import java.util.HashMap;
import java.util.List;

import application.offer.House;
import application.offer.OfferStatus;
import application.offer.Reservation;
import exceptions.HostException;

public class MultiRoleUser extends RegisteredUser implements GuestI, HostI {

	public MultiRoleUser(String name, String surname, String passwd, String creditCard, String NIF) {
		super(name, surname, passwd, creditCard, NIF);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rol getRol() {
		return Rol.MULTIROL;
	}

	@Override
	public List<House> getHouses() {
		return this.houses;
	}
	
	@Override
	public List<Reservation> getReservedOffers() {
		return this.reservedOffers;
	}
	
	
	// Host methods (excluding getters)
	@Override
	public void addHouse(Integer zipCode, String city, HashMap<String, String> chs) throws HostException {
		House house = new House(zipCode, city, chs, this);

		for (House h : houses) {
			if (h.equals(house)) {
				throw new HostException("House already created");
			}
		}

		houses.add(house);
	}

	// Guest methods (excluding getters)
	@Override
	public void addReservation(Reservation reservation) {
		// TODO comprobar algo de las fechas?
		// TODO comprobar el estado de la oferta?

		this.reservedOffers.add(reservation);
	}

	@Override
	public void deleteReservation(Reservation reservation) {
		// TODO mirar algo con las fechas?

		reservation.getBookedOffer().modifyOffer(OfferStatus.APPROVED);

		this.reservedOffers.remove(reservation);
	}

}
