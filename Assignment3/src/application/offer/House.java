//Creo que ya esta terminado.

package application.offer;

import java.util.*;

import application.users.*;

import exceptions.HouseException;

public class House {
	
	private Integer zipCode;
	private String city;
	private HashMap<String, String> chs;
	private RegisteredUser host;


	public House(Integer zipCode, String city, Host host) {
		this.zipCode = zipCode;
		this.city = city;
		this.chs = new HashMap<String, String>();
		this.host = host;
	}
	
	public House(Integer zipCode, String city, MultiRoleUser host) {
		this.zipCode = zipCode;
		this.city = city;
		this.chs = new HashMap<String, String>();
		this.host = host;
	}
	
	//metodo para añadir caracteristicas al hashmap
	public void addCharacteristic(String key, String value) throws HouseException {
		if(this.chs.containsKey(key)) {
			if(value.equals(this.chs.get(key))) {
				throw new HouseException("Already contains that characteristic");
			}
		}
		else {
			this.chs.put(key, value);
			return;
		}
	}


	
	public Integer getZipCode() {
		return zipCode;
	}


	public String getCity() {
		return city;
	}

	public RegisteredUser getHost() {
		return host;
	}
	
	
	@Override
	public String toString() {
		String string = "";
		string += "ZIP Code: " + zipCode + "\n";
		string += "City: " + city + "\n";
		string += "Characteristics: " + chara;
	}
}
