package exceptions;

import application.offer.House;

public class HouseAlreadyCreatedException extends Exception{

	private static final long serialVersionUID = -8425575652000587826L;
	@SuppressWarnings("unused")
	private final House house;

	public HouseAlreadyCreatedException(House house) {
		this.house = house;
	}
	
	@Override
	public String toString() {
		return "The house has aready been created";
	}

}
