package toolbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exceptions.UnreadableFileException;

public class IOTool {
	public static String getString(String filepath, boolean enableLineWrap) {
		String lineWrap = enableLineWrap ? "\n" : "";
		File file = new File(filepath);
		if (file.isFile()) {
			if (file.canRead()) {
				String result = "";
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader(file));
					for (String line = reader.readLine(); line != null && !line.equals(""); line = reader.readLine()) {
						result += line + lineWrap;
					}
					reader.close();
					return result;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				new UnreadableFileException(file).printStackTrace();
			}
		}
		return null;
	}
}
