package application.offer;

import java.time.LocalDate;

import application.app.App;
import application.users.Guest;

import exceptions.*;


public class Reservation {
	
	private LocalDate bookingDate;
	private Guest client;
	private Offer bookedOffer;

	public Reservation(LocalDate bookingDate, Guest client, Offer bookedOffer) {
		this.bookingDate = bookingDate;
		this.client = client;
		this.bookedOffer = bookedOffer;
	}
	
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	
	public Guest getClient() {
		return client;
	}
	
	public Offer getBookedOffer() {
		return bookedOffer;
	}
	
	
	
	public void cancelReservation() {
				
		Guest user = this.client;
		
		user.deleteReservation(this);
		
		// esto no se si sirve para algo pero por si acaso lo pongo todo a null
		this.bookedOffer = null;
		this.client = null;
		this.bookingDate = null;
	}
	
	
	public void payReservation() throws NotTheReserverException {

		// comprobar que el usuario que intenta pagar la reserva es el que esta logueado y es el que la ha reservado
		
		if (!this.client.equals(App.getLoggedUser())) {
			throw new NotTheReserverException(this, App.getLoggedUser());
		} else {
			try {
				this.bookedOffer.payOffer();
			} catch (NoUserLoggedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String toString() {
		// TODO
		
		return null;
	}

}
