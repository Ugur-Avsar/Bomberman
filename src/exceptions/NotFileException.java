package exceptions;

import java.io.File;

public class NotFileException extends Exception {
	public NotFileException(File file) {
		super("Given File-Object is a directory! ... " + file.getPath());
	}
}
