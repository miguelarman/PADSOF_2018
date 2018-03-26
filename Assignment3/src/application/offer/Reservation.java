package application.offer;

import java.util.*;

import application.users.Guest;
import exceptions.BookingException;
import exceptions.PaymentException;
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
	
	
	public void payReservation() throws BookingException {

		// comprobar que el usuario que intenta pagar la reserva es el que esta logueado y es el que la ha reservado
		
		if (!this.client.equals(System.getLoggedUser())) {
			throw new BookingException("In order to pay a reservation you must be the one that has booked it");
		} else {
			try {
				this.bookedOffer.payOffer();
			} catch (PaymentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// TODO comprobar las excepciones que lanza payoffer
	}
	
	// TODO no se si faltan mas metodos

}
