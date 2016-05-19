package settings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import toolbox.IOTool;

public class GeneralDescription extends JPanel {
	public GeneralDescription() {
		super(new BorderLayout(5, 5));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel title = new JLabel("Bomberman HD - Ugur A. Kevin K.");
		title.setFont(new Font("Arial", Font.BOLD, 25));
		add(title, BorderLayout.NORTH);

		JTextArea area = new JTextArea(IOTool.getString("./res/projectDescription.txt", false));
		JScrollPane scrollPaneText = new JScrollPane(area);
		area.setFont(new Font("Arial", 0, 16));
		area.setEditable(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(scrollPaneText, BorderLayout.CENTER);
		scrollPaneText.setPreferredSize(new Dimension(350, 0));

		TitleIMGContainer img = new TitleIMGContainer();
		img.setPreferredSize(new Dimension(250, 0));
		add(img, BorderLayout.EAST);
	}
}
