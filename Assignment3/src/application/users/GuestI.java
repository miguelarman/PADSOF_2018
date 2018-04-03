package application.users;

import java.util.List;

import application.offer.Reservation;

public interface GuestI {
	public List<Reservation> getReservedOffers();
	public void addReservation(Reservation reservation);
	public void deleteReservation(Reservation reservation);
}
