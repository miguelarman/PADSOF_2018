package application.users;

public class MultiRoleUser extends RegisteredUser {

	public MultiRoleUser(String name, String surname, String passwd, String creditCard, String NIF) {
		super(name, surname, passwd, creditCard, NIF);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rol getRol() {
		// TODO Auto-generated method stub
		return null;
	}

}
