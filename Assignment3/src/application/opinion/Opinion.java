package application.opinion;

import application.system.System;
import application.users.RegisteredUser;


public abstract class Opinion {
	
	private RegisteredUser commenter;

	
	public Opinion() {
		this.commenter = System.getLoggedUser();
	}
	
	public Boolean addReply(Comment c) {
		return false;
	}
	
	
	// TODO
	

}
