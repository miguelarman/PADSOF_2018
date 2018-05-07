package application.opinion;

import java.util.*;

import application.App;
import exceptions.NoUserLoggedException;

/**
 * Class that stores a comment about an offer. Thus, it is a subclass of the
 * Opinion class
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public class Comment extends Opinion {

	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = -704197778955576120L;
	
	/**
	 * Text of the comment. Its main part
	 */
	private String text;
	
	/**
	 * All the replies the comment has received
	 */
	private List<Opinion> replies;


	/**
	 * Constructor of the class Comment
	 * 
	 * @param text Text of the comment
	 */
	public Comment(String text) {
		super();
		this.text = text;
		this.replies = new ArrayList<Opinion>();
	}
	
	
	/**
	 * Method that computes the average rating the comment has received, based on the numerical replies
	 * 
	 * @return Average rating of the comment
	 */
	public Double getAvgRating() {
		Double avg = 0.0;
		int amount = 0;
		
		for (Opinion o : this.replies) {
			if (o.getClass() == Rating.class) {
				avg += ((Rating) o).getRating();
				amount++;
			}
		}
		
		if(amount == 0) {
			return 0.0;
		}
		
		return avg / amount;
	}
	
	/**
	 * Getter method for the text attribute
	 * 
	 * @return Text of the comment
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * Getter method for the text replies
	 * 
	 * @return Replies of the comment
	 */
	public List<Opinion> getComments() {
		List<Opinion> aux = new ArrayList<Opinion>();
		for(Opinion o: replies) {
			if (o.getClass() == Comment.class) {
				aux.add(o);
			}
		}
		return aux;
	}
	
	/**
	 * Getter method for the numerical replies
	 * 
	 * @return Ratings of the comment
	 */
	public List<Opinion> getRatings() {
		List<Opinion> aux = new ArrayList<Opinion>();
		for(Opinion o: replies) {
			if (o.getClass() == Rating.class) {
				aux.add(o);
			}
		}
		return aux;
	}
	
	/**
	 * Getter method for the replies (both numerical and text)
	 * 
	 * @return Replies of the comment
	 */
	public List<Opinion> getReplies() {
		List<Opinion> aux = new ArrayList<Opinion>();
		for(Opinion o: replies) {
			aux.add(o);
		}
		return aux;
	}
	
	/**
	 * Method that adds a reply to a Comment
	 * 
	 * @param reply Text of the reply
	 * @throws NoUserLoggedException When a non-logged user tries to reply a comment
	 */
	public void addReply(String reply) throws NoUserLoggedException {
		if(App.getLoggedUser() == null) {
			throw new NoUserLoggedException();
		}
		Opinion o = new Comment(reply);
		
		if(App.getLoggedUser() == null) {
			throw new NoUserLoggedException();
		}
		this.replies.add(o);
	}
	
	/**
	 * Method that rates a comment
	 * 
	 * @param rating Rating to rate the comment
	 * @throws NoUserLoggedException When a non-logged user tries to reply a comment
	 */
	public void rateComment(double rating) throws NoUserLoggedException {
		if(App.getLoggedUser() == null) {
			throw new NoUserLoggedException();
		}
		Opinion o = new Rating(rating);
		
		this.replies.add(o);
	}
	
	@Override
	/**
	 * Method that returns all the information stored in an object of the class Comment in a printable and readable format
	 * 
	 * @return Information stored in the comment in a printable format
	 */
	public String toString() {
		String string = "";
		
		
		string += this.getCommenter().getNIF() + ": " + this.text;
		string += " |Rating: " + this.getAvgRating();
		
		for (Opinion o : this.replies) {
			if(o.getClass() == Comment.class) {
				string += "\n\t" + o;
			}
		}
		return string;
	}

}
