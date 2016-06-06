package winnerScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import menue.MenueAnimation;

public class winningScreenGui extends JFrame{
	
	private JPanel firstPanel;
	private JPanel mainPanel;
	
	private JTextField textField;
	
	
	
	public winningScreenGui()
	{
		super("Winning Screen");
		addElements();
		setProperties();
	}
	
	private void addElements()
	{
	    firstPanel =  new JPanel();
		firstPanel.setBackground(Color.red);
		firstPanel.setLayout(new GridLayout(4,1));
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		textField = new JTextField();
	    
		
		
		add(mainPanel,BorderLayout.CENTER);
		mainPanel.add(firstPanel,BorderLayout.CENTER);
		firstPanel.add(textField);
		
	}
	private void setProperties() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
		setSize(300,350);
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
		
		new winningScreenGui();
	}

	}


