package exceptions;

import java.time.LocalDate;

public class InvalidDateException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -543809079168629693L;
	private final LocalDate date;
	public InvalidDateException(LocalDate date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "The date " + this.date + " is not valid";
	}

}
