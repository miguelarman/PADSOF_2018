package application.offer;

import java.io.Serializable;
import java.util.*;

import application.users.*;
import exceptions.DuplicateCharacteristicException;

/**
 * Class that stores all the data of a house. This class is needed for the implementation of Offers
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 *
 */
public class House implements Serializable{
	
	/**
	 * ID needed for serializing
	 */
	private static final long serialVersionUID = 3389804622762131198L;
	
	/**
	 * ZIP code of the house
	 */
	private Integer zipCode;
	
	/**
	 * City of the house
	 */
	private String city;
	
	/**
	 * Map representing characteristics of the house. Is made by a String with a
	 * name of the characteristic and a String used to specify the characteristic.
	 * For example, "Pool" and "covered"
	 */
	private HashMap<String, String> chs;
	
	/**
	 * Host that owns the house
	 */
	private RegisteredUser host;

	
	/**
	 * Constructor of the class House
	 * 
	 * @param zipCode ZIP code of the house
	 * @param city City of the house
	 * @param host Host that owns the house
	 */
	public House(Integer zipCode, String city, Host host) {
		this.zipCode = zipCode;
		this.city = city;
		this.chs = new HashMap<String, String>();
		this.host = host;
	}
	
	/**
	 * Constructor of the class House
	 * 
	 * @param zipCode ZIP code of the house
	 * @param city City of the house
	 * @param host MultiRoleUser that owns the house
	 */
	public House(Integer zipCode, String city, MultiRoleUser host) {
		this.zipCode = zipCode;
		this.city = city;
		this.chs = new HashMap<String, String>();
		this.host = host;
	}
	
	/**
	 * Method that adds characteristics to the house
	 * 
	 * @param key Description of the characteristic
	 * @param value Specification of the characteristic
	 * @throws DuplicateCharacteristicException When a characteristic has already been inserted
	 */
	public void addCharacteristic(String key, String value) throws DuplicateCharacteristicException {
		if(this.chs.containsKey(key)) {
			if(value.equals(this.chs.get(key))) {
				throw new DuplicateCharacteristicException(this, key);
			}
		} else {
			this.chs.put(key, value);
			return;
		}
	}


	/**
	 * Getter method for the zip code attribute
	 * 
	 * @return zip code of the house
	 */
	public Integer getZipCode() {
		return zipCode;
	}

	/**
	 * Getter method for the city attribute
	 * 
	 * @return city of the house
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Getter method for the host attribute
	 * 
	 * @return Owner of the house
	 */
	public RegisteredUser getHost() {
		return host;
	}
	
	
	@Override
	/**
	 * Method that returns all the information stored in an object of the class House in a printable and readable format
	 * 
	 * @return Information stored in the house in a printable format
	 */
	public String toString() {
		String string = "";
		string += "ZIP Code: " + zipCode + "\n";
		string += "City: " + city + "\n";
		string += "Owner: " + host.getNIF() + "\n";
		string += "Characteristics: " + chs + "\n";
		return string;
	}
}
