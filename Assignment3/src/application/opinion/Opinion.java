package application.opinion;

import application.app.App;
import application.users.RegisteredUser;


public abstract class Opinion {
	
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
