package exceptions;

public class InvalidTextureFormatException extends Exception {
	@Override
	public String getMessage() {
		return "Invalid Texture-File-Format... File type must be .png!";
	}
}