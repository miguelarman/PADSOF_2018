package application.opinion;

import java.io.Serializable;

import application.App;
import application.users.RegisteredUser;


/**
 * This class stores the basic data needed for an Opinion. It is abstract, as it
 * is used to create Comment and Rating
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public abstract class Opinion implements Serializable{
	
	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = -8294999714619383268L;
	
	/**
	 * Registered User who posted the opinion. It is in common in Ratings and Comments
	 */
	private RegisteredUser commenter;

	
	/**
	 * Constructor method of the class Opinion
	 */
	public Opinion() {
		this.commenter = App.getLoggedUser();
	}
	
	/**
	 * Getter method for the attribute commenter
	 * 
	 * @return User that posted the opinion
	 */
	public RegisteredUser getCommenter() {
		return this.commenter;
	}
	
	@Override
	/**
	 * Method that returns all the information stored in an object of the class
	 * Opinion in a printable and readable format. It is abstract, as it makes no
	 * sense to call this method from a Opinion object if it not either a Comment or
	 * a Rating
	 * 
	 * @return Information stored in the opinion in a printable format
	 */
	public abstract String toString();
}
