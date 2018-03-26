package application.users;

public class Admin {
	
	private String id;
	private String passwd;
	
	public Admin(String id, String passwd) {
		this.id = id;
		this.passwd = passwd;
	}

	
	public String getId() {
		return id;
	}

	
	public String getPasswd() {
		return passwd;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
	@Override
	public String toString() {
		// TODO
		
		return null;
	}
}
