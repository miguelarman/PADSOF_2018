package application.users;

/**
 * Class that stores all the data of an Admin. It is a subclass of
 * RegisteredUser, and implements HostI
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez
 *         (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public class Admin extends RegisteredUser {

	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = -4188824740328380552L;

	/**
	 * Constructor of the class Admin
	 * 
	 * @param name Name of the admin
	 * @param surname Surname of the admin
	 * @param passwd Password of the admin
	 * @param creditCard Credit card of the admin
	 * @param NIF NIF of the admin
	 */
	public Admin(String name, String surname, String passwd, String creditCard, String NIF) {
		super(name, surname, passwd, creditCard, NIF);
	}

	/**
	 * Method that returns the rol of the user. Inherited from the superclass
	 * RegisteredUser
	 * 
	 * @return The role of the user. Always admin
	 */
	public Role getRole() {
		return Role.ADMIN;
	}

}
