package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public final class Game extends Canvas implements Runnable {

	public static final String TITLE = "GAME";
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	private BufferStrategy bs;
	private Graphics2D g;
	private JFrame parentFrame;
	private boolean running;
	//////////////////////////////////////////////////////// GAME Elements

	public Game(JFrame f) {
		super();
		parentFrame = f;
		this.setFocusable(true);
		initGameElements();
	}

	private void initGameElements() {

	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Graphics2D getGraphics2D() {
		return g;
	}

	private void start() {
		if (running)
			return;
		running = true;
		new Thread(this, "Main-Thread").start();
	}

	private void stop() {
		if (!running)
			return;
		running = false;
		System.exit(0);
	}

	@Override
	public void run() {
		System.err.println("running...");
		createBufferStrategy(3);
		bs = getBufferStrategy();
		g = (Graphics2D) bs.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double target = 60.0;
		double nsPerTick = 1000000000.0 / target;
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double unprocessed = 0.0;
		int fps = 0;
		int tps = 0;
		boolean canRender = false;

		requestFocus();
		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;

			if (unprocessed >= 1.0) {
				tick();
				unprocessed--;
				tps++;
				canRender = true;
			} else
				canRender = false;

			if (canRender) {
				render();
				fps++;
			}

			if (System.currentTimeMillis() - 1000 > timer) {
				timer += 1000;
				System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
				fps = 0;
				tps = 0;
				System.out.println("-----------------------------------------------------");
			}
		}
	}

	private void render() {
		////////////////////////////////// RENDER PART
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1920, 1080);
		g.setColor(Color.BLACK);
		g.fillRect(500, 500, 500, 500);
		//////////////////////////////////
		bs.show();
	}

	private void tick() {

	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Game");
		Game g = new Game(f);
		f.setSize(WIDTH, HEIGHT);
		f.setLocationRelativeTo(null);
		f.setUndecorated(true);
		f.setFocusable(true);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.err.println("Game exits!");
				g.stop();
			}
		});
		f.add(g);
		f.setResizable(false);
		f.setVisible(true);
		f.requestFocus();
		g.start();
	}
}