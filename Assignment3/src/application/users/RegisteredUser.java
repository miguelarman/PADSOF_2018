/**
 * 
 */
package application.users;

/**
 * @author eps
 *
 */
public abstract class RegisteredUser {
	
	public enum Rol {
		HOST, GUEST, ERROR;		
	}
	
	private String name;
	private String surname;
	private String passwd;
	private String creditCard;
	/**
	 * @param name
	 * @param surname
	 * @param passwd
	 * @param creditCard
	 */
	public RegisteredUser(String name, String surname, String passwd, String creditCard) {
		this.name = name;
		this.surname = surname;
		this.passwd = passwd;
		this.creditCard = creditCard;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}
	/**
	 * @return the creditCard
	 */
	public String getCreditCard() {
		return creditCard;
	}
	/**
	 * @param creditCard the creditCard to set
	 */
	public void changeCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
	public Rol getRol() {
		return Rol.ERROR;
	}
	

}
