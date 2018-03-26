package application.users;

public abstract class RegisteredUser {
	
	public enum Rol {
		HOST, GUEST, ADMIN, ERROR;		
	}
	
	private String name;
	private String surname;
	private String passwd;
	private String creditCard;
	private String NIF;


	public RegisteredUser(String name, String surname, String passwd, String creditCard, String NIF) {
		this.name = name;
		this.surname = surname;
		this.passwd = passwd;
		this.creditCard = creditCard;
		this.NIF = NIF;
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
	
	public String getNIF() {
		return NIF;
	}


	public void changeCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}


	@Override
	public String toString() {
		return "RegisteredUser [name=" + name + ", surname=" + surname + ", passwd=" + passwd + ", creditCard="
				+ creditCard + ", NIF=" + NIF + "]";
	}
}
