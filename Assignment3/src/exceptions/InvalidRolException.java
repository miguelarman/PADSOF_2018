package exceptions;

import application.users.RegisteredUser.Rol;

public class InvalidRolException extends Exception{

	private final String NIF;
	private final Rol rol;
	private final String method;
	
	private static final long serialVersionUID = -1293386080345139163L;
	
	public InvalidRolException(String NIF, Rol rol, String method) {
		this.NIF = NIF;
		this.rol = rol;
		this.method = method;
	}
	@Override
	public String toString() {
		return "The user " + this.NIF + "with rol " + this.rol + "cannot execute the mehthod " + this.method;
	}

}
