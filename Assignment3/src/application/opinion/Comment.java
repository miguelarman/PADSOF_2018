package application.opinion;

import java.util.*;


public class Comment extends Opinion {

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
	
	@Override
	public String toString() {
		// TODO
		
		return null;
	}

}
