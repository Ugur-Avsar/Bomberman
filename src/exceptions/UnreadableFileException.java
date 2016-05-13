package exceptions;

import java.io.File;

public class UnreadableFileException extends Exception {
	public UnreadableFileException(File file) {
		super("Couldn't read File: " + file.getPath());
	}
}
