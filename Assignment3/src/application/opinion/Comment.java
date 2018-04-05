package application.opinion;

import java.util.*;

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
	public Float getAvgRating() {
		Float avg = (float) 0;
		int amount = 0;
		
		for (Opinion o : this.replies) {
			if (o.getClass() == Rating.class) {
				avg += ((Rating) o).getRating();
				amount++;
			}
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
	public List<Opinion> getReplies() {
		return this.replies;
	}
	
	/**
	 * Method that adds a reply to a Comment
	 * 
	 * @param o Opinion with the reply
	 */
	public void addReply(Opinion o) {
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
		
		string += this.getCommenter() + " : " + this.text;
		
		for (Opinion o : this.replies) {
			string += "\n" + o;
		}
		
		return string;
	}

}
