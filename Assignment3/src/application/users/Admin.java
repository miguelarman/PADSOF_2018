package application.users;

public class Admin extends RegisteredUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4188824740328380552L;


	public Admin(String name, String surname, String passwd, String creditCard, String NIF) {
		super(name, surname, passwd, creditCard, NIF);
	}

	
	public Rol getRol() {
		return Rol.ADMIN;
	}
	
	
}
