package exceptions;

public class HouseAlreadyCreatedException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 668916730650574219L;

	public HouseAlreadyCreatedException(){
		super("This house has already been created");
	}

}
