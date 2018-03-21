/**
 * 
 */
package application.opinion;

import application.system.System;
import application.users.RegisteredUser;

/**
 * @author eps
 *
 */
public abstract class Opinion {
	
	private RegisteredUser commenter;

	/**
	 * @param commenter
	 */
	public Opinion() {
		this.commenter = System.getLoggedUser();
	}
	
	public Boolean addReply(Comment c) {
		return false;
	}

}
