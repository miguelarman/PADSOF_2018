package application.users;
import java.util.*;

import application.offer.*;


public class Guest extends RegisteredUser implements GuestI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4989719272790707017L;

	public Guest(String name, String surname, String passwd, String creditCard, String NIF) {
		super(name, surname, passwd, creditCard, NIF);
		reservedOffers = new ArrayList<Reservation>();
	}
	
	public Rol getRol() {
		return Rol.GUEST;
	}
	
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
	

	@Override
	public List<Reservation> getReservedOffers() {
		return this.reservedOffers;
	}
	
	@Override
	public String toString() {
		// TODO
		
		return null;
	}
}
