package application.offer;

import java.util.*;

import application.opinion.Opinion;


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
		// TODO
	}
	
	public void rateOffer(int rating) {
		// TODO
	}
	
	public void rateOffer(String opinion, int rating) {
		// TODO
	}
	
	public Float getAvgRating() {
		// TODO
		return (float) -1.0;
	}

	public abstract Double getAmount();
	
	// TODO mas metodos?

}
