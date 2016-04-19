package main;

import static java.awt.event.KeyEvent.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import entities.DynamicEntity;
import entities.Entity;
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
	//public static final int DESKTOP_WIDTH = 1280;
	//public static final int DESKTOP_HEIGHT = 720;
	public static final float SCREEN_SCALING_FACTOR = (DESKTOP_WIDTH + DESKTOP_HEIGHT) / (1920f + 1080f);
	public static final int FPS_TARGET = 60;
	public static final float SPEED_SCALING_FACTOR = FPS_TARGET / 1000;

	private BufferStrategy bs;
	private Graphics2D g;
	public static JFrame parentFrame;
	private boolean running;
	//////////////////////////////////////////////////////// GAME Elements
	private ArrayList<Entity> entities;
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
		entities = new ArrayList<Entity>();
		DynamicEntity player;
		player = new DynamicEntity(700, 700, 80, 75, 0, "playerBlue", 2, 2, 4, 3);
		player.addEntityComponent(new ControledDirectionsMovement(player, 15, VK_A, VK_D, VK_W, VK_S));
		entities.add(player);
		level = new Level(new File("./levels/level.txt"));
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
			// ((DynamicEntity) entities.get(0)).getSpritesheet()
			// .loadSpritesheet("player" + tex[new Random().nextInt(4)]);
		}
	}

	private void render() {
		////////////////////////////////// RENDER PART
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1920, 1080);
		for (Entity e : entities) {
			Renderer.renderEntity(level, entities, g);
		}
		//////////////////////////////////
		bs.show();
	}

	private void tick() {
		Mouse.update();
		for (Entity e : entities)
			e.update();
	}

	/**
	 * @return the level
	 */
	public static Level getLevel() {
		return level;
	}

	@SuppressWarnings("unused")
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