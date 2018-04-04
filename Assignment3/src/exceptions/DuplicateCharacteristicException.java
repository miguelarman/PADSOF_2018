package exceptions;

import application.offer.House;

public class DuplicateCharacteristicException extends Exception {
	
	private static final long serialVersionUID = 7414680616343739233L;
	public final House house;
	public final String key;

	public DuplicateCharacteristicException(House house, String key) {
		this.house = house;
		this.key = key;
	}
	
	@Override
	public String toString() {
		return "The characteristic " + this.key + " is already in the house";
	}

}
