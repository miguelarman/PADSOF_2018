package application.opinion;

/**
 * Class that stores a rating of an offer or other opinion. It is a subclass of
 * the Opinion class
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public class Rating extends Opinion {
	
	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = 6381129000868423363L;
	
	/**
	 * Numerical rating. Its main part
	 */
	private Float rating;
	
	
	/**
	 * Constructor of the class Rating
	 * 
	 * @param rating Numerical rating
	 */
	public Rating (Float rating) {
		super();
		this.rating = rating;
	}
	
	
	/**
	 * Getter method for the rating attribute
	 * 
	 * @return Numerical rating
	 */
	public Float getRating() {
		return this.rating;
	}
	
	@Override
	/**
	 * Method that returns all the information stored in an object of the class Rating in a printable and readable format
	 * 
	 * @return Information stored in the rating in a printable format
	 */
	public String toString() {
		// TODO
		
		return null;
	}
}
