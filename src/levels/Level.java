package levels;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.DynamicEntity;
import entities.StaticEntity;
import exceptions.UnvalidLevelFormatException;
import main.Game;
import resources.Texture;

public class Level {

	public static final double COLLISION_TOLERANCE = 0;

	private Texture texture;

	private String name;
	private int maxPlayers;

	private List<Rectangle> collisionBoxes;
	private List<StaticEntity> entities;
	private List<DynamicEntity> players;

	public Level(File levelFile) {
		players = new ArrayList<DynamicEntity>();
		entities = new ArrayList<StaticEntity>();
		collisionBoxes = new ArrayList<Rectangle>();
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

		initEntities(reader);
		initCollisionBoxes(reader);
	}

	private void initCollisionBoxes(BufferedReader reader) throws UnvalidLevelFormatException {
		try {
			String line = reader.readLine();
			String[] data = null;
			String[] leftSide = null;
			String[] rightSide = null;

			while (line != null) {
				data = line.split(";");
				

				collisionBoxes.add(new Rectangle(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
						Integer.parseInt(data[2]), Integer.parseInt(data[3])));

				line = reader.readLine();
			}
		} catch (IOException | NumberFormatException e) {
			throw new UnvalidLevelFormatException();
		}
	}

	private void initEntities(BufferedReader reader) throws UnvalidLevelFormatException {
		try {
			String line = reader.readLine();
			String[] data = null;
			StaticEntity currentEntity = null;

			while (line != null && !line.equals("COLLISION")) {
				data = line.split(";");

		   		currentEntity = new StaticEntity(Double.parseDouble(data[0]), Double.parseDouble(data[1]),
						Integer.parseInt(data[2]), Integer.parseInt(data[3]), Double.parseDouble(data[4]), data[5], null);
				entities.add(currentEntity);

				line = reader.readLine();
			}
		} catch (IOException | NumberFormatException e) {
			throw new UnvalidLevelFormatException();
		}
	}

	public boolean walkable(double x, double y, double width, double height) {
		for (Rectangle rectangle : collisionBoxes)
			if (rectangle.intersects(x - COLLISION_TOLERANCE, y - COLLISION_TOLERANCE, width + COLLISION_TOLERANCE,
					height + COLLISION_TOLERANCE))
				return false;
		return true;
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
	 * @return the entities
	 */
	public List<StaticEntity> getEntities() {
		return entities;
	}

	/**
	 * @return the collisionBoxes
	 */
	public List<Rectangle> getCollisionBoxes() {
		return collisionBoxes;
	}
}