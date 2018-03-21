/**
 * 
 */
package application.users;
import java.util.*;

import application.offer.House;
import application.users.RegisteredUser.Rol;

/**
 * @author eps
 *
 */
public class Host extends RegisteredUser {
	
	private List<House> houses;

	/**
	 * @param name
	 * @param surname
	 * @param passwd
	 * @param creditCard
	 */
	public Host(String name, String surname, String passwd, String creditCard) {
		super(name, surname, passwd, creditCard);
		houses = new ArrayList<House>();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the houses
	 */
	public List<House> getHouses() {
		return houses;
	}
	
	public Rol getRol() {
		return Rol.HOST;
	}
	
	public Boolean addHouse(House house) {
		return true;
		
	}
	

}
