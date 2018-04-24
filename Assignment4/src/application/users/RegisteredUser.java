package application.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import application.offer.House;
import application.offer.Reservation;

/**
 * This class contains everything related to the users and their types. This
 * class is abstract, because in our implementation a user must either be Host,
 * Guest and/or Admin.
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) and Alberto
 *         Gonzalez (alberto.gonzalezk@gmail.com)
 *
 */
public abstract class RegisteredUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222090974779634707L;

	/**
	 * This enumeration is used to distinguish between all the types of users,
	 * without having to check the class of a Java Object
	 * 
	 * @author Usuario
	 *
	 */
	@SuppressWarnings("javadoc")
	public enum Role {
		HOST, GUEST, ADMIN, MULTIROLE;
	}

	/**
	 * Name of the user
	 */
	private String name;
	/**
	 * Surname of the user
	 */
	private String surname;
	/**
	 * Password of the user
	 */
	private String passwd;
	/**
	 * Credit card number of the user
	 */
	private String creditCard;
	/**
	 * NIF of the user
	 */
	private String NIF;

	/**
	 * List of reserved offers of the user. Cannot be modified unless the user is
	 * from the type Guest or Multirole
	 */
	public List<Reservation> reservedOffers;

	/**
	 * List of houses of the user. Cannot be modified unless the user is from the
	 * type Host or Multirole
	 */
	protected List<House> houses;

	
	/**
	 * Constructor of the RegisteredUser class
	 * 
	 * @param name Name of the user
	 * @param surname Surname of the user
	 * @param passwd Password of the user
	 * @param creditCard Credit Card number of the user
	 * @param NIF NIF of the user
	 */
	public RegisteredUser(String name, String surname, String passwd, String creditCard, String NIF) {
		this.name = name;
		this.surname = surname;
		this.passwd = passwd;
		this.creditCard = creditCard;
		this.NIF = NIF;
		this.reservedOffers = new ArrayList<Reservation>();
		this.houses = new ArrayList<House>();
	}

	/**
	 * Getter method for the name argument
	 * 
	 * @return The name of the user
	 */
	public String getName() {
		return name;
	}
	
	public List<Reservation> getReservedOffers(){
		return this.reservedOffers;
	}

	/**
	 * Getter method for the surname argument
	 * 
	 * @return The surname of the user
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Getter method for the password argument
	 * 
	 * @return The password of the user
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * Getter method for the credit card argument
	 * 
	 * @return The credit card of the user
	 */
	public String getCreditCard() {
		return creditCard;
	}

	/**
	 * Getter method for the NIF argument
	 * 
	 * @return The NIF of the user
	 */
	public String getNIF() {
		return NIF;
	}

	/**
	 * Method that returns the rol of the user. It is abstract, because it has no
	 * sense using it in a RegisteredUser that is not Guest or Host
	 * 
	 * @return The role of the user
	 */
	public abstract Role getRole();

	/**
	 * Method that modifies the credit card associated with a user
	 * 
	 * @param creditCard The new credit card number
	 */
	public void changeCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	@Override
	/**
	 * Method that returns all the data of a RegisteredUser in a String with
	 * printable format
	 */
	public String toString() {
		String string = "";
		string += "Name: " + name + "\n";
		string += "Surname: " + surname + "\n";
		string += "Password: " + passwd + "\n";
		string += "CreditCard: " + creditCard + "\n";
		string += "NIF: " + NIF + "\n";
		if (this.getRole().equals(Role.HOST) || this.getRole().equals(Role.MULTIROLE)) {
			System.out.println(houses);
			string += "Houses: \n";
			int i = 1;
			for (House h : houses) {
				string += "\n(" + i + ")\n";
				string += h + "\n";
				i++;
			}
		}
		if (this.getRole().equals(Role.GUEST) || this.getRole().equals(Role.MULTIROLE)) {
			string += "Booked offers: \n";
			int i = 1;
			for (Reservation r : reservedOffers) {
				string += "\n(" + i + ")\n";
				string += r + "\n";
				i++;
			}
		}
		return string;
	}
}
