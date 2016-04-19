package levels;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.DynamicEntity;
import entities.StaticEntity;
import exceptions.UnvalidLevelFormatException;
import main.Game;
import resources.Texture;

public class Level {
	private Texture texture;

	private String name;
	private int maxPlayers;

	private List<Polygon> collisionBoxes;
	private List<StaticEntity> entities;
	private List<DynamicEntity> players;

	public Level(File levelFile) {
		players = new ArrayList<DynamicEntity>();
		entities = new ArrayList<StaticEntity>();
		collisionBoxes = new ArrayList<Polygon>();
		try {
			initLevel(levelFile);
		} catch (UnvalidLevelFormatException e) {
			e.printStackTrace();
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
			this.maxPlayers = Integer.parseInt(reader.readLine());
			this.texture = new Texture(reader.readLine());
		} catch (IOException | NumberFormatException e) {
			throw new UnvalidLevelFormatException();
		}

		// initEntities(reader);
		initCollisionBoxes(reader);

		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initCollisionBoxes(BufferedReader reader) throws UnvalidLevelFormatException {
		try {
			String line = reader.readLine();
			String[] data = null;
			List<String> xCoords = null;
			List<String> yCoords = null;

			int[] xPoints = null;
			int[] yPoints = null;

			while (line != null) {
				data = line.split(";");

				xCoords = new ArrayList<String>(Arrays.asList(data[0]));
				yCoords = new ArrayList<String>(Arrays.asList(data[1]));

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
			}
		} catch (IOException | NumberFormatException e) {
			throw new UnvalidLevelFormatException();
		}
	}

	// private void initEntities(BufferedReader reader) throws
	// UnvalidLevelFormatException {
	// try {
	// String line = reader.readLine();
	// String[] data = null;
	// StaticEntity currentEntity = null;
	//
	// while (line != null && !line.equals("COLLISION")) {
	// data = line.split(";");
	//
	// currentEntity = new StaticEntity(Double.parseDouble(data[0]),
	// Double.parseDouble(data[1]),
	// Integer.parseInt(data[2]), Integer.parseInt(data[3]),
	// Double.parseDouble(data[4]), data[5]);
	// entities.add(currentEntity);
	//
	// line = reader.readLine();
	// }
	// } catch (IOException | NumberFormatException e) {
	// throw new UnvalidLevelFormatException();
	// }
	// }
	//
	// public boolean walkable(double x, double y, double width, double height)
	// {
	// for (Rectangle rectangle : collisionBoxes)
	// if (rectangle.intersects(x - COLLISION_TOLERANCE, y -
	// COLLISION_TOLERANCE, width + COLLISION_TOLERANCE,
	// height + COLLISION_TOLERANCE))
	// return false;
	// return true;
	// }

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
	 * @return the entities
	 */
	public List<StaticEntity> getEntities() {
		return entities;
	}

	/**
	 * @return the collisionBoxes
	 */
	public List<Polygon> getCollisionBoxes() {
		return collisionBoxes;
	}

	public boolean walkable(double x, double y, double width, double height) {
		for (Polygon polygon : collisionBoxes) {
			System.out.println(polygon);
			if (polygon.intersects(x, y, width, height))
				return false;
		}
		return true;
	}
}