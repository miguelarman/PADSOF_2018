/**
 * 
 */
package application.offer;
import java.util.*;

import application.opinion.Opinion;

/**
 * @author eps
 *
 */
public abstract class Offer {
	private Date startingDate;
	private Float price;
	private Float deposit;
	private String description;
	private OfferState state;
	private House offeredHouse;
	private List<Opinion> opinions;
	/**
	 * @param startingDate
	 * @param price
	 * @param deposit
	 * @param description
	 * @param state
	 * @param offeredHouse
	 * @param opinions
	 */
	public Offer(Date startingDate, Float price, Float deposit, String description, OfferState state,
			House offeredHouse) {
		this.startingDate = startingDate;
		this.price = price;
		this.deposit = deposit;
		this.description = description;
		this.state = OfferState.PENDING;
		this.offeredHouse = offeredHouse;
		this.opinions = new ArrayList<Opinion>();
	}
	

}
