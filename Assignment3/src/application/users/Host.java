package application.users;

import java.util.*;

import exceptions.*;
import application.offer.House;

/**
 * Class that stores all the data of a Host. It is a subclass of RegisteredUser,
 * and implements HostI
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez
 *         (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public class Host extends RegisteredUser implements HostI{
	
	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = -7067070176031856975L;

	/**
	 * Constructor of the class Host
	 * 
	 * @param name Name of the host
	 * @param surname Surname of the host
	 * @param passwd Password of the host
	 * @param creditCard Credit card of the host
	 * @param NIF NIF of the host
	 */
	public Host(String name, String surname, String passwd, String creditCard, String NIF) {
		super(name, surname, passwd, creditCard, NIF);
		houses = new ArrayList<House>();
	}

	/**
	 * Method that returns the role of the user. Inherited from the superclass
	 * RegisteredUser
	 * 
	 * @return The role of the user. Always host
	 */
	public Role getRole() {
		return Role.HOST;
	}
	
	/**
	 * Method that adds a House to the list of created houses of a Host. From the
	 * interface HostI
	 * 
	 * @param house House to be added to the list of houses of the host
	 * @throws HouseAlreadyCreatedException When a house has been created with the same data
	 */
	public void addHouse(House house) throws HouseAlreadyCreatedException {
		for(House h: houses) {
			if(h.equals(house)) {
				throw new HouseAlreadyCreatedException(house);
			}
		}
		
		houses.add(house);
	}
	
	@Override
	/**
	 * Method that returns the houses which the Host has created in the app. From
	 * the interface HostI
	 * 
	 * @return List with all the houses the host has created
	 */
	public List<House> getHouses() {
		return this.houses;
	}
	
}
