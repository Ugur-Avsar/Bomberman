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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import combat.BombMaster;
import combat.Player;
import entities.DynamicEntity;
import entities.Entity;
import entities.EntityMaster;
import entityComponents.SinusMovement;
import exceptions.BadFrameSizeException;
import graphics.MovingSpriteConfiguration;
import graphics.Renderer;
import ingameMenu.IngameMenue;
import ingameMenu.OpenOnEscapeListener;
import inputManagement.Keyboard;
import inputManagement.Mouse;
import levels.Level;
import settings.SettingsDialog;
import toolbox.Ticker;
import toolbox.TimeManager;

/**
 * 
 * @author Avsar Ugur & Kevin Kulcsar
 *
 */
public final class Game extends Canvas implements Runnable {
	private Thread thread;

	public static final String TITLE = "Bomberman HD - by Ugur A. & Kevin K.";
	// public static final int DESKTOP_WIDTH = (int)
	// Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	// public static final int DESKTOP_HEIGHT = (int)
	// Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static final int DESKTOP_WIDTH = 1280;
	public static final int DESKTOP_HEIGHT = 720;
	public static final double SCREEN_SCALING_FACTOR = (DESKTOP_WIDTH + DESKTOP_HEIGHT) / (1920f + 1080f);
	public static final int FPS_TARGET = 60;

	private BufferStrategy bs;
	private Graphics2D g;
	public JFrame topLevelFrame;
	private boolean running;
	//////////////////////////////////////////////////////// GAME Elements
	private Level level;
	private Ticker ticker;
	private SinusMovement s;

	private void initGameElements() {
		level = new Level(new File("./levels/level.txt"));
		topLevelFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

		final int playerW = 90;
		final int playerH = 110;

		Player player1 = new Player(this, playerW, playerH, 0, "sora", 2, 2,
				new MovingSpriteConfiguration(4, 4, 15, 4, 8, 12, 0, 4), VK_A, VK_D, VK_W, VK_S);
		level.addPlayer(player1);

		Player player2 = new Player(this, playerW, playerH, 0, "sora", 2, 2,
				new MovingSpriteConfiguration(4, 4, 15, 4, 8, 12, 0, 4), VK_A, VK_D, VK_W, VK_S);
		level.addPlayer(player2);

		Player player3 = new Player(this, playerW, playerH, 0, "sora", 2, 2,
				new MovingSpriteConfiguration(4, 4, 15, 4, 8, 12, 0, 4), VK_A, VK_D, VK_W, VK_S);
		level.addPlayer(player3);

		Player player4 = new Player(this, playerW, playerH, 0, "sora", 2, 2,
				new MovingSpriteConfiguration(4, 4, 15, 4, 8, 12, 0, 4), VK_A, VK_D, VK_W, VK_S);
		level.addPlayer(player4);

		DynamicEntity e1 = new DynamicEntity(this, 0, 0, 100, 15, 0, "levels/white", 1, 1, 1, 1, 0);
		s = new SinusMovement(e1, 64, 0, 4, 4, 0, 0, 0, 0);
		e1.addEntityComponent(s);
		EntityMaster.addEntity(e1);

		DynamicEntity e2 = new DynamicEntity(this, 1820, 1065, 100, 15, 0, "levels/white", 1, 1, 1, 1, 0);
		s = new SinusMovement(e2, -64, 0, 4, 4, 0, 0, 0, 0);
		e2.addEntityComponent(s);
		EntityMaster.addEntity(e2);
	}

	public boolean isRunning() {
		return running;
	}

	public Graphics2D getGraphics2D() {
		return g;
	}

	public void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this, "BombermanHD - Thread");
		thread.start();
	}

	public void stop() {
		if (!running)
			return;
		running = false;
		System.exit(0);
	}

	@Override
	public void run() {
		initGameElements();
		this.setFocusable(true);
		this.addKeyListener(new Keyboard());
		this.addMouseListener(new Mouse());

		System.err.println("Running...");
		initGameElements();
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

		ticker = new Ticker();

		requestFocus();
		while (running) {
			if (isEnabled()) {
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
					System.out.printf(TimeManager.getCurrentTime() + "... FPS: %d | TPS: %d\n", fps, tps);
					fps = 0;
					tps = 0;
					System.out.println("-----------------------------------------------------");
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void render() {
		////////////////////////////////// RENDER PART
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1920, 1080);
		Renderer.render(this, level, g);
		////////////////////////////////// RENDER PART
		bs.show();
	}

	private void tick() {
		EntityMaster.update();
		BombMaster.update();
		Ticker.tick();
		if (ticker.getI() % 60 == 0)
			s.setyCos(!s.isyCos());
	}

	/**
	 * @return the level
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * @return the topLevelFrame
	 */
	public JFrame getTopLevelFrame() {
		return topLevelFrame;
	}

	public static void main(String[] args) {
		Game.createNewGame();
	}

	public static void createNewGame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		JFrame f = new JFrame(TITLE);
		Game g = new Game();

		f.setLayout(null);
		f.setSize(DESKTOP_WIDTH, DESKTOP_HEIGHT);
		f.setLocationRelativeTo(null);
		f.setUndecorated(false);
		f.setFocusable(true);

		g.setSize(DESKTOP_WIDTH, DESKTOP_HEIGHT);
		g.setLocation(0, 0);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.err.println("Game exits!");
				g.stop();
			}
		});
		g.start();f.add(g);

		f.setResizable(false);
		f.setVisible(true);
	}
}