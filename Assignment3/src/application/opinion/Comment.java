/**
 * 
 */
package application.opinion;

import java.util.*;

import application.users.RegisteredUser;

/**
 * @author eps
 *
 */
public class Comment extends Opinion {

	private String text;
	private List<Opinion> replies;

	/**
	 * @param commenter
	 * @param text
	 * @param replies
	 */
	public Comment(RegisteredUser commenter, String text) {
		super();
		this.text = text;
		this.replies = new ArrayList<Opinion>();
	}

	public Float getAvgRating() {
		return (float) -1.0;
	}

}
