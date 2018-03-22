package application.system;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import application.MyApplication;
import application.offer.*;
import application.users.*;

public class System implements Serializable{
	/**
	 * 
	 */
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
		
		File data = new File(System.filename);
		
		if (data.exists()) {
			this.loadData(filename);
		} else {
			this.loadUserInfo()
		}
		
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
	}
}
