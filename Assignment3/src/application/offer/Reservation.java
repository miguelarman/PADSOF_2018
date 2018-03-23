package application.offer;

import java.util.*;

import application.users.Guest;
import exceptions.UserTriesToPayReservationWithoutBookingException;
import application.system.System;


public class Reservation {
	
	private Date bookingDate;
	private Guest client;
	private Offer bookedOffer;

	public Reservation(Date bookingDate, Guest client, Offer bookedOffer) {
		this.bookingDate = bookingDate;
		this.client = client;
		this.bookedOffer = bookedOffer;
	}
	
	public Date getBookingDate() {
		return bookingDate;
	}
	
	public Guest getClient() {
		return client;
	}
	
	public Offer getBookedOffer() {
		return bookedOffer;
	}
	
	
	
	public Boolean cancelReservation() {
		
		// TODO
		return true;
		
	}
	
	
	public void payReservation() throws UserTriesToPayReservationWithoutBookingException {

		// comprobar que el usuario que intenta pagar la reserva es el que esta logueado y es el que la ha reservado
		
		if (!this.client.equals(System.getLoggedUser())) {
			throw new UserTriesToPayReservationWithoutBookingException();
		} else {
			this.bookedOffer.payOffer();
		}
		
		// TODO comprobar las excepciones que lanza payoffer
	}
	
	// TODO no se si faltan mas metodos

}
