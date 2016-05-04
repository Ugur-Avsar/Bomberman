package levels;

import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Player;
import exceptions.UnvalidLevelFormatException;
import resources.Texture;

public class Level {
	private Texture texture;

	private String name;

	private List<Polygon> collisionBoxes;
	private List<Point> playerSpawns;

	private List<Player> players;

	public Level(File levelFile) {
		playerSpawns = new ArrayList<Point>();
		collisionBoxes = new ArrayList<Polygon>();
		players = new ArrayList<Player>();
		try {
			initLevel(levelFile);
		} catch (UnvalidLevelFormatException e) {
			e.printStackTrace();
		}
	}

	public void addPlayer(Player player) {
		if (players.size() < playerSpawns.size()) {
			player.setX(playerSpawns.get(players.size()).getX() - player.getWidth() / 2);
			player.setY(playerSpawns.get(players.size()).getY() - player.getHeight() / 2);
			players.add(player);
		}
	}

	private void initLevel(File levelFile) throws UnvalidLevelFormatException {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(levelFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			this.name = reader.readLine();
			this.playerSpawns = new ArrayList<Point>(Integer.parseInt(reader.readLine()));
			this.texture = new Texture(reader.readLine());
		} catch (IOException | NumberFormatException e) {
			throw new UnvalidLevelFormatException();
		}

		initCollisionBoxes(reader);
		initPlayerSpawns(reader);

		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initCollisionBoxes(BufferedReader reader) throws UnvalidLevelFormatException {
		try {
			String line = reader.readLine();
			String[] data = line.split(";");

			List<String> xCoords = new ArrayList<String>();
			List<String> yCoords = new ArrayList<String>();

			int[] xPoints = null;
			int[] yPoints = null;

			while (line != null && !line.equals("")) {
				data = line.split(";");
				for (String s : data[0].split(",")) {
					xCoords.add(s);
					System.out.println(s);
				}
				for (String s : data[1].split(",")) {
					yCoords.add(s);
					System.out.println(s);
				}

				xCoords.remove(0);
				yCoords.remove(0);

				xPoints = new int[xCoords.size()];
				yPoints = new int[yCoords.size()];

				for (int i = 0; i < xCoords.size() && i < yCoords.size(); i++) {
					xPoints[i] = Integer.parseInt(xCoords.get(i));
					yPoints[i] = Integer.parseInt(yCoords.get(i));
				}

				collisionBoxes.add(new Polygon(xPoints, yPoints, xPoints.length));

				line = reader.readLine();
				xCoords.clear();
				yCoords.clear();
			}
		} catch (IOException | NumberFormatException e) {
			throw new UnvalidLevelFormatException();
		}
	}

	private void initPlayerSpawns(BufferedReader reader) throws UnvalidLevelFormatException {
		try {
			String line = reader.readLine();
			String[] data = null;

			while (line != null) {
				data = line.split("/");
				playerSpawns.add(new Point(Integer.parseInt(data[0]), Integer.parseInt(data[1])));

				line = reader.readLine();
			}
		} catch (IOException | NumberFormatException e) {
			throw new UnvalidLevelFormatException();
		}
	}

	/**
	 * @return the texture
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * @param texture
	 *            the texture to set
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	/**
	 * @return the collisionBoxes
	 */
	public List<Polygon> getCollisionBoxes() {
		return collisionBoxes;
	}

	public boolean walkable(double x, double y, double width, double height) {
		for (Polygon polygon : collisionBoxes) {
			if (polygon.intersects(x, y, width, height))
				return false;
		}
		return true;
	}

	public void update() {
		for (Player player : players) {
			player.update();
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Point> getSpawns() {
		return playerSpawns;
	}
}