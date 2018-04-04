package application.offer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import application.app.App;
import application.opinion.*;
import application.users.RegisteredUser;

import exceptions.NoUserLoggedException;

import es.uam.eps.padsof.telecard.*;


public abstract class Offer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8222183165622361142L;
	private LocalDate startingDate;
	private Double price;
	private Double deposit;
	private String description;
	private OfferStatus status;
	private House offeredHouse;
	private List<Opinion> opinions;
	
	
	public Offer(LocalDate startingDate, Double price, Double deposit, String description, House offeredHouse) {
		this.startingDate = startingDate;
		this.price = price;
		this.deposit = deposit;
		this.status = OfferStatus.PENDING_FOR_APPROVAL;
		this.offeredHouse = offeredHouse;
		this.opinions = new ArrayList<Opinion>();
	}
	
	
	public House getHouse() {
		return this.offeredHouse;
	}
	
	public OfferStatus getStatus() {
		return this.status;
	}
	
	public LocalDate getDate() {
		return this.startingDate;
	}
	
	
	
	
	public String getDescription() {
		return description;
	}

	public void modifyOffer(LocalDate startingDate) {
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
	
	
	public void payOffer() throws NoUserLoggedException {
		Double amount = this.getAmount();
		
		// TODO rellenar el asunto
		String subject = "------------";
		
		RegisteredUser user = App.getLoggedUser();
		if (user == null) {
			throw new NoUserLoggedException();
		}
		
		String ccard = user.getCreditCard();
		
		try {
			TeleChargeAndPaySystem.charge(ccard, subject, amount); // Igual en los catch siguentes debemos lanzar un PaymentException
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
		
		// TODO pagar al host
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
		
		// no se como hacer para poner a la vez un comentario de texto y numerico
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
	
	
	
	
	@Override
	public String toString() {
		// TODO
		
		return null;
	}

}
