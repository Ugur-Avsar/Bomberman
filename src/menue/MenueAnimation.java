package menue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import graphics.FigureAnimation;
import graphics.FigureAnimation.Direction;
import graphics.MovingSpriteConfiguration;
import graphics.Spritesheet;
import graphics.Texture;
import graphics.TextureDisplay;
import sound.DefaultSounds;
import sun.java2d.pipe.SolidTextRenderer;
import toolbox.Ticker;

public class MenueAnimation extends JFrame {

	private BombenButton start;
	private BombenButton lvlEditor;
	private BombenButton options;
	private BombenButton exit;

	private Listener l;

	public MenueAnimation() {
		super("Bomberman HD - Main Menu");
		DefaultSounds.MAIN_MUSIC.play();
		addElements();
		addActionListeners();
		setActionCommands();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int nummber = JOptionPane.showConfirmDialog(MenueAnimation.this, "Wollen sie beenden..?", "Beenden?",
						JOptionPane.YES_NO_OPTION);
				if (nummber == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		setProperties();
	}

	private void addElements() {
		l = new Listener(this);
		start = new BombenButton("Start");
		lvlEditor = new BombenButton("lvlEditor");
		options = new BombenButton("Options");
		exit = new BombenButton("Exit");

		JPanel main = new JPanel(new BorderLayout());

		MovingSpriteConfiguration config = new MovingSpriteConfiguration(4, 4, 15, 4, 8, 12, 0, 4);

		Spritesheet figureLeft = new Spritesheet("lama", config.getRowCount(), config.getColCount(),
				config.getLeftIndex());
		FigureAnimation runningLeft = new FigureAnimation(Direction.RIGHT, figureLeft, config, 0, 0);

		Spritesheet figureRight = new Spritesheet("lama", config.getRowCount(), config.getColCount(),
				config.getRightIndex());
		FigureAnimation runningRight = new FigureAnimation(Direction.LEFT, figureRight, config, 0, 0);

		new Timer(15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Ticker.tick();
				runningLeft.update();
				runningRight.update();
				runningLeft.repaint();
				runningRight.repaint();
			}
		}).start();

		JPanel middle = new JPanel(new BorderLayout());

		TextureDisplay display = new TextureDisplay(new Texture("BombermanHDLogo"), 0, 0);
		display.setPreferredSize(new Dimension(400, 200));
		runningLeft.setPreferredSize(new Dimension(150, 0));
		runningRight.setPreferredSize(new Dimension(150, 0));

		JPanel buttons = new JPanel(new GridLayout(4, 1));
		buttons.add(start);
		buttons.add(lvlEditor);
		buttons.add(options);
		buttons.add(exit);
		buttons.setPreferredSize(new Dimension(400, 200));

		middle.add(display, BorderLayout.CENTER);
		middle.add(buttons, BorderLayout.SOUTH);

		main.add(runningLeft, BorderLayout.WEST);
		main.add(middle, BorderLayout.CENTER);
		main.add(runningRight, BorderLayout.EAST);
		main.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(main);
	}

	private void setProperties() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}

	private void addActionListeners() {
		start.addMouseListener(l);
		start.addActionListener(l);
		lvlEditor.addMouseListener(l);
		lvlEditor.addActionListener(l);
		options.addMouseListener(l);
		options.addActionListener(l);
		exit.addMouseListener(l);
		exit.addActionListener(l);
	}

	private void setActionCommands() {
		start.setActionCommand("start");
		lvlEditor.setActionCommand("levelEditor");
		options.setActionCommand("options");
		exit.setActionCommand("exit");
	}

	public static void main(String args[]) {
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
		new MenueAnimation();
	}
}