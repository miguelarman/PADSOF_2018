package application.opinion;

import java.util.*;

import application.users.RegisteredUser;


public class Comment extends Opinion {

	private String text;
	private List<Opinion> replies;

	
	public Comment(RegisteredUser commenter, String text) {
		super();
		this.text = text;
		this.replies = new ArrayList<Opinion>();
	}

	public Float getAvgRating() {
		// TODO
		return (float) -1.0;
	}

}
