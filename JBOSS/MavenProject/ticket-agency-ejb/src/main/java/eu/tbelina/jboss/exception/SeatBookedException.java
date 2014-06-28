package eu.tbelina.jboss.exception;

/*
 * Sprawdzany wyjatek
 * 	- przewidziana sytuacja biznesowa
 */
public class SeatBookedException extends Exception {

	private static final long serialVersionUID = 1L;
	public SeatBookedException(String string) {
		super(string);
	}
	public SeatBookedException() {
		super();
	}
}
