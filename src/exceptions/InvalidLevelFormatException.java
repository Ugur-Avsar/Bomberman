package exceptions;

public class InvalidLevelFormatException extends Exception {
	@Override
	public String getMessage() {
		return "Unvalid Format in Level-File!";
	}
}