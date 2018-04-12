package exceptions;

import java.time.LocalDate;

/**
* @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
*         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
*         
*/
public class InvalidDateException extends Exception{
	
	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = -543809079168629693L;
	
	/**
	 * Invalid date
	 */
	private final LocalDate date;
	
	/**
	 * Constructor for the class InvalidDateException
	 * @param date Invalid date
	 */
	public InvalidDateException(LocalDate date) {
		this.date = date;
	}
	
	/**
	 * Getter method for date
	 * @return Invalid date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Method to generate the string to print the exception message
	 */
	@Override
	public String toString() {
		return "The date " + this.date + " is not valid";
	}

}
