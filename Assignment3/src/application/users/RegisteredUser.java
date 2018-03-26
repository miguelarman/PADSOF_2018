package application.users;

public abstract class RegisteredUser {
	
	public enum Rol {
		HOST, GUEST, ERROR;		
	}
	
	private String name;
	private String surname;
	private String passwd;
	private String creditCard;


	public RegisteredUser(String name, String surname, String passwd, String creditCard) {
		this.name = name;
		this.surname = surname;
		this.passwd = passwd;
		this.creditCard = creditCard;
	}


	public String getName() {
		return name;
	}


	public String getSurname() {
		return surname;
	}


	public String getPasswd() {
		return passwd;
	}


	public String getCreditCard() {
		return creditCard;
	}
	
	public abstract Rol getRol();
	
	public void changeCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
	@Override
	public String toString() {
		// TODO
		
		return null;
	}
}
