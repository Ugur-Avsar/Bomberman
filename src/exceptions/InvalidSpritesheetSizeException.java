package exceptions;

public class InvalidSpritesheetSizeException extends Exception {
	@Override
	public String getMessage() {
		return "Invalid Spritesheet-Size... Spritesheet-Size has to be divisible through the row-/collumn-Count";
	}
}