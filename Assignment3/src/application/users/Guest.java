package application.users;
import java.util.*;

import application.offer.Reservation;


public class Guest extends RegisteredUser {
	
	private List<Reservation> reservedOffers;

	
	public Guest(String name, String surname, String passwd, String creditCard) {
		super(name, surname, passwd, creditCard);
		reservedOffers = new ArrayList<Reservation>();
	}
	
	public Rol getRol() {
		return Rol.GUEST;
	}

	
	public List<Reservation> getReservedOffers() {
		return reservedOffers;
	}
	
	public Boolean addReservation(Reservation reservation) {
		// TODO
		return true;
	}
	
	public Boolean deleteReservation(Reservation reservation) {
		// TODO
		return true;
		
	}
	

}
