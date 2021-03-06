package toolbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Z�hlt Frames. Wird bei jedem Tick durch die statische Methode 'tick()'
 * auktualisiert.
 * 
 * @author AvsarUgur, KulcsarKevin
 *
 */
public class Ticker {
	private static List<Ticker> tickers = new ArrayList<Ticker>();
	private int i;

	public Ticker(boolean start, int startvalue) {
		this.i = startvalue;
		if (start)
			start();
	}

	public Ticker() {
		this.i = 0;
		start();
	}

	public static void tick() {
		for (Ticker ticker : tickers) {
			ticker.incI();
		}
	}

	public void incI() {
		i++;
	}

	public void decI() {
		i--;
	}

	public boolean isTicking() {
		return tickers.contains(this);
	}

	public void start() {
		if (!tickers.contains(this))
			tickers.add(this);
	}

	public boolean stop() {
		return tickers.remove(this);
	}

	public int getI() {
		return i;
	}

	public void reset() {
		i = 1;
	}
}
