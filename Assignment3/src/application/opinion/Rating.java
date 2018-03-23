package application.opinion;

public class Rating extends Opinion {
	private Float rating;
	
	public Rating (Float rating) {
		super();
		this.rating = rating;
	}
	
	public Float getRating() {
		return this.rating;
	}
	
	// TODO
}
