package settingsMenue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.StyledDocument;

import toolbox.IOTool;
import toolbox.OnlyNumericInputListener;

public class VideoSettings extends JPanel {

	private JComboBox<String> windowMode;
	private JTextField windowWidth, windowHeight;

	private JToggleButton antialiasing;

	private JToggleButton aninmations;

	private JToggleButton fpsLimit;
	private JSpinner fpsLimitSpinner;

	public VideoSettings() {
		super(new BorderLayout(5, 5));

		Font textfieldFont = new Font("Arial", 0, 14);

		setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel title = new JLabel("Video Settings");
		title.setBorder(new EmptyBorder(0, 0, 10, 0));
		title.setFont(new Font("Arial", Font.BOLD, 25));
		add(title, BorderLayout.NORTH);

		initComponents();

		// CENTER
		JPanel centerPanel = new JPanel(new GridLayout(4, 3, 10, 10));
		centerPanel.add(new JLabel("Window-Mode:"));
		centerPanel.add(windowMode);

		JLabel resolution = new JLabel("(16:9)");
		JPanel windowSizeInput = new JPanel(new GridLayout(1, 3, 10, 10));
		windowWidth.setFont(textfieldFont);
		windowHeight.setFont(textfieldFont);
		windowWidth.setEditable(false);
		windowHeight.setEditable(false);
		windowSizeInput.add(windowWidth);
		windowSizeInput.add(windowHeight);
		windowSizeInput.add(resolution);

		centerPanel.add(windowSizeInput);

		centerPanel.add(new JLabel("Antialiasing:"));
		centerPanel.add(antialiasing);
		centerPanel.add(new JPanel());

		centerPanel.add(new JLabel("Animations:"));
		centerPanel.add(aninmations);
		centerPanel.add(new JPanel());

		centerPanel.add(new JLabel("FPS-Limit:"));
		centerPanel.add(fpsLimit);
		centerPanel.add(fpsLimitSpinner);

		add(centerPanel, BorderLayout.CENTER);

	}

	private void initComponents() {
		windowMode = new JComboBox<String>();
		windowMode.setEditable(false);
		windowMode.addItem("Fullscreen-Mode");
		windowMode.addItem("Window-Mode");
		windowWidth = new JTextField((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		windowWidth.setHorizontalAlignment(SwingConstants.CENTER);
		((AbstractDocument) windowWidth.getDocument())
				.setDocumentFilter(new OnlyNumericInputListener(windowWidth, 128, 7680, 4));
		windowHeight = new JTextField((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		windowHeight.setHorizontalAlignment(SwingConstants.CENTER);
		((AbstractDocument) windowHeight.getDocument())
				.setDocumentFilter(new OnlyNumericInputListener(windowHeight, 72, 4320, 4));

		antialiasing = new JToggleButton("On/Off", true);
		aninmations = new JToggleButton("On/Off", true);
		fpsLimit = new JToggleButton("On/Off", true);

		fpsLimitSpinner = new JSpinner();
		fpsLimitSpinner.setModel(new SpinnerNumberModel(60, 1, 144, 1));
		((DefaultEditor) fpsLimitSpinner.getEditor()).getTextField().setEditable(false);
	}
}
