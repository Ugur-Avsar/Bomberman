package settings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import graphics.Texture;
import graphics.TextureDisplay;
import toolbox.IOTool;

public class GeneralDescription extends JPanel {
	public GeneralDescription() {
		super(new BorderLayout(5, 5));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel title = new JLabel("Bomberman HD - Ugur A. Kevin K.");
		title.setFont(new Font("Arial", Font.BOLD, 25));
		add(title, BorderLayout.NORTH);

		JTextArea area = new JTextArea(IOTool.getString("./lib/res/projectDescription.txt", false));
		JScrollPane scrollPaneText = new JScrollPane(area);
		area.setFont(new Font("Arial", 0, 16));
		area.setEditable(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(scrollPaneText, BorderLayout.CENTER);
		scrollPaneText.setPreferredSize(new Dimension(350, 0));

		TextureDisplay img = new TextureDisplay(new Texture("titleIMG"), 0, 0);
		img.setPreferredSize(new Dimension(200, 0));
		add(img, BorderLayout.EAST);
	}
}
