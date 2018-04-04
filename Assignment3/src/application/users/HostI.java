package application.users;

import java.util.List;

import application.offer.House;
import exceptions.HouseAlreadyCreatedException;

public interface HostI {
	public List<House> getHouses();
	public void addHouse(Integer zipCode, String city) throws HouseAlreadyCreatedException;
}
