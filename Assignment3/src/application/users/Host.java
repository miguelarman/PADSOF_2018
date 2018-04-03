//Creo que host ya estaría acabado. Revisar addHouse.

package application.users;
import java.util.*;
import exceptions.*;

import application.offer.House;


public class Host extends RegisteredUser {
	
	private List<House> houses;

	
	public Host(String name, String surname, String passwd, String creditCard, String NIF) {
		super(name, surname, passwd, creditCard, NIF);
		houses = new ArrayList<House>();
	}

	
	public List<House> getHouses() {
		return houses;
	}
	
	public Rol getRol() {
		return Rol.HOST;
	}
	
	public Boolean addHouse(Integer zipCode, String city) throws HouseAlreadyCreatedException {
		House house = new House(zipCode, city, this);
		
		for(House h: houses) {
			if(h.equals(house)) {
				throw new HouseAlreadyCreatedException();
			}
		}
		
		houses.add(house);
		
		return true;
	}
	
}
