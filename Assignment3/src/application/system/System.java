package application.system;

import java.io.*;
import java.util.*;

import application.offer.*;
import application.users.*;
import exceptions.LoginException;

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
	
	// Getters

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
	
	
	
	
	// Searches
	
	public List<Offer> searchZipCode(Integer zip) {
		
		List<Offer> searchResult = new ArrayList<Offer>();
		
		for (Offer o : this.offers) {
			House house = o.getHouse();
			
			if (house.getZipCode() == zip) {
				if (o.getStatus() == OfferStatus.APPROVED) {
					searchResult.add(o);
				}
			}
		}
		
		
		return searchResult;	
	}
	
	public List<Offer> searchStartingDate(Date date1, Date date2) {
		
		List<Offer> searchResult = new ArrayList<Offer>();
		
		for (Offer o : this.offers) {
			if (o.getDate().after(date1) && o.getDate().before(date2)) {
				if (o.getStatus() == OfferStatus.APPROVED) {
					searchResult.add(o);
				}
			}
		}
		
		return searchResult;
	}
	
	public List<Offer> searchOfferType(OfferType type) {

		List<Offer> searchResult = new ArrayList<Offer>();
		
		for (Offer o : this.offers) {
			if (o.getType() == type) {
				if (o.getStatus() == OfferStatus.APPROVED) {
					searchResult.add(o);
				}
			}
		}
		
		return searchResult;
	}
	
	
	public List<Offer> searchBooked() {

		List<Offer> searchResult = new ArrayList<Offer>();
		
		for (Offer o : this.offers) {
			if (o.getStatus() == OfferStatus.BOOKED) {
				if (o.getStatus() == OfferStatus.APPROVED) {
					searchResult.add(o);
				}
			}
		}
		
		return searchResult;
	}
	
	public List<Offer> searchPaid() {

		List<Offer> searchResult = new ArrayList<Offer>();
		
		for (Offer o : this.offers) {
			if (o.getStatus() == OfferStatus.PAID) {
				if (o.getStatus() == OfferStatus.APPROVED) {
					searchResult.add(o);
				}
			}
		}
		
		return searchResult;
	}
	
	public List<Offer> searchAvgRating(Float minRating) {

		List<Offer> searchResult = new ArrayList<Offer>();
		
		for (Offer o : this.offers) {
			if (o.getAvgRating() >= minRating) {
				if (o.getStatus() == OfferStatus.APPROVED) {
					searchResult.add(o);
				}
			}
		}
		
		return searchResult;
	}
	
	
	
	// System data functions
	
	public void login(String id, String passwd) throws LoginException {
		
		for (RegisteredUser user : this.bannedUsers) {
			if (user.getName() == id) {
				throw new LoginException("The user (" + id + ") is banned");
			}
		}
		
		for (RegisteredUser user : this.authorizedUsers) {
			if (user.getName() == id) {
				if (user.getPasswd() == passwd) {
					System.loggedAdmin = null;
					System.loggedUser = user;
				} else {
					throw new LoginException("Incorrect password");
				}
			}
		}
		
		for (Admin a : this.admins) {
			if (a.getId() == id) {
				if (a.getPasswd() == passwd) {
					System.loggedAdmin = a;
					System.loggedUser = null;
				} else {
					throw new LoginException("Incorrect password");
				}
			}
		}
		
		throw new LoginException("User does not seem to exist");
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
	
	
	
	private static System openSystem() {
		System mySystem = null;
		
		File data = new File(System.filename);
		if (data.exists()) {
			mySystem = System.loadData();
			return mySystem;
		} else {
			mySystem = new System();
			// ALBERTO
			
			// si entra aquí es porque no existe el fichero con los datos, es decir, si no
			// se ha hecho logout antes, lo que quiere decir que es la primera vez, por lo
			// que hay que cargar los datos de los usuarioscdel fichero que nos dan, en vez
			// de desde el fichero que actualicemos cada vez (cuya direccion es System.filename)
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
	
	
	
	
	public void addOffer(Offer offer) {
		this.offers.add(offer);
	}
	
	public void removeOffer(Offer offer) {
		this.offers.remove(offer);
	}
	
	public void banUser(RegisteredUser user) {
		this.authorizedUsers.remove(user);
		this.bannedUsers.add(user);
	}
	
	public void unbanUser(RegisteredUser user) {
		this.authorizedUsers.add(user);
		this.bannedUsers.remove(user);
	}
	
	// TODO metodo cancelReservation
}
