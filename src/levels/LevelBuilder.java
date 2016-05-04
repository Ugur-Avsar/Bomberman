package levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import inputManagement.Keyboard;
import inputManagement.Mouse;
import toolbox.ArrayListConverter;
import toolbox.StringUtils;
import toolbox.TimeManager;

public class LevelBuilder extends JPanel {
	public static final String TITLE = "LevelBuilder v1.0";
	public static final int BUTTON_HEIGHT = 100;
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	private static final File LEVEL_BACKGROUND_FOLDER = new File("./res/levels/");
	private static final File LEVEL_EXPORT_FOLDER = new File("./levels/");

	private static final double FULLHD_SCLAING_FACTOR = 3000.0 / (WIDTH + HEIGHT);
	private static final double HD_SCLAING_FACTOR = (WIDTH + HEIGHT) / 3000.0;

	private static Texture background;

	private static JPanel levelField;
	private static Graphics2D g;

	private JButton editBackground;
	private JButton addCollisionBox;
	private JButton setPlayerSpawnPoint;
	private JButton exportLevel;
	private JButton openLevel;
	private JButton delete;

	private static List<Point> playerSpawns;
	private static List<Polygon> collisionBoxes;

	public LevelBuilder() {
		super(null);
		init();
		levelField.setDoubleBuffered(true);
		collisionBoxes = new ArrayList<Polygon>();
		playerSpawns = new ArrayList<Point>();
		background = new Texture("levels/black");
	}

	private void init() {
		JPanel buttons = new JPanel(new GridLayout(2, 3));
		editBackground = new JButton("Edit Background");
		addCollisionBox = new JButton("Add Collision-Box");
		setPlayerSpawnPoint = new JButton("Add Spawn-Point");
		exportLevel = new JButton("Export Level");
		openLevel = new JButton("Open Level");
		delete = new JButton("Delete Collision Box");

		Font f = new Font("Arial", Font.BOLD, 15);
		editBackground.setFont(f);
		addCollisionBox.setFont(f);
		setPlayerSpawnPoint.setFont(f);
		exportLevel.setFont(f);
		openLevel.setFont(f);
		delete.setFont(f);

		buttons.add(editBackground);
		buttons.add(addCollisionBox);
		buttons.add(setPlayerSpawnPoint);
		buttons.add(exportLevel);
		buttons.add(openLevel);
		buttons.add(delete);

		buttons.setBounds(0, 0, LevelBuilder.WIDTH, BUTTON_HEIGHT);

		editBackground.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File[] levelFiles = LEVEL_BACKGROUND_FOLDER.listFiles();
				String[] levels = new String[levelFiles.length];

				for (int i = 0; i < levelFiles.length; i++) {
					levels[i] = levelFiles[i].getName();
				}

				String input = (String) JOptionPane.showInputDialog(getParent(), "Select Level-Background-Image:",
						"Level-Background Selection", JOptionPane.PLAIN_MESSAGE, null, levels, levels[0]);

				if (input != null)
					setBG(new Texture("levels/" + input.substring(0, input.length() - 4)));
			}
		});

		addCollisionBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addMouseListenerToLevel(
							new BoxAdder(Integer.parseInt(JOptionPane.showInputDialog("Polygon-Point-Count:"))));
				} catch (NumberFormatException | HeadlessException e1) {
					System.err.println(TimeManager.getCurrentTime() + "... Invalid Polygon-Point count entered!");
				}
			}
		});

		exportLevel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exportLevel();
			}
		});

		openLevel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadLevel(JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(getParent()), "Level-Name:"));
				repaintLevel(null, null);
			}
		});

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addMouseListenerToLevel(new BoxRemover());
			}
		});

		setPlayerSpawnPoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addMouseListenerToLevel(new SpawnAdder());
			}
		});

		levelField = new JPanel(null);
		levelField.setBackground(Color.BLACK);
		levelField.setBounds(0, BUTTON_HEIGHT, WIDTH, HEIGHT);

		Mouse m = new Mouse();
		levelField.addKeyListener(new Keyboard());
		levelField.addMouseListener(m);
		levelField.addMouseMotionListener(m);
		levelField.addMouseWheelListener(m);

		levelField.setFocusable(true);
		levelField.requestFocus();

		add(levelField);
		add(buttons);
	}

	public static void removeMouseListenerFromLevel(MouseListener c) {
		levelField.removeMouseListener(c);
	}

	public static void addMouseListenerToLevel(MouseListener c) {
		levelField.addMouseListener(c);
	}

	public static void addSpawn(int x, int y) {
		playerSpawns.add(new Point(x, y));
	}

	public static void removeBox(int x, int y) {
		for (Polygon polygon : collisionBoxes) {
			if (polygon.contains(x, y)) {
				collisionBoxes.remove(polygon);
				break;
			}
		}
	}

	// HIER// HIER// HIER// HIER// HIER// HIER// HIER// HIER
	public static void loadLevel(String levelName) {
		File level = new File("./levels/" + levelName + ".txt");
		if (level.isFile() && level.exists()) {
			playerSpawns.clear();
			collisionBoxes.clear();
			background = null;
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(level));
				reader.readLine();
				reader.readLine();
				background = new Texture(reader.readLine());

				String[] data = null;
				String[] xCoordsSArr = null;
				String[] yCoordsSArr = null;

				int[] xCoords = null;
				int[] yCoords = null;

				List<String> xCoordsSList = null;
				List<String> yCoordsSList = null;
				for (String line = reader.readLine(); line != null && !line.equals(""); line = reader.readLine()) {
					data = line.split(";");
					System.out.println(line);
					xCoordsSArr = data[0].split(",");
					yCoordsSArr = data[1].split(",");

					xCoordsSList = ArrayListConverter.toList(xCoordsSArr);
					yCoordsSList = ArrayListConverter.toList(yCoordsSArr);
					xCoordsSList.remove(0);
					yCoordsSList.remove(0);

					xCoords = ArrayListConverter.stringArrayToIntArray(ArrayListConverter.toArray(xCoordsSList));
					yCoords = ArrayListConverter.stringArrayToIntArray(ArrayListConverter.toArray(yCoordsSList));

					xCoords = ArrayListConverter.calculate(xCoords, '*', HD_SCLAING_FACTOR);
					yCoords = ArrayListConverter.calculate(yCoords, '*', HD_SCLAING_FACTOR);

					collisionBoxes.add(new Polygon(xCoords, yCoords, Math.min(xCoords.length, yCoords.length)));
				}

				int x = 0;
				int y = 0;
				for (String line = reader.readLine(); line != null && !line.equals(""); line = reader.readLine()) {
					playerSpawns.add(new Point((int) (Integer.parseInt(line.split("/")[0]) * HD_SCLAING_FACTOR),
							(int) (Integer.parseInt(line.split("/")[1]) * HD_SCLAING_FACTOR)));
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static void repaintLevel(List<Integer> xCoords, List<Integer> yCoords) {
		g = (Graphics2D) levelField.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (background != null) {
			background.render(g, 0, 0, WIDTH, HEIGHT);
		}

		g.setColor(Color.RED);
		for (Polygon polygon : collisionBoxes) {
			g.fillPolygon(polygon);
		}
		g.setColor(Color.YELLOW);
		for (Point point : playerSpawns) {
			g.fillOval((int) (point.getX() - 2), (int) (point.getY() - 2), 4, 4);
		}

		if (xCoords != null && yCoords != null)
			for (int i = 0; i < xCoords.size(); i++)
				g.fillOval(xCoords.get(i) - 2, yCoords.get(i) - 2, 4, 4);
	}

	/**
	 * @return the background
	 */
	public void setBG(Texture bg) {
		background = bg;
		System.out.println(
				TimeManager.getCurrentTime() + "... Level background changed to: " + bg.getFilename() + ".png");
		repaintLevel(null, null);
	}

	public static void addCollisionBox(Polygon box) {
		collisionBoxes.add(box);
	}

	public static void exportLevel() {
		String exportName = JOptionPane.showInputDialog(levelField.getParent(), "Enter Level-Name:");

		if (exportName == null || StringUtils.containsSpecialChars(exportName)) {
			System.err.println(TimeManager.getCurrentTime() + "... Invalid Level-Name!");
			return;
		}

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(LEVEL_EXPORT_FOLDER.getPath() + "/" + exportName + ".txt"));
			writer.write(exportName);
			writer.newLine();
			writer.write(playerSpawns.size() + "");
			writer.newLine();
			writer.write(background.getFilename());
			writer.newLine();

			int[] xCoords = null;
			int[] yCoords = null;

			for (Polygon polygon : collisionBoxes) {
				xCoords = polygon.xpoints;
				yCoords = polygon.ypoints;

				for (int i : xCoords) {
					writer.write("," + (int) (i * FULLHD_SCLAING_FACTOR));
				}
				writer.write(';');
				for (int i : yCoords) {
					writer.write("," + (int) (i * FULLHD_SCLAING_FACTOR));
				}
				writer.newLine();
			}
			writer.newLine();
			for (Point point : playerSpawns) {
				int x = (int) point.getX();
				int y = (int) point.getY();

				writer.write("" + ((int) (x * FULLHD_SCLAING_FACTOR)));
				writer.write('/');
				writer.write("" + ((int) (y * FULLHD_SCLAING_FACTOR)));
				writer.newLine();
			}
			writer.close();
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}

		System.out.println(TimeManager.getCurrentTime() + "... Level exported to: ./levels/" + exportName + ".txt");
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		JFrame f = new JFrame();

		f.setTitle(LevelBuilder.TITLE);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(LevelBuilder.WIDTH + 6, LevelBuilder.BUTTON_HEIGHT + LevelBuilder.HEIGHT + 35);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		LevelBuilder l = new LevelBuilder();
		f.add(l);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				System.err.println("---------------------------------------------");
				System.err.println("Levelbuilder v1.0 \nBy:	Ugur Avsar\n  	Kevin Kulcsar");
				System.err.println("---------------------------------------------");
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.err.println("---------------------------------------------");
				System.err.println("Exits...");
				System.err.println("---------------------------------------------");
			}
		});

		f.setVisible(true);
	}
}