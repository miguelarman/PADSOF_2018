package application.users;
import java.util.*;

import application.offer.OfferStatus;
import application.offer.Reservation;


public class Guest extends RegisteredUser {
	
	private List<Reservation> reservedOffers;

	
	public Guest(String name, String surname, String passwd, String creditCard, String NIF) {
		super(name, surname, passwd, creditCard, NIF);
		reservedOffers = new ArrayList<Reservation>();
	}
	
	public Rol getRol() {
		return Rol.GUEST;
	}

	
	public List<Reservation> getReservedOffers() {
		return reservedOffers;
	}
	
	public void addReservation(Reservation reservation) {
		// TODO comprobar algo de las fechas?
		// TODO comprobar el estado de la oferta?
		
		this.reservedOffers.add(reservation);
	}
	
	public void deleteReservation(Reservation reservation) {
		// TODO mirar algo con las fechas?
		
		reservation.getBookedOffer().modifyOffer(OfferStatus.APPROVED);
		
		this.reservedOffers.remove(reservation);		
	}
	
}
