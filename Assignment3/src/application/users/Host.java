//Creo que host ya estaría acabado. Revisar addHouse.

package application.users;
import java.util.*;
import exceptions.*;

import application.offer.House;


public class Host extends RegisteredUser {
	
	private List<House> houses;

	
	public Host(String name, String surname, String passwd, String creditCard) {
		super(name, surname, passwd, creditCard);
		houses = new ArrayList<House>();
		// TODO Auto-generated constructor stub
	}

	
	public List<House> getHouses() {
		return houses;
	}
	
	public Rol getRol() {
		return Rol.HOST;
	}
	
	public Boolean addHouse(Integer zipCode, String city, HashMap<String, String> chs) throws HouseAlreadyCreatedException {
		House house = new House(zipCode, city, chs, this);
		for(House h: houses) {
			if(h.equals(house)) {
				throw new HouseAlreadyCreatedException();
			}
		}
		houses.add(house);
		
		return true;
	}
}
