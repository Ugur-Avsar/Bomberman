package exceptions;

public class BadFrameSizeException extends Exception {
	@Override
	public String getMessage() {
		return "Bad Frame size... The frame sidelength-ratio should be 16:9!";
	}
}
