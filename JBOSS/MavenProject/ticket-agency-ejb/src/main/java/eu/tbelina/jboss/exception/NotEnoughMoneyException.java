package eu.tbelina.jboss.exception;

/*
 * Wyjątek wykonania
 *  - zgłaszany gdy nie można już nic zrobić (np.brak połączenia z bazą danych)
 */
public class NotEnoughMoneyException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NotEnoughMoneyException(String string) {
		super(string);
	}
	public NotEnoughMoneyException() {
		super();
	}
}
