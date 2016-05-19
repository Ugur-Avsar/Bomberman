package settings;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ControlsTable extends JPanel {
	private JTable table;
	private static final String[] columnHeads = new String[] { "Key", "Action" };
	private static final String[][] data = new String[][] {
		{ "W", "Player 1 - UP" },
		{ "A", "Player 1 - LEFT" },
		{ "S", "Player 1 - DOWN" },
		{ "D", "Player 1 - RIGHT" },

		{ "UP-ARROW", "Player 2 - UP" },
		{ "LEFT-ARROW", "Player 2 - LEFT" },
		{ "DOWN-ARROW", "Player 2 - DOWN" },
		{ "RIGHT-ARROW", "Player 2 - RIGHT" },

		{ "Z", "Player 3 - UP" },
		{ "G", "Player 3 - LEFT" },
		{ "J", "Player 3 - DOWN" },
		{ "K", "Player 3 - RIGHT" },

		{ "NUMPAD 8", "Player 4 - UP" },
		{ "NUMPAD 4", "Player 4 - LEFT" },
		{ "NUMPAD 5", "Player 4 - DOWN" },
		{ "NUMPAD 6", "Player 4 - RIGHT" }
	}; // Platzhalter! Steuerung wird später aus einer Textdatei geladen!

	public ControlsTable() {
		super(new CardLayout());
		Font font = new Font("Arial", 0, 14);
		EmptyBorder padding = new EmptyBorder(10, 10, 10, 10);
		setBorder(padding);
		table = new JTable(data, columnHeads);
		table.setBorder(getBorder());
		table.setFont(font);
		table.setRowHeight(30);
		table.setModel(new DefaultTableModel(data, columnHeads) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0;
			}
		});
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(false);
		JScrollPane scrollpane = new JScrollPane(table);
		add(scrollpane);
	}
}
