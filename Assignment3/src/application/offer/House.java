/**
 * 
 */
package application.offer;
import java.util.*;

import application.users.Host;

/**
 * @author eps
 *
 */
public class House {
	
	private Integer zipCode;
	private String city;
	private HashMap<String, String> chs;
	private Host host;
	/**
	 * @param zipCode
	 * @param city
	 * @param chs
	 * @param host
	 */
	public House(Integer zipCode, String city, List<Characteristic> chs, Host host) {
		this.zipCode = zipCode;
		this.city = city;
		this.chs = new HashMap<String, String>();
		this.host = host;
	}
	
	public void addC(String nombre, String valor) {
		this.chs.put(nombre, valor);
		for(String key : this.chs.keySet()) {
			String v = this.chs.get(key);
		}
		this.chs.containsKey("Profe");
	}
	/**
	 * @return the zipCode
	 */
	public Integer getZipCode() {
		return zipCode;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @return the host
	 */
	public Host getHost() {
		return host;
	}
	
	
	
	

}
