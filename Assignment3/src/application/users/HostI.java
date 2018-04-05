package application.users;

import java.util.List;

import application.offer.House;
import exceptions.HouseAlreadyCreatedException;

/**
 * Interface that models the functionality required for Hosts in the app
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez
 *         (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public interface HostI {
	
	/**
	 * Method that returns the houses which the Host has created in the app
	 * 
	 * @return List with all the houses the host has created
	 */
	public List<House> getHouses();

	/**
	 * Method that adds a House to the list of created houses of a Host
	 * 
	 * @param house House to be added
	 * @throws HouseAlreadyCreatedException When a house has been created with the same data
	 */
	public void addHouse(House house) throws HouseAlreadyCreatedException;
}
