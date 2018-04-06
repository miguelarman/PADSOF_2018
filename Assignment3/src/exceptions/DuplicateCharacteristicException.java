package exceptions;

import application.offer.House;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*         
*/
public class DuplicateCharacteristicException extends Exception {
	
	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = 7414680616343739233L;
	
	/**
	 * House where the characteristic is duplicated
	 */
	public final House house;
	
	/**
	 * Duplicated key 
	 */
	public final String key;
	
	/**
	 * Constructor for the class DuplicateCharacteristicException
	 * @param house House that contains the duplicated characteristic
	 * @param key Duplicated key
	 */
	public DuplicateCharacteristicException(House house, String key) {
		this.house = house;
		this.key = key;
	}
	
	/**
	 * Getter method for house
	 * @return The house whose characteristic is duplicated
	 */
	public House getHouse() {
		return house;
	}

	/**
	 * Getter method for key
	 * @return Duplicated key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "The characteristic " + this.key + " is already in the house";
	}

}
