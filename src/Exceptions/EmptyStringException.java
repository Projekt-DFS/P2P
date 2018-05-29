package Exceptions;

@SuppressWarnings("serial")
public class EmptyStringException extends IllegalArgumentException {

	private static final String MSG_INPUT = "Falsche Eingabe!";
	
	public EmptyStringException() {
		super(MSG_INPUT);
	}

}
