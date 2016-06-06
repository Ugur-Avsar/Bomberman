package settings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class SettingsDialog extends JPanel {

	private JFrame parentFrame;
	private JTabbedPane tabbedPane;
	private JButton cancel_BTN, apply_BTN, save_BTN;

	private VideoSettings videoSettings;
	private ControlsTable controlsTable;

	public SettingsDialog() {
		super(new BorderLayout(5, 5));
		videoSettings = new VideoSettings();
		controlsTable = new ControlsTable();
		initComponents();
		addComponents();

		parentFrame = new JFrame("Settings");
		parentFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(("./res/settingsIcon.png")));
		parentFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		parentFrame.setSize(600, 450);
		parentFrame.setResizable(false);
		parentFrame.setLocationRelativeTo(null);
		parentFrame.add(this);
		parentFrame.setVisible(false);

	}

	private void addComponents() {
		JPanel southButtons = new JPanel(new GridLayout(1, 3, 15, 15));
		southButtons.setBorder(new EmptyBorder(0, 5, 10, 5));
		southButtons.add(cancel_BTN);
		southButtons.add(apply_BTN);
		southButtons.add(save_BTN);
		southButtons.setPreferredSize(new Dimension(0, 50));

		add(tabbedPane, BorderLayout.CENTER);
		add(southButtons, BorderLayout.SOUTH);
	}

	private void initComponents() {
		cancel_BTN = new JButton("Cancel");
		apply_BTN = new JButton("Apply");
		save_BTN = new JButton("Save");

		tabbedPane = new JTabbedPane();
		tabbedPane.setBorder(new EmptyBorder(5, 5, 0, 5));

		tabbedPane.addTab("General", new GeneralDescription());
		tabbedPane.addTab("Video", videoSettings);
		tabbedPane.addTab("Controls", controlsTable);
	}

	public JFrame getParentFrame() {
		return parentFrame;
	}

	public void setVisible(boolean show) {
		parentFrame.setVisible(show);
	}
}