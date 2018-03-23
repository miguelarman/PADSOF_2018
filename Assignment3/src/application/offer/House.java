package application.offer;

import java.util.*;

import application.users.Host;

public class House {
	
	private Integer zipCode;
	private String city;
	private HashMap<String, String> chs;
	private Host host;


	public House(Integer zipCode, String city, List<Characteristic> chs, Host host) {
		this.zipCode = zipCode;
		this.city = city;
		this.chs = new HashMap<String, String>();
		this.host = host;
	}
	
	
	// que es esto?
	/*public void addC(String nombre, String valor) {
		this.chs.put(nombre, valor);
		for(String key : this.chs.keySet()) {
			String v = this.chs.get(key);
		}
		this.chs.containsKey("Profe");
	}*/


	
	public Integer getZipCode() {
		return zipCode;
	}


	public String getCity() {
		return city;
	}

	public Host getHost() {
		return host;
	}
	
	
	// TODO
	

}
