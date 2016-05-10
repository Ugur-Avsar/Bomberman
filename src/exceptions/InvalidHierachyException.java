package exceptions;

public class InvalidHierachyException extends Exception {
	public InvalidHierachyException(String message) {
		super("Invalid Container-Hierachy: " + message);
	}
}
