package exceptions;

public class PaymentException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8830822158013803952L;

	public PaymentException(String description) {
		super(description);
	}
}
