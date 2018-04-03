//Creo que ya esta terminado.

package application.offer;

import java.util.*;

import application.users.Host;

import exceptions.HouseException;

public class House {
	
	private Integer zipCode;
	private String city;
	private HashMap<String, String> chs;
	private Host host;


	public House(Integer zipCode, String city, HashMap<String, String> chs, Host host) {
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

	public Host getHost() {
		return host;
	}
	
	
	@Override
	public String toString() {
		// TODO
		
		return null;
	}
}
