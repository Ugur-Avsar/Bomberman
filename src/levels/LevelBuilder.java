package levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import inputManagement.Keyboard;
import toolbox.TimeManager;

public class LevelBuilder extends JPanel {
	public static final String TITLE = "LevelBuilder v1.0";
	public static final int BUTTON_HEIGHT = 100;
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	private static final File LEVEL_BACKGROUND_FOLDER = new File("./res/levels/");
	private static final File LEVEL_EXPORT_FOLDER = new File("./levels/");

	private static final double FULLHD_SCLAING_FACTOR = 3000.0 / (WIDTH + HEIGHT);

	private static int playerCount;
	private static Texture background;

	private static JPanel levelField;
	private static Graphics2D g;

	private JButton editBackground;
	private JButton addCollisionBox;
	private JButton setPlayerSpawnPoint;
	private JButton exportLevel;

	private static List<Polygon> collisionBoxes;

	public LevelBuilder() {
		super(null);
		init();
		levelField.setDoubleBuffered(true);
		collisionBoxes = new ArrayList<Polygon>();
		setPlayerCount();
	}

	private void init() {
		JPanel buttons = new JPanel(new GridLayout(2, 2));
		editBackground = new JButton("Edit Background");
		addCollisionBox = new JButton("Add Collision-Box");
		setPlayerSpawnPoint = new JButton("Add Spawn-Point");
		exportLevel = new JButton("Export Level");

		Font f = new Font("Arial", Font.BOLD, 20);
		editBackground.setFont(f);
		addCollisionBox.setFont(f);
		setPlayerSpawnPoint.setFont(f);
		exportLevel.setFont(f);

		buttons.add(editBackground);
		buttons.add(addCollisionBox);
		buttons.add(setPlayerSpawnPoint);
		buttons.add(exportLevel);

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
					levelField.addMouseListener(
							new CoordinateSaver(Integer.parseInt(JOptionPane.showInputDialog("Polygon-Point-Count:"))));
				} catch (NumberFormatException | HeadlessException e1) {
					System.err.println(TimeManager.getCurrentTime() + "... Invalid Polygon-Point count entered!!");
				}
			}
		});

		exportLevel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exportLevel();
			}
		});

		levelField = new JPanel(null);
		levelField.setBackground(Color.BLACK);
		levelField.setBounds(0, BUTTON_HEIGHT, WIDTH, HEIGHT);
		levelField.setFocusable(true);

		add(levelField);
		add(buttons);
	}

	public static void removeCoordSaver(CoordinateSaver c) {
		levelField.removeMouseListener(c);
	}

	public static void repaintLevel(List<Integer> xCoords, List<Integer> yCoords) {
		g = (Graphics2D) levelField.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (background != null) {
			background.render(g, 0, 0, WIDTH, HEIGHT);
		}

		for (Polygon polygon : collisionBoxes) {
			g.fillPolygon(polygon);
		}

		if (xCoords != null && yCoords != null)
			for (int i = 0; i < xCoords.size(); i++)
				g.fillOval(xCoords.get(i) - 3, yCoords.get(i) - 3, 6, 6);
	}

	/**
	 * @return the background
	 */
	public void setBG(Texture bg) {
		this.background = bg;
		System.out.println(
				TimeManager.getCurrentTime() + "... Level background changed to: " + bg.getFilename() + ".png");
		repaintLevel(null, null);
	}

	public static void addCollisionBox(Polygon box) {
		collisionBoxes.add(box);
	}

	public static void exportLevel() {
		String exportName = JOptionPane.showInputDialog(levelField.getParent(), "Enter Level-Name:");
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(LEVEL_EXPORT_FOLDER.getPath() + "/" + exportName + ".txt"));
			writer.write(exportName);
			writer.newLine();
			writer.write(playerCount + "");
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
			writer.close();
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}

		System.out.println(TimeManager.getCurrentTime() + "... Level exported to: ./levels/" + exportName + ".txt");
	}

	public static void setPlayerCount() {
		playerCount = Integer.parseInt(JOptionPane.showInputDialog(levelField.getParent(), "Enter Player-Count:"));
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
		f.add(new LevelBuilder());

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

		new Keyboard();

		f.setVisible(true);
	}
}