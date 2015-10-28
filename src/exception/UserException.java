package exception;

public class UserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserException() {
		super("Entrada de usuarix invalidx!");

	}

	public UserException(String string) {
		super(string);
	}

}
