package application.app;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import application.offer.*;
import application.users.*;

import exceptions.*;

/**
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 *
 */

public class App implements Serializable {
	
	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = 7941697892854198940L;
	
	/**
	 * List of offers in the system
	 */
	private List<Offer> offers;
	
	/**
	 * List of banned users in the system
	 */
	private List<RegisteredUser> bannedUsers;
	
	/**
	 * List of users that can log into the system
	 */
	private List<RegisteredUser> authorizedUsers;
	
	/**
	 * User that is currently logged in
	 */
	private static RegisteredUser loggedUser;
	
	/**
	 * Name of the file where the information of the system is stored
	 */
	private static String filename = "data.obj";
	
	/**
	 * HashMap that stores an offer and the date in which an admin requested a change
	 */
	private HashMap<Offer, LocalDate> changesRequests;
	
	/**
	 * Constructor of the class App
	 */
	public App() {
		offers = new ArrayList<Offer>();
		bannedUsers = new ArrayList<RegisteredUser>();
		authorizedUsers = new ArrayList<RegisteredUser>();
		loggedUser = null;
		changesRequests = new HashMap<Offer, LocalDate>();
	}
	
		
	// Getters

	/**
	 * Getter method for offers
	 * @return offers, list of offers in the system
	 */
	public List<Offer> getOffers() {
		return offers;
	}

	/**
	 * Getter method for bannedUsers
	 * @return bannedUsers, list of banned users in the system
	 */
	public List<RegisteredUser> getBannedUsers() {
		return bannedUsers;
	}

	/**
	 * Getter method for authorizedUsers
	 * @return authorizedUsers, list of authorized users in the system
	 */
	public List<RegisteredUser> getAuthorizedUsers() {
		return authorizedUsers;
	}

	/**
	 * Getter method for loggedUser
	 * @return loggedUser, user that is currently logged in
	 */
	public static RegisteredUser getLoggedUser() {
		return loggedUser;
	}

	//Setters
	
	/**
	 * Setter method for loggedUser
	 * @param loggedUser, user that logs into the system
	 */
	public void setLoggedUser(RegisteredUser loggedUser) {
		App.loggedUser = loggedUser;
	}
	
	
	
	
	// Searches
	
	/**
	 * 
	 * @param zip, zip code of the house in the offer
	 * @return
	 */
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
	
	public List<Offer> searchStartingDate(LocalDate date1, LocalDate date2) {
		
		List<Offer> searchResult = new ArrayList<Offer>();
		
		for (Offer o : this.offers) {
			if (o.getDate().isAfter(date1) && o.getDate().isBefore(date2)) {
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
	
	
	
	// Appdata functions
	
	public void login(String id, String passwd) throws UserIsBannedException, IncorrectPasswordException, UnexistentUserException {
		
		for (RegisteredUser user : this.bannedUsers) {
			if (user.getName() == id) {
				throw new UserIsBannedException(user);
			}
		}
		
		for (RegisteredUser user : this.authorizedUsers) {
			if (user.getName() == id) {
				if (user.getPasswd() == passwd) {
					App.loggedUser = user;
					return;
				} else {
					throw new IncorrectPasswordException(user, passwd);
				}
			}
		}
		
		throw new UnexistentUserException(id);
	}
	
	public void logout() {
		
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream( new FileOutputStream(App.filename));
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		App.loggedUser = null;
	}
	
	
	public static App openApp() {
		App myApp = null;
		File data = new File(App.filename);

		if (data.exists()) {
			myApp = App.loadData();
			return myApp;
		} else {			
			myApp = App.initializeApp();
			return myApp;
		}
	}
	
	
	private static App initializeApp() {
		
		App app = new App();
		int i;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("users.txt")));
			
			for(String x = br.readLine(); x != null; x = br.readLine()) {
				StringTokenizer str = new StringTokenizer(x, ";");
				String[] info = new String[str.countTokens()];
				
				for(i = 0; i < info.length; i++) {
					info[i] = str.nextToken();
				}
				StringTokenizer n = new StringTokenizer(info[2], ",");
				String surname = n.nextToken();
				String name = n.nextToken();
				if(info[0].equals("A")) {
					RegisteredUser a = new Admin(name, surname, info[3], info[4], info[1]);
					app.authorizedUsers.add(a);
				} else if (info[0].equals("H")){
					
					RegisteredUser h = new Host(name, surname, info[3], info[4], info[1]);
					app.authorizedUsers.add(h);
					
				} else if (info[0].equals("G")){
					RegisteredUser g = new Guest(name, surname, info[3], info[4], info[1]);
					app.authorizedUsers.add(g);
				} else if (info[0].equals("HG")) {
					//RegisteredUser m = new Multi(name, surname, info[3], info[4], info[1]); TODO mirar el nombre del multrol
					//app.authorizedUsers.add(m);
				}
			}
			
			br.close();
			
			return app;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return app;
	}


	private static App loadData() {
		
		App app = null;
		
		ObjectInputStream is;
		try {
			is = new ObjectInputStream(new FileInputStream(App.filename));
			app = (App) is.readObject();
			is.close();
			return app;
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
		
		

		// Deleting expired offers
		app.deleteExpiredOffers();
		
		// Deleting expired Reservations
		app.deleteExpiredReservations();
		
		// Deleting expired offers pending changes
		app.deleteExpiredPendingOffers();
		
		return app;
	}
	
	
	
	
	private void deleteExpiredOffers() {
		for (Offer o : this.offers) {
			LocalDate startingDate = o.getDate();
			LocalDate currentDate = LocalDate.now();
			
			if (startingDate.isBefore(currentDate)) { // The offer has expired
				if (o.getStatus() != OfferStatus.PAID) {
					this.removeOffer(o);
				}
			}
		}
	}


	private void deleteExpiredReservations() {
		for (RegisteredUser user : this.authorizedUsers) {
			if (user.getRol() == RegisteredUser.Rol.GUEST) {
				for (Reservation r : ((Guest) user).getReservedOffers()) {
					LocalDate bookingDate = r.getBookingDate();
					LocalDate currentDate = LocalDate.now();
									    
				    long daysBetween = ChronoUnit.DAYS.between(bookingDate, currentDate);
					
					if (daysBetween >= 5) { // User has exceeded 5 days without paying
						r.cancelReservation();
					}
				}
			}
		}
	}


	private void deleteExpiredPendingOffers() {
		List<Offer> offers = this.getPendingOffers();
		
		for (Offer o : offers) {
			
			LocalDate changesDate = this.changesRequests.get(o);
			LocalDate currentDate = LocalDate.now();
		    
		    long daysBetween = ChronoUnit.DAYS.between(changesDate, currentDate);
			
			if (daysBetween >= 5) { // User has exceeded 5 days without paying
				// Deletes the request from the app
				this.changesRequests.remove(o);
				
				// Removes the offer from the system
				this.removeOffer(o);
			}
		}
	}


	private List<Offer> getPendingOffers() {
		List<Offer> offers = new ArrayList<Offer>();
		
		for (Offer o : this.offers) {
			if (o.getStatus() == OfferStatus.PENDING) {
				offers.add(o);
			}
		}
		
		return offers;
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
		
		// TODO hacer logout aqui o donde se llame a esta funcion
	}
	
	public void unbanUser(RegisteredUser user) {
		this.authorizedUsers.add(user);
		this.bannedUsers.remove(user);
	}


	@Override
	public String toString() {
		return "App[offers=" + offers + ", bannedUsers=" + bannedUsers + ", authorizedUsers=" + authorizedUsers
				+ "]";
	}
	
	// TODO metodo cancelReservation
}
