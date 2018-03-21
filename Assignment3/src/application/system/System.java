package application.system;
import java.util.*;

import application.offer.Offer;
import application.offer.OfferType;
import application.users.Admin;
import application.users.RegisteredUser;

public class System {
	private List<Admin> admins;
	private static Admin loggedAdmin;
	private List<Offer> offers;
	private List<RegisteredUser> bannedUsers;
	private List<RegisteredUser> authorizedUsers;
	private static RegisteredUser loggedUser;	
	
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
		return null;
		
		
	}
	
	public List<Offer> searchStartingDate(Date date1, Date date2) {
		return null;
		
	}
	
	public List<Offer> searchOfferType(OfferType type) {
		return null;
		
		
	}
	
	public List<Offer> searchBooked() {
		return null;
		
		
	}
	
	public List<Offer> searchPaid() {
		return null;
		
	}
	
	public List<Offer> searchAvgRating(Float minRating) {
		return null;
		
		
	}
	
	public Boolean login(String id, String passwd) {
		return true;
		
	}
	
	public Boolean logout() {
		return true;
		
		
	}
	
	public Boolean addOffer(Offer offer) {
		return true;
		
	}
	
	public Boolean removeOffer(Offer offer) {
		return true;
		
		
	}
	
	public Boolean banUser(RegisteredUser user) {
		return true;
		
	}
	
	public Boolean unbanUser(RegisteredUser user) {
		return true;
		
	}
}
