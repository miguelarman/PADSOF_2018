package application;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import application.dates.ModifiableDate;
import application.offer.*;
import application.users.*;
import application.users.RegisteredUser.Role;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import exceptions.*;

/**
 * Class that stores all the data needed to get the app working. This includes
 * user data, offers, ... As it is Serializable, it can be writen and read from
 * binary files, in order to store data for later uses
 * 
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
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
	 * HashMap that stores the amount of money to be paid
	 */
	private HashMap<RegisteredUser, Double> toPay; // Must be RegisteredUser as there are multirole users
	
	/**
	 * Constructor of the class App. Inicializes all the Lists and Maps inside the
	 * App
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
	 * 
	 * @return list of offers in the system
	 */
	public List<Offer> getOffers() {
		return offers;
	}

	/**
	 * Getter method for bannedUsers
	 * 
	 * @return list of banned users in the system
	 */
	public List<RegisteredUser> getBannedUsers() {
		return bannedUsers;
	}

	/**
	 * Getter method for authorizedUsers
	 * 
	 * @return list of authorized users in the system
	 */
	public List<RegisteredUser> getAuthorizedUsers() {
		return authorizedUsers;
	}

	/**
	 * Getter method for loggedUser
	 * 
	 * @return user that is currently logged in
	 */
	public static RegisteredUser getLoggedUser() {
		return loggedUser;
	}

	//Setters
	
	/**
	 * Setter method for loggedUser
	 * 
	 * @param loggedUser User that logs into the system
	 */
	public void setLoggedUser(RegisteredUser loggedUser) {
		App.loggedUser = loggedUser;
	}
	
	// Searches
	
	/**
	 * Method that searches all the offers that have a house with the given ZIP code
	 * 
	 * @param zip ZIP code of the house in the offer
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
	 * 
	 * @param date1 Starting or ending date of the interval
	 * @param date2 Starting or ending date of the interval
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
	 * 
	 * @param type Type of offer that we want to search
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
	 * 
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
	 * 
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
	 * 
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
	 * Method that with a given id (NIF) and a password checks if the user is an
	 * authorized user and lets that user go into the system
	 * 
	 * @param id NIF of the user trying to log in
	 * @param passwd Password of the user trying to log in
	 * @throws UserIsBannedException When the user that is trying to log is been banned
	 * @throws IncorrectPasswordException When the password with which the user
	 * is trying to log is incorrect
	 * @throws UnexistentUserException When the user with the specified id cannot be found on the system
	 */
	public void login(String id, String passwd) throws UserIsBannedException, IncorrectPasswordException, UnexistentUserException,AUserIsAlreadyLoggedException {
		
		if(App.loggedUser != null) {
			throw new AUserIsAlreadyLoggedException();
		} else {
			for (RegisteredUser user : this.bannedUsers) { //Checks if the user trying to log in is banned
				if (user.getNIF().equals(id)) {
					throw new UserIsBannedException(user);
				}
			}
			for (RegisteredUser user : this.authorizedUsers) {
				if (user.getNIF().equals(id)) { //Checks if that NIF is stored as a authorized user NIF
					if (user.getPasswd().equals(passwd)) { //Checks if the user's password matches with the one stored in the system
						App.loggedUser = user; //Sets the user as logged
						return;
					} else {
						throw new IncorrectPasswordException(user, passwd);
					}
				}
			}
			throw new UnexistentUserException(id);
		}
	}
	
	/**
	 * Method that logs out the user 
	 */
	public void logout() {
		
		App.loggedUser = null; //Logs the user out
	}
	
	/**
	 * Method that closes the app, dumping the information into the file filename
	 */
	public void closeApp() {
		
		if(App.loggedUser != null) {
			App.loggedUser = null;
		}
		ObjectOutputStream oos;
		
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
	 * 
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
	 * 
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

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Deleting expired offers
		app.deleteExpiredOffers();
		
		// Deleting expired Reservations
		app.deleteExpiredReservations();
		
		// Deleting expired offers pending changes
		app.deleteExpiredPendingOffers();
		
		// Try to pay the pending payments
		app.payPendingOffers();
		
		return app;
	}
	
	/**
	 * This method tries to pay all the money that has not been paid to the hosts
	 */
	public void payPendingOffers() {
		Set<RegisteredUser> pendingHosts = this.toPay.keySet();
		
		for (RegisteredUser user : pendingHosts) {
			Double amount = this.toPay.get(user);
			
			// Trying to pay
			String subject = "__________"; // TODO
			
			try {
				TeleChargeAndPaySystem.charge(user.getCreditCard(), subject, -amount);// It is negative to pay the host, not charge
			} catch (OrderRejectedException e) {
				continue;
			}
			
			// If payment is successfull remove host from people we owe money
			
			this.toPay.remove(user);
		}
	}


	/**
	 * This method removes the offers in the System whose starting date has arrived
	 * without anyone paying for it. It is called every time the system is loaded
	 * from the binary files
	 */
	private void deleteExpiredOffers() {
		for (Offer o : this.offers) {
			LocalDate startingDate = o.getDate();
			LocalDate currentDate = App.getCurrentDate();
			
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
	 */
	private void deleteExpiredReservations() {
		for (RegisteredUser user : this.authorizedUsers) {
			if (user.getRole() == RegisteredUser.Role.GUEST) {
				for (Reservation r : ((Guest) user).getReservedOffers()) {
					LocalDate bookingDate = r.getBookingDate();
					LocalDate currentDate = App.getCurrentDate();
									    
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
	 */
	private void deleteExpiredPendingOffers() {
		List<Offer> offers = this.getPendingOffers();
		
		for (Offer o : offers) {
			LocalDate changesDate = this.changesRequests.get(o);
			LocalDate currentDate = App.getCurrentDate();
		    
		    long daysBetween = ChronoUnit.DAYS.between(changesDate, currentDate);
			
			if (daysBetween >= 5) { // User has exceeded 5 days without paying
				// Deletes the request from the app
				this.changesRequests.remove(o);
				
				// Removes the offer from the system
				this.removeOffer(o);
			}
		}
	}

	/**
	 * Method that returns all the offers in the system that are pending for
	 * approval
	 * 
	 * @return The list of offers that are pending
	 */
	private List<Offer> getPendingOffers() {
		List<Offer> offers = new ArrayList<Offer>();
		
		for (Offer o : this.offers) {
			if (o.getStatus() == OfferStatus.PENDING_FOR_APPROVAL) {
				offers.add(o);
			}
		}
		
		return offers;
	}
	
	/**
	 * Method that creates a living offer and loads it into the system
	 * @param startingDate
	 * @param price
	 * @param deposit
	 * @param description
	 * @param offeredHouse
	 * @param numberOfMonths
	 * @throws InvalidRolException
	 * @throws NoUserLoggedException
	 * @throws InvalidDateException
	 * @throws NotTheOwnerException
	 */
	
	public void createLivingOffer(LocalDate startingDate, Double price, Double deposit, String description, House offeredHouse, int numberOfMonths) throws InvalidRolException, NoUserLoggedException, InvalidDateException, NotTheOwnerException {
		Offer o= null;
		
		if(startingDate.isBefore(App.getCurrentDate())){
			throw new InvalidDateException(startingDate);
		}
		
		else if(App.loggedUser == null) {
			throw new NoUserLoggedException();
		}
		
		else if(App.loggedUser.getRole().equals(Role.HOST) || App.loggedUser.getRole().equals(Role.MULTIROLE)){ //Checks if the loggedUser is a host
			
			if(offeredHouse.getHost().equals(App.getLoggedUser())){
				o = new LivingOffer(startingDate, price, deposit, description, offeredHouse, numberOfMonths);
				offers.add(o);
			} else {
				throw new NotTheOwnerException(offeredHouse, App.getLoggedUser());
			}
		}
		else {
			throw new InvalidRolException(App.loggedUser.getNIF(), App.loggedUser.getRole(), "createLivingOffer");
		}

	}
	
	public void createHolidayOffer(LocalDate startingDate, Double price, Double deposit, String description, House offeredHouse, LocalDate finishDate) throws InvalidRolException, NoUserLoggedException, InvalidDateException, NotTheOwnerException {
		Offer o= null;
		
		if(finishDate.isBefore(startingDate)) {
			LocalDate aux = finishDate;
			finishDate = startingDate;
			startingDate = aux;
			aux = null;
		}
		if(startingDate.isBefore(App.getCurrentDate())){
			throw new InvalidDateException(startingDate);
		}

		if(App.loggedUser == null) {
			throw new NoUserLoggedException();
		}
		
		else if(App.loggedUser.getRole().equals(Role.HOST) || App.loggedUser.getRole().equals(Role.MULTIROLE)){ //Checks if the loggedUser is a host
			
			if(offeredHouse.getHost().equals(App.getLoggedUser())){
				o = new HolidayOffer(startingDate, price, deposit, description, offeredHouse, finishDate);
				offers.add(o);
			} else {
				throw new NotTheOwnerException(offeredHouse, App.getLoggedUser());
			}
		}
		else {
			throw new InvalidRolException(App.loggedUser.getNIF(), App.loggedUser.getRole(), "createHolidayOffer");
		}

	}

	/**
	 * Method that removes an offer from the App
	 * 
	 * @param offer Offer to be removed
	 */
	public void removeOffer(Offer offer) {
		this.offers.remove(offer);
	}
	
	/**
	 * Method that bans a user from the App
	 * 
	 * @param user User to be banned
	 */
	public void banUser(RegisteredUser user) {
		this.authorizedUsers.remove(user);
		this.bannedUsers.add(user);
		
		// TODO hacer logout aqui o donde se llame a esta funcion
		// TODO comprobar si es admin el que llama a esta funcion
	}
	
	/**
	 * Method that unbans a user from the app
	 * 
	 * @param user User to be unbanned
	 */
	public void unbanUser(RegisteredUser user) {
		this.authorizedUsers.add(user);
		this.bannedUsers.remove(user);

		// TODO comprobar si es admin el que llama a esta funcion
	}
	
	/**
	 * Method used by Admins to suggest changes in an Offer in order to approve it
	 * 
	 * @param o Offer that the admin has reviewed
	 * @param description Description of the suggestion of changes
	 */
	public void suggestChanges(Offer o, String description) {
		if (o.getStatus() != OfferStatus.PENDING_FOR_APPROVAL) { // Can only suggest changes to an offer pending for
																 // approval
			return;
		}
		
		o.modifyOffer(OfferStatus.PENDING_FOR_CHANGES);
		
		this.changesRequests.put(o, App.getCurrentDate());
	}


	/**
	 * Method used by an Admin to mark an offer as approved
	 * 
	 * @param o Offer to be approved
	 * @throws OfferIsPendingForChangesExceptions When the offer that is
	 * trying to be approved is still pending for changes
	 */
	public void approveOffer(Offer o) throws OfferIsPendingForChangesExceptions{
		
		if (o.getStatus() == OfferStatus.PENDING_FOR_CHANGES) {
			throw new OfferIsPendingForChangesExceptions(o);
		}
		o.modifyOffer(OfferStatus.APPROVED); // Mark the offer as approved
		
		this.changesRequests.remove(o); // Remove the changes suggestion from the HashMap
	}
	
	/**
	 * Method used by a Guest in order to request approval of an Offer after changes
	 * have been made
	 * 
	 * @param o Offer to be reviewed
	 */
	public void requestRevision(Offer o) {
		if (o.getStatus() != OfferStatus.PENDING_FOR_CHANGES) { // Cannot request this if the offer has been approved
			return;
		}
		
		o.modifyOffer(OfferStatus.PENDING_FOR_APPROVAL); // Mark the offer as pending for approval
		
		this.changesRequests.remove(o);
	}
	
	/**
	 * Method that returns the current date of the app. Used to simulate dates in
	 * the testers. When not testing, it can be changed to the now method of the
	 * class LocalDate
	 * 
	 * @return Current date of the app
	 */
	private static LocalDate getCurrentDate() {
		return ModifiableDate.getModifiableDate();
	}
	
	/**
	 * Method that creates if the logged user has a valid role
	 * 
	 * @param zip ZIP code of the house
	 * @param city City in which the the house is located
	 * @throws InvalidRolException When the logged user is neither a host nor a multiRoleUser
	 * @throws NoUserLoggedException When the app could not retrieve the logged user
	 * @return the created house
	 */
	public House createHouse(Integer zip, String city) throws InvalidRolException, NoUserLoggedException {
		House h = null;
		if(App.loggedUser == null) {
			throw new NoUserLoggedException();
		}
		else if(App.loggedUser.getRole().equals(Role.HOST)){ //Checks if the loggedUser is a host
			h = new House(zip, city, (Host)App.loggedUser);
		}
		else if(App.loggedUser.getRole().equals(Role.MULTIROLE)) { //Checks if the loggedUser is a multirole
			h = new House(zip, city, (MultiRoleUser)App.loggedUser);
			
		}
		else {
			throw new InvalidRolException(App.loggedUser.getNIF(), App.loggedUser.getRole(), "createHouse");
		}
		return h;
	}
	
	/**
	 * Method that adds a house to the system if the owner of the house and the logged user match, 
	 * and if the logged user is a host
	 * 
	 * @param house House to add to the system
	 * @throws InvalidRolException When the logged user is neither a host nor a multiRoleUser
	 * @throws NoUserLoggedException When the app could not retrieve the logged user
	 * @throws NotTheOwnerException When the logged user is not the owner of the house
	 */
	public void addHouse(House house) throws InvalidRolException, NoUserLoggedException, NotTheOwnerException{
		
		if(App.loggedUser == null) {
			throw new NoUserLoggedException();
		}
		else if(App.loggedUser.getRole().equals(Role.HOST)){ //Checks if the loggedUser is a host
			if(house.getHost().equals(App.getLoggedUser())){
				Host user = (Host)App.getLoggedUser();
				user.getHouses().add(house);
			} else {
				throw new NotTheOwnerException(house, App.loggedUser);
			}

		}
		else if(App.loggedUser.getRole().equals(Role.MULTIROLE)) { //Checks if the loggedUser is a multirole
			if(house.getHost().equals(App.getLoggedUser())){
				MultiRoleUser user = (MultiRoleUser)App.getLoggedUser();
				user.getHouses().add(house);
			} else {
				throw new NotTheOwnerException(house, App.loggedUser);
			}
		}
		else {
			throw new InvalidRolException(App.loggedUser.getNIF(), App.loggedUser.getRole(), "addHouse");
		}			
	}
	
	// TODO javadoc
	public void payOffer(Offer o) {
		try {
			o.payOffer();
		} catch (InvalidCardNumberException e) {
			this.banUser(App.getLoggedUser());
		} catch (NoUserLoggedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CouldNotPayHostException e) {
			RegisteredUser h = e.getHost();
			Double amount = e.getAmount();
			
			this.addDebt(h, amount);
		}
	}


	// TODO javadoc
	public void payReservation(Reservation r) {
		try {
			r.payReservation();
		} catch (NotTheReserverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCardNumberException e) {
			this.banUser(App.getLoggedUser());
		} catch (CouldNotPayHostException e) {
			RegisteredUser user = e.getHost();
			Double amount = e.getAmount();
			
			this.addDebt(user, amount);
		}
	}
	
	// TODO javadoc
	private void addDebt(RegisteredUser user, Double amount) {
		if (this.toPay.containsKey(user)) {
			this.toPay.put(user, this.toPay.get(user) + amount);
		} else {
			this.toPay.put(user, amount);
		}
	}
	
	@Override
	/**
	 * Method that returns all the information stored in an object of the class App in a printable and readable format
	 * 
	 * @return Information stored in the app in a printable format
	 */
	public String toString() {
		String string = "";
		int i = 1;
		string += "Offers:";
		for(Offer o: offers) {
			string += "\n\n(" + i + ")" + "\n";
			string += o;
			i++;
		}
		i = 1;
		string += "\nBannedUsers:";
		for(RegisteredUser r: bannedUsers) {
			string += "\n\n(" + i + ")" + "\n";
			string += r;
			i++;
		}
		i = 1;
		string += "\nAuthorizedUsers:";
		for(RegisteredUser a: authorizedUsers) {
			string += "\n\n(" + i + ")" + "\n";
			string += a;
			i++;
		}
		string+= "\n\nLoggedUser:\n" + App.getLoggedUser();
		return string;
	}
	
	// TODO metodo cancelReservation
}
