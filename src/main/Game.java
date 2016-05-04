package main;

import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_G;
import static java.awt.event.KeyEvent.VK_H;
import static java.awt.event.KeyEvent.VK_J;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_NUMPAD4;
import static java.awt.event.KeyEvent.VK_NUMPAD5;
import static java.awt.event.KeyEvent.VK_NUMPAD6;
import static java.awt.event.KeyEvent.VK_NUMPAD8;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_W;
import static java.awt.event.KeyEvent.VK_Z;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.swing.JFrame;

import entities.Player;
import entityComponents.ControledDirectionsMovement;
import exceptions.BadFrameSizeException;
import inputManagement.Keyboard;
import inputManagement.Mouse;
import levels.Level;
import rendering.Renderer;

public final class Game extends Canvas implements Runnable {

	private Thread thread;

	public static final String TITLE = "GAME";

	public static final int DESKTOP_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int DESKTOP_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	// public static final int DESKTOP_WIDTH = 1280;
	// public static final int DESKTOP_HEIGHT = 720;
	public static final float SCREEN_SCALING_FACTOR = (DESKTOP_WIDTH + DESKTOP_HEIGHT) / (1920f + 1080f);
	public static final int FPS_TARGET = 60;

	private BufferStrategy bs;
	private Graphics2D g;
	public static JFrame parentFrame;
	private boolean running;
	//////////////////////////////////////////////////////// GAME Elements
	private static Level level;

	public Game(JFrame parentFrame) {
		super();
		Game.parentFrame = parentFrame;
		this.setFocusable(true);
		this.addKeyListener(new Keyboard());
		this.addMouseListener(new Mouse());
		initGameElements();
	}

	private void initGameElements() {
		level = new Level(new File("./levels/level.txt"));

		final int playerW = 50;
		final int playerH = 50;
		Player player1 = new Player(0, 0, playerW, playerH, 0, "playerRed", 2, 2, 4, 3);
		player1.addEntityComponent(new ControledDirectionsMovement(player1, 15, VK_A, VK_D, VK_W, VK_S));
		level.addPlayer(player1);

		Player player2 = new Player(0, 0, playerW, playerH, 0, "playerRed", 2, 2, 4, 3);
		player2.addEntityComponent(new ControledDirectionsMovement(player2, 15, VK_LEFT, VK_RIGHT, VK_UP, VK_DOWN));
		level.addPlayer(player2);

		Player player3 = new Player(0, 0, playerW, playerH, 0, "playerRed", 2, 2, 4, 3);
		player3.addEntityComponent(new ControledDirectionsMovement(player3, 15, VK_G, VK_J, VK_Z, VK_H));
		level.addPlayer(player3);

		Player player4 = new Player(0, 0, playerW, playerH, 0, "playerRed", 2, 2, 4, 3);
		player4.addEntityComponent(
				new ControledDirectionsMovement(player4, 15, VK_NUMPAD4, VK_NUMPAD6, VK_NUMPAD8, VK_NUMPAD5));
		level.addPlayer(player4);
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
		thread = new Thread(this, "POS-Projekt-Thread");
		thread.start();
	}

	private void stop() {
		if (!running)
			return;
		running = false;
		System.exit(0);
	}

	@Override
	public void run() {
		System.err.println("Running...");
		createBufferStrategy(2);
		bs = getBufferStrategy();
		g = (Graphics2D) bs.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double nsPerTick = 1000000000.0 / FPS_TARGET;
		long timer = System.currentTimeMillis();
		double unprocessed = 0.0;
		int fps = 0;
		int tps = 0;
		boolean canRender = false;
		long lastTime = System.nanoTime();

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
			// String[] tex = new String[] { "Red", "Blue", "Green", "Yellow" };
			// try {
			// ((Spritesheet)
			// entities.get(0).getTexture()).loadSpritesheet("player" + tex[new
			// Random().nextInt(4)]);
			// } catch (InvalidSpritesheetSizeException e) {
			// e.printStackTrace();
			// }
		}
	}

	private void render() {
		////////////////////////////////// RENDER PART
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1920, 1080);
		Renderer.renderEntity(level, g);
		//////////////////////////////////
		bs.show();
	}

	private void tick() {
		level.update();
	}

	/**
	 * @return the level
	 */
	public static Level getLevel() {
		return level;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Game");
		Game g = new Game(f);

		if (DESKTOP_WIDTH / DESKTOP_HEIGHT != 16 / 9)
			try {
				throw new BadFrameSizeException();
			} catch (BadFrameSizeException e1) {
				e1.printStackTrace();
			}

		f.setSize(DESKTOP_WIDTH, DESKTOP_HEIGHT);
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