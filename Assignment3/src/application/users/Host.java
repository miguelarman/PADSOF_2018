package application.users;
import java.util.*;

import application.offer.House;
import application.users.RegisteredUser.Rol;


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
	
	public Boolean addHouse(House house) {
		// TODO 
		return true;
	}
}
