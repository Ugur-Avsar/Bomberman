package toolbox;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeManager {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss ");

	public static String getCurrentTime() {
		return dateFormat.format(Calendar.getInstance().getTime());
	}
}
