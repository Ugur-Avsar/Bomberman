package toolbox;

public class StringUtils {
	private static final char[] SPECIAL_CHARS = new char[] { ',', ';', ':', '.', '&', '%', '$', '§', '"', '!', '^', '<',
			'>', '|', '?', '#', '+', '-', '*', '/', '(', ')', '[', ']', '{', '}' };

	public static boolean contains(String s, char c) {
		return s.indexOf(c) >= 0;
	}

	public static boolean containsSpecialChars(String s) {
		if (s != null)
			for (char c : SPECIAL_CHARS)
				if (s.indexOf(c) >= 0)
					return true;
		return false;
	}

	public static boolean isInt(String text) {
		if (text == null)
			return false;

		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}