package application.opinion;

public class Rating extends Opinion {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6381129000868423363L;
	private Float rating;
	
	public Rating (Float rating) {
		super();
		this.rating = rating;
	}
	
	public Float getRating() {
		return this.rating;
	}
	
	@Override
	public String toString() {
		// TODO
		
		return null;
	}
}
