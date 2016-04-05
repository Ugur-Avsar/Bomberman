package exceptions;

public class UnvalidLevelFormatException extends Exception {
	@Override
	public String getMessage() {
		return "Unvalid Format in Level-File!";
	}
}