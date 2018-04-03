//Creo que host ya estaría acabado. Revisar addHouse.

package application.users;

import java.util.*;

import exceptions.*;
import application.offer.House;


public class Host extends RegisteredUser implements HostI {
	
	public Host(String name, String surname, String passwd, String creditCard, String NIF) {
		super(name, surname, passwd, creditCard, NIF);
		houses = new ArrayList<House>();
	}

	
	public Rol getRol() {
		return Rol.HOST;
	}
	
	public void addHouse(Integer zipCode, String city, HashMap<String, String> chs) throws HostException {
		House house = new House(zipCode, city, chs, this);
		
		for(House h: houses) {
			if(h.equals(house)) {
				throw new HostException("House already created");
			}
		}
		
		houses.add(house);
	}
	
	@Override
	public List<House> getHouses() {
		return this.houses;
	}
	
	@Override
	public String toString() {
		// TODO
		
		return null;
	}
}
