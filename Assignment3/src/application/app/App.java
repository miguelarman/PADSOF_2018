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
	 * @return list of offers in the system
	 */
	public List<Offer> getOffers() {
		return offers;
	}

	/**
	 * Getter method for bannedUsers
	 * @return list of banned users in the system
	 */
	public List<RegisteredUser> getBannedUsers() {
		return bannedUsers;
	}

	/**
	 * Getter method for authorizedUsers
	 * @return list of authorized users in the system
	 */
	public List<RegisteredUser> getAuthorizedUsers() {
		return authorizedUsers;
	}

	/**
	 * Getter method for loggedUser
	 * @return user that is currently logged in
	 */
	public static RegisteredUser getLoggedUser() {
		return loggedUser;
	}

	//Setters
	
	/**
	 * Setter method for loggedUser
	 * @param loggedUser - User that logs into the system
	 */
	public void setLoggedUser(RegisteredUser loggedUser) {
		App.loggedUser = loggedUser;
	}
	
	// Searches
	
	/**
	 * Method that searches all the offers that have a house with the given ZIP code
	 * @param zip - ZIP code of the house in the offer
	 * @return list of offers whose house has the given ZIP code
	 */
	public List<Offer> searchZipCode(Integer zip) {
		
		List<Offer> searchResult = new ArrayList<Offer>();
		
		for (Offer o : this.offers) { //Go over all the houses in the system
			House house = o.getHouse();
			
			if (house.getZipCode() == zip) { //Check if the ZIP code matches
				if (o.getStatus() == OfferStatus.APPROVED) { //Check if that offer is approved
					searchResult.add(o);
				}
			}
		}
		
		
		return searchResult;	
	}
	
	/**
	 * Method that searches all the offers whose starting date is between the two given dates
	 * @param date1 - Starting or ending date of the interval
	 * @param date2 - Starting or ending date of the interval
	 * @return list of offers whose starting date is between the two given dates
	 */
	public List<Offer> searchStartingDate(LocalDate date1, LocalDate date2) {
		
		List<Offer> searchResult = new ArrayList<Offer>();
		if(date1.isAfter(date2)) { //If the date1 is after the date2 we invert them
			LocalDate dateAux = date2;
			date2 = date1;
			date1 = dateAux;
			dateAux = null;
		}
		
		for (Offer o : this.offers) { //Go over all the offers
			if (o.getDate().isAfter(date1) && o.getDate().isBefore(date2)) { //Check if its starting date is in the interval
				if (o.getStatus() == OfferStatus.APPROVED) { //Check if the offer is approved
					searchResult.add(o);
				}
			}
		}
		
		return searchResult;
	}
	
	/**
	 * Method that searches all the offers whose type is the given one
	 * @param type - Type of offer that we want to search
	 * @return list of offers whose type is the given one
	 */
	public List<Offer> searchOfferType(OfferType type) {

		List<Offer> searchResult = new ArrayList<Offer>();
		
		for (Offer o : this.offers) { //Go over all the offers
			if (o.getType() == type) { //Check if the type of offer matches
				if (o.getStatus() == OfferStatus.APPROVED) { //Check if the offer is approved
					searchResult.add(o);
				}
			}
		}
		
		return searchResult;
	}
	
	/**
	 * Method that searches all the offers that have been booked
	 * @return list of booked offers
	 */
	public List<Offer> searchBooked() {

		List<Offer> searchResult = new ArrayList<Offer>();
		
		for (Offer o : this.offers) { //Go over all the offers
			if (o.getStatus() == OfferStatus.BOOKED) { //Check if the offer is booked
				searchResult.add(o);
			}
		}
		
		return searchResult;
	}
	
	/**
	 * Method that searches all the offers that have been paid
	 * @return list of paid offers
	 */
	public List<Offer> searchPaid() {

		List<Offer> searchResult = new ArrayList<Offer>();
		
		for (Offer o : this.offers) { //Go over all the offers
			if (o.getStatus() == OfferStatus.PAID) { //Check if the offer is paid
				searchResult.add(o);
			}
		}
		
		return searchResult;
	}
	
	/**
	 * Method that searches all the offers with at least the given rating
	 * @param minRating - Minimum rating of the offers
	 * @return list of offers with at least the given rating
	 */
	public List<Offer> searchAvgRating(Float minRating) {

		List<Offer> searchResult = new ArrayList<Offer>();
		
		for (Offer o : this.offers) { //Go over all the offers
			if (o.getAvgRating() >= minRating) { //Check if the rating of the offer is greater or equal
				if (o.getStatus() == OfferStatus.APPROVED) { //Check if the offer is approved
					searchResult.add(o);
				}
			}
		}
		
		return searchResult;
	}
	
	
	
	// App data functions
	
	/**
	 * Method that with a given id (NIF) and a password checks if the user is an authorized user and lets that user go into the system
	 * @param id - NIF of the user trying to log in
	 * @param passwd - Password of the user trying to log in
	 * @throws LoginException - When 
	 */
	public void login(String id, String passwd) throws UserIsBannedException, IncorrectPasswordException, UnexistentUserException {
		
		for (RegisteredUser user : this.bannedUsers) { //Checks if the user trying to log in is banned
			if (user.getName() == id) {
				throw new UserIsBannedException(user);
			}
		}
		
		for (RegisteredUser user : this.authorizedUsers) { 
			if (user.getName() == id) { //Checks if that NIF is stored as a authorized user NIF
				if (user.getPasswd() == passwd) { //Checks if the user's password matches with the one stored in the system
					App.loggedUser = user; //Sets the user as logged
					return;
				} else {
					throw new IncorrectPasswordException(user, passwd);
				}
			}
		}
		
		throw new UnexistentUserException(id);
	}
	
	/**
	 * Method that logs out the user and closes the system, dumping the information into the file filename
	 */
	public void logout() {
		
		ObjectOutputStream oos;
		
		App.loggedUser = null; //Logs the user out
		
		try {
			oos = new ObjectOutputStream( new FileOutputStream(App.filename));
			oos.writeObject(this); //Writes the system in the file
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that is called to start the system. If there it is the first time opening
	 * the system it reads and loads a list of users into the system. Otherwise, it reads the 
	 * system's information from the file filename
	 * @return the system ready to function
	 */
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
	
	/**
	 * Method that reads the user information from a file called "users.txt" and loads
	 * its information into the authorized users list in the system
	 * @return a new app with the information of the users from the file
	 */
	private static App initializeApp() {
		
		App app = new App();
		int i;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("users.txt")));//Open the file "users.txt" to read
			
			for(String x = br.readLine(); x != null; x = br.readLine()) { //While the file has not ended
				StringTokenizer str = new StringTokenizer(x, ";"); //Token by the character ";"
				String[] info = new String[str.countTokens()]; //Store the tokenized information in an array
				
				for(i = 0; i < info.length; i++) { //Get all the information
					info[i] = str.nextToken();
				}
				StringTokenizer n = new StringTokenizer(info[2], ","); // Token the full name into name and surname
				String surname = n.nextToken();
				String name = n.nextToken();
				if(info[0].equals("A")) { //If the user is an admin we create an admin in the system
					RegisteredUser a = new Admin(name, surname, info[3], info[4], info[1]);
					app.authorizedUsers.add(a);
				} else if (info[0].equals("H")){ //If the user is a host we create a host in the system
					RegisteredUser h = new Host(name, surname, info[3], info[4], info[1]);
					app.authorizedUsers.add(h);
				} else if (info[0].equals("G")){//If the user is a guest we create a guest in the system
					RegisteredUser g = new Guest(name, surname, info[3], info[4], info[1]);
					app.authorizedUsers.add(g);
				} else if (info[0].equals("HG")) { //If the user is a multirole we create a multiRoleUser in the system
					RegisteredUser m = new MultiRoleUser(name, surname, info[3], info[4], info[1]); 
					app.authorizedUsers.add(m);
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

	/**
	 * Method that loads the data stored in binary files locally. It uses the
	 * functionality of the Serializable Interface
	 * 
	 * @return An Object of the class System with all the data loaded
	 */
	private static App loadData() {
		
		App app = null;
		
		ObjectInputStream is;
		try {
			is = new ObjectInputStream(new FileInputStream(App.filename));
			app = (App) is.readObject();
			is.close();
			
			LocalDate currentDate = LocalDate.now();

			// Deleting expired offers
			app.deleteExpiredOffers(currentDate);
			
			// Deleting expired Reservations
			app.deleteExpiredReservations(currentDate);
			
			// Deleting expired offers pending changes
			app.deleteExpiredPendingOffers(currentDate);
			
			return app;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not find the file to read the data in loadData()");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Encountered an IOException when loading data");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Encountered an ClassNotFoundException when loading data");
			e.printStackTrace();
		}
		
		
		
		return app;
	}
	
	/**
	 * This method removes the offers in the System whose starting date has arrived
	 * without anyone paying for it. It is called every time the system is loaded
	 * from the binary files
	 * 
	 * @param currentDate The current date, which will be used to sort which offers are
	 * going to be removed from the system
	 */
	private void deleteExpiredOffers(LocalDate currentDate) {
		for (Offer o : this.offers) {
			LocalDate startingDate = o.getDate();
			
			if (startingDate.isBefore(currentDate)) { // The offer has expired
				if (o.getStatus() != OfferStatus.PAID) {
					this.removeOffer(o);
				}
			}
		}
	}

	/**
	 * This method removes the reservations in the System whose starting date has arrived
	 * without anyone paying for it. It is called every time the system is loaded
	 * from the binary files
	 * 
	 * @param currentDate The current date, which will be used to sort which reservations
	 * are going to be removed from the system
	 */
	private void deleteExpiredReservations(LocalDate currentDate) {
		for (RegisteredUser user : this.authorizedUsers) {
			if (user.getRol() == RegisteredUser.Rol.GUEST) {
				for (Reservation r : ((Guest) user).getReservedOffers()) {
					LocalDate bookingDate = r.getBookingDate();
									    
				    long daysBetween = ChronoUnit.DAYS.between(bookingDate, currentDate);
					
					if (daysBetween >= 5) { // User has exceeded 5 days without paying
						r.cancelReservation();
					}
				}
			}
		}
	}

	/**
	 * This method removes the offers in the System that have not been modified in
	 * fice days after an admin suggested changes
	 * 
	 * @param currentDate The current date, which will be used to sort which offers are
	 * going to be removed from the system
	 */
	private void deleteExpiredPendingOffers(LocalDate currentDate) {
		List<Offer> offers = this.getPendingOffers();
		
		for (Offer o : offers) {
			
			LocalDate changesDate = this.changesRequests.get(o);
		    
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
	
	public void suggestChanges(Offer o, String description) {
		// TODO
	}
	
	public void modifyOffer(Offer o) {
		//TODO especifico de cada atributo
	}
	
	public void approveOffer(Offer o) {
		// TODO
	}


	@Override
	public String toString() {
		return "App[offers=" + offers + ", bannedUsers=" + bannedUsers + ", authorizedUsers=" + authorizedUsers
				+ "]";
	}
	
	// TODO metodo cancelReservation
}
