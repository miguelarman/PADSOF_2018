package exceptions;


public class OfferAlreadyCreatedException extends Exception{

	private static final long serialVersionUID = -8425575652000587826L;

	public OfferAlreadyCreatedException(){}
	
	@Override
	public String toString() {
		return "The offer has aready been created";
	}

}
