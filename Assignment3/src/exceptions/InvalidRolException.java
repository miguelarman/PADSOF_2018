package exceptions;

import application.users.RegisteredUser.Role;

public class InvalidRolException extends Exception{

	private final String NIF;
	private final Role rol;
	private final String method;
	
	private static final long serialVersionUID = -1293386080345139163L;
	
	public InvalidRolException(String NIF, Role rol, String method) {
		this.NIF = NIF;
		this.rol = rol;
		this.method = method;
	}
	@Override
	public String toString() {
		return "The user " + this.NIF + " with rol " + this.rol + " cannot execute the method " + this.method;
	}

}
