package application.opinion;

import java.util.*;


public class Comment extends Opinion {

	/**
	 * 
	 */
	private static final long serialVersionUID = -704197778955576120L;
	private String text;
	private List<Opinion> replies;

	
	public Comment(String text) {
		super();
		this.text = text;
		this.replies = new ArrayList<Opinion>();
	}

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
	
	public String getText() {
		return this.text;
	}
	
	@Override
	public String toString() {
		// TODO
		
		return null;
	}

}
