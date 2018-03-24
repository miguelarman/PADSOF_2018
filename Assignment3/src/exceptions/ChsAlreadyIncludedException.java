package exceptions;

public class ChsAlreadyIncludedException extends Exception {
	private static final long serialVersionUID = 668916730650574219L;

	public ChsAlreadyIncludedException(){
		super("This house has already been created");
	}

}
