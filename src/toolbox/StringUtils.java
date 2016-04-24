package toolbox;

public class StringUtils {
	public static final char[] SPECIAL_CHARS = new char[] { ',', ';', ':', '.', ',', '&', '%', '$', '§', '"', '!', '^',
			'°', '/', '?', '=', '`', '´', '{', '}', '[', ']', '#', 'ü', 'Ü', 'ä', 'Ä', 'ö', 'Ö', '+', '*', '~', '-',
			'²', '³', '<', '>', '|' };

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
}
