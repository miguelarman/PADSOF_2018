package application.system;

import java.io.*;
import java.util.*;

import application.offer.*;
import application.users.*;

public class System implements Serializable{
	
	private static final long serialVersionUID = 7941697892854198940L;
	private List<Admin> admins;
	private static Admin loggedAdmin;
	private List<Offer> offers;
	private List<RegisteredUser> bannedUsers;
	private List<RegisteredUser> authorizedUsers;
	private static RegisteredUser loggedUser;
	private static String filename = "data.obj";
	
	public System() {
		admins = new ArrayList<Admin>();
		loggedAdmin = null;
		offers = new ArrayList<Offer>();
		bannedUsers = new ArrayList<RegisteredUser>();
		authorizedUsers = new ArrayList<RegisteredUser>();
		loggedUser = null;
	}
	
	
	/*
	 * StringTokenizer str = new StringTokenizer(line, '.')
	 * str.castToken()?;
	 * str.nextToken();
	 * str.hashMoreTokens();*/
	

	public List<Admin> getAdmins() {
		return admins;
	}

	public static Admin getLoggedAdmin() {
		return loggedAdmin;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public List<RegisteredUser> getBannedUsers() {
		return bannedUsers;
	}

	public List<RegisteredUser> getAuthorizedUsers() {
		return authorizedUsers;
	}

	public static RegisteredUser getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedAdmin(Admin loggedAdmin) {
		System.loggedAdmin = loggedAdmin;
	}

	public void setLoggedUser(RegisteredUser loggedUser) {
		System.loggedUser = loggedUser;
	}
	
	
	
	public List<Offer> searchZipCode(Integer zip) {
		// TODO
		return null;	
	}
	
	public List<Offer> searchStartingDate(Date date1, Date date2) {
		// TODO
		return null;
	}
	
	public List<Offer> searchOfferType(OfferType type) {
		// TODO
		return null;
	}
	
	public List<Offer> searchBooked() {
		// TODO
		return null;
	}
	
	public List<Offer> searchPaid() {
		// TODO
		return null;
	}
	
	public List<Offer> searchAvgRating(Float minRating) {
		// TODO
		return null;
	}
	
	public Boolean login(String id, String passwd) {
		// TODO
		
		for (RegisteredUser user : this.bannedUsers) {
			if (user.getName() == id) {
				// el usuario esta baneado
			}
		}
		
		for (RegisteredUser user : this.authorizedUsers) {
			if (user.getName() == id) {
				if (user.getPasswd() == passwd) {
					// login como ese usuario
				} else {
					// contrasena incorrecta. exception?
				}
			}
		}
		
		// el usuario no existe. exception?
		
		return false;
	}
	
	public void logout() {
		
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream( new FileOutputStream(System.filename));
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.loggedAdmin = null;
		System.loggedUser = null;
	}
	
	public Boolean addOffer(Offer offer) {
		// TODO
		return true;
	}
	
	public Boolean removeOffer(Offer offer) {
		// TODO
		return true;
	}
	
	public Boolean banUser(RegisteredUser user) {
		// TODO
		return true;
	}
	
	public Boolean unbanUser(RegisteredUser user) {
		// TODO
		return true;
	}
	
	
	private static System openSystem() {
		System mySystem = null;
		
		File data = new File(System.filename);
		if (data.exists()) {
			mySystem = System.loadData();
			return mySystem;
		} else {
			mySystem = new System();
			return mySystem;
		}
		
		// TODO mas comprobaciones o cosas?
	}
	
	
	private static System loadData() {
		
		System system = null;
		
		ObjectInputStream is;
		try {
			is = new ObjectInputStream(new FileInputStream(System.filename));
			system = (System) is.readObject();
			is.close();
			return system;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return system;

		// TODO comprobar segun se arranca las reservas que han caducado y eliminarlas y
		// poner las ofertas como disponibles

		// TODO mas comprobaciones o cosas?
	}
	
	// TODO metodo cancelReservation
}
