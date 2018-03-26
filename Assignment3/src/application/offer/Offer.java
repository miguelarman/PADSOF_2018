package application.offer;

import java.util.*;

import application.opinion.Comment;
import application.opinion.Opinion;
import application.opinion.Rating;
import application.system.System;
import application.users.RegisteredUser;
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import exceptions.PaymentException;


public abstract class Offer {
	private Date startingDate;
	private Double price;
	private Double deposit;
	private String description;
	private OfferStatus status;
	private House offeredHouse;
	private List<Opinion> opinions;
	
	
	public Offer(Date startingDate, Double price, Double deposit, String description, OfferStatus status,
			House offeredHouse) {
		this.startingDate = startingDate;
		this.price = price;
		this.deposit = deposit;
		this.description = description;
		this.status = OfferStatus.PENDING;
		this.offeredHouse = offeredHouse;
		this.opinions = new ArrayList<Opinion>();
	}
	
	
	public House getHouse() {
		return this.offeredHouse;
	}
	
	public OfferStatus getStatus() {
		return this.status;
	}
	
	public Date getDate() {
		return this.startingDate;
	}
	
	
	
	
	public void modifyOffer(Date startingDate) {
		this.startingDate = startingDate;
	}
	
	public void modifyOffer(Double price, Double deposit) {
		this.price = price;
		this.deposit = deposit;
	}
	
	public void modifyOffer(String description) {
		this.description = description;
	}
	
	public void modifyOffer(OfferStatus status) {
		this.status = status;
	}
	
	
	public void payOffer() throws PaymentException {
		Double amount = this.getAmount();
		
		// TODO rellenar el asunto
		String subject = "------------";
		
		RegisteredUser user = System.getLoggedUser();
		if (user == null) {
			throw new PaymentException("Could not get logged user from System");
		}
		
		String ccard = user.getCreditCard();
		
		try {
			TeleChargeAndPaySystem.charge(ccard, subject, amount);
		} catch (InvalidCardNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FailedInternetConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OrderRejectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rateOffer(String opinion) {
		Opinion o = new Comment(opinion);
		
		this.opinions.add(o);
	}
	
	public void rateOffer(Float rating) {
		Opinion o = new Rating(rating);
		
		this.opinions.add(o);
	}
	
	public void rateOffer(String opinion, int rating) {
		// TODO
	}
	
	public Float getAvgRating() {
		
		Float rating = (float) 0;
		int amount = 0;
		
		for (Opinion o : this.opinions) {
			if (o.getClass() == Rating.class) {
				rating += ((Rating) o).getRating();
				
				amount++;
			}
		}
		
		return rating / amount;
	}

	public Double getAmount() {
		return this.price + this.deposit;
	}
	
	public abstract OfferType getType();
	
	// TODO mas metodos?

}
