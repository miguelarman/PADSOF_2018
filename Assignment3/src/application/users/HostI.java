package application.users;

import java.util.HashMap;
import java.util.List;

import application.offer.House;
import exceptions.HostException;

public interface HostI {
	public List<House> getHouses();
	public void addHouse(Integer zipCode, String city, HashMap<String, String> chs) throws HostException;
}
