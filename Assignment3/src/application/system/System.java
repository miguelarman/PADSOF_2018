package application.system;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import application.offer.*;
import application.users.*;

import exceptions.*;

public class System implements Serializable{
	
	private static final long serialVersionUID = 7941697892854198940L;
	private List<Offer> offers;
	private List<RegisteredUser> bannedUsers;
	private List<RegisteredUser> authorizedUsers;
	private static RegisteredUser loggedUser;
	private static String filename = "data.obj";
	
	public System() {
		offers = new ArrayList<Offer>();
		bannedUsers = new ArrayList<RegisteredUser>();
		authorizedUsers = new ArrayList<RegisteredUser>();
		loggedUser = null;
	}
	
		
	// Getters


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
					System.loggedUser = user;
					return;
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
		
		System.loggedUser = null;
	}
	
	
	private static System openSystem() {
		System mySystem = null;
		File data = new File(System.filename);

		if (data.exists()) {
			mySystem = System.loadData();
			return mySystem;
		} else {			
			mySystem = System.initializeSystem();
			return mySystem;
		}
	}
	
	
	private static System initializeSystem() {
		
		System system = new System();
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
				if(info[0].equals("A")) {
					RegisteredUser a = new Admin(n.nextToken(), n.nextToken(n.nextToken()), info[3], info[4], info[1]);
					system.authorizedUsers.add(a);
				} else if (info[0].equals("H")){
					RegisteredUser h = new Host(n.nextToken(), n.nextToken(n.nextToken()), info[3], info[4], info[1]);
					system.authorizedUsers.add(h);
					
				} else if (info[0].equals("G")){
					RegisteredUser g = new Guest(n.nextToken(), n.nextToken(n.nextToken()), info[3], info[4], info[1]);
					system.authorizedUsers.add(g);
				}
			}
			
			br.close();
			
			return system;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return system;
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
		
		

		// Deleting expired offers
		
		for (Offer o : system.offers) {
			Date startingDate = o.getDate();
			Date currentDate = new Date();
			
			if (startingDate.before(currentDate)) { // The offer has expired
				if (o.getStatus() != OfferStatus.PAID) {
					system.removeOffer(o);
				}
			}
		}
		
		
		// Deleting expired Reservations

		for (RegisteredUser user : system.authorizedUsers) {
			if (user.getRol() == RegisteredUser.Rol.GUEST) {
				for (Reservation r : ((Guest) user).getReservedOffers()) {
					Date bookingDate = r.getBookingDate();
					Date currentDate = new Date();
					
					long diffInMillies = Math.abs(currentDate.getTime() - bookingDate.getTime());
				    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
					
					if (diff >= 5) { // User has exceeded 5 days without paying
						r.cancelReservation();
					}
				}
			}
		}
		
		return system;
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


	@Override
	public String toString() {
		return "System [offers=" + offers + ", bannedUsers=" + bannedUsers + ", authorizedUsers=" + authorizedUsers
				+ "]";
	}
	
	// TODO metodo cancelReservation
}
