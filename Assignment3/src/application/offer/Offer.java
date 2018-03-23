package application.offer;

import java.util.*;

import application.opinion.Comment;
import application.opinion.Opinion;
import application.opinion.Rating;


public abstract class Offer {
	private Date startingDate;
	private Float price;
	private Float deposit;
	private String description;
	private OfferStatus status;
	private House offeredHouse;
	private List<Opinion> opinions;
	
	
	public Offer(Date startingDate, Float price, Float deposit, String description, OfferStatus status,
			House offeredHouse) {
		this.startingDate = startingDate;
		this.price = price;
		this.deposit = deposit;
		this.description = description;
		this.status = OfferStatus.PENDING;
		this.offeredHouse = offeredHouse;
		this.opinions = new ArrayList<Opinion>();
	}
	
	
	
	public void modifyOffer(Date startingDate) {
		this.startingDate = startingDate;
	}
	
	public void modifyOffer(Float price, Float deposit) {
		this.price = price;
		this.deposit = deposit;
	}
	
	public void modifyOffer(String description) {
		this.description = description;
	}
	
	public void modifyOffer(OfferStatus status) {
		this.status = status;
	}
	
	
	public abstract void payOffer();
	
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

	public abstract Double getAmount();
	
	// TODO mas metodos?

}
