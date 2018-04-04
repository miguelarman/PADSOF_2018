package application.opinion;

import java.io.Serializable;

import application.app.App;
import application.users.RegisteredUser;


public abstract class Opinion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8294999714619383268L;
	private RegisteredUser commenter;

	
	public Opinion() {
		this.commenter = App.getLoggedUser();
	}
	
	public Boolean addReply(Comment c) {
		return false;
	}
	
	public RegisteredUser getCommenter() {
		return this.commenter;
	}
	
	@Override
	public String toString() {
		// TODO
		
		return null;
	}
}
