package application.users;

public class Admin extends RegisteredUser{
	
	public Admin(String name, String surname, String passwd, String creditCard, String NIF) {
		super(name, surname, passwd, creditCard, NIF);
	}

	
	public Rol getRol() {
		return Rol.ADMIN;
	}
	
	
	@Override
	public String toString() {
		// TODO
		
		return null;
	}
}
