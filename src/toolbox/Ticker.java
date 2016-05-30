package toolbox;

import java.util.ArrayList;
import java.util.List;

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

	public void incI() {
		i++;
	}

	public void decI() {
		i--;
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

	public static void tick() {
		for (Ticker ticker : tickers) {
			ticker.incI();
		}
	}
}
