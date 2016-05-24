package menue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import levels.LevelBuilder;


public class menueAnimation extends JFrame{

	JLabel text;
	JButton button;
	JButton lvlEditor;
	JButton options;
	JButton exit;
	JLabel firstLabel;
	JLabel  pic;
	JLabel secoundLabel;
	JLabel mainLabel;
	JPanel mainPanel;
	JPanel topPanel;
	JPanel bottomPanel;
	JPanel sitePanel;
	JLabel background;
	JLabel labelLeft;
	JLabel labelRight;
    Icon img1;
    Icon img2;
    
    private Listener l;
    
   
	public menueAnimation(Listener listener){
		l=listener;
		
		listener = new Listener(this);
		img1 = new ImageIcon(getClass().getResource("Bombe.png"));
		img2 = new ImageIcon(getClass().getResource("Bomberman_Logo.png"));
		setVisible(true);
		setSize(900,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Unser Programm");
		setResizable(true);
		
		setLayout(new BorderLayout());
		
		
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3,1));
		mainPanel.setBackground(Color.red);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		
		
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(4,3));
		
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(4,0));
		
		
		button = new JButton ("Start");
		button.addMouseListener(listener);
		button.addActionListener(listener);
		
		
		
		
		lvlEditor = new JButton ("lvlEditor");
		lvlEditor.addMouseListener(listener);
		lvlEditor.addActionListener(listener);
		
		options = new JButton("Options");
		options.addMouseListener(listener);
		options.addActionListener(listener);
		
		exit = new JButton("Exit");
		exit.addMouseListener(listener);
		exit.addActionListener(listener);
		
		firstLabel = new JLabel();
		pic = new JLabel();
		secoundLabel = new JLabel();
		mainLabel = new JLabel();	
		background = new JLabel(img2);
		labelLeft = new JLabel();
		labelRight = new JLabel();
	
		
		
		
		add(mainPanel);
		mainPanel.add(background);
		mainPanel.add(topPanel,BorderLayout.NORTH);
		mainPanel.add(bottomPanel, BorderLayout.CENTER);
		topPanel.add(firstLabel);
		topPanel.add(button);
		topPanel.add(pic);
		topPanel.add(lvlEditor);
		bottomPanel.add(secoundLabel);
		bottomPanel.add(options);
		bottomPanel.add(mainLabel);
		bottomPanel.add(exit);
		
		
		
		addWindowListener(new WindowHandler());
		
		
		repaint();
		validate();
		
	}
	
	private void ActionListener()
	{
		button.addMouseListener(l);
		lvlEditor.addMouseListener(l);
		
	}
	
	public void setJIcon(Icon i)
	{
		this.img1=i;
	}
	
	public Icon getJIcon()
	{
		return img1;
	}
	public void setJLpic(JLabel i)
	{
		this.pic= i;
	}
	
	public JLabel getJLpic()
	{
		return pic;
	}
	
	public void setButton(JButton b)
	{
		this.button=b;
	}
	
	
	public JButton getButton()
	{
		return button;
	}
	
	//-----------------------------
	public void setlvlEditor(JButton b)
	{
		this.lvlEditor=b;
	}
	
	
	public JButton getlvlEditor()
	{
		return lvlEditor;
	}
	//---------------------------------
	public void setoption(JButton b)
	{
		this.options=b;
	}
	
	
	public JButton getoption()
	{
		return options;
	}
	
	public JButton getExit()
	{
		return exit;
	}
	//--------------------------------
	
	
	

	
	/*private class Listener implements ActionListener , MouseListener{

		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
			
			
		}
		
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
			pic.setIcon(img1);
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		     pic.setIcon(null);
		    
		}

		
		
	}*/
	
	private class WindowHandler implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
		int nummber  =	JOptionPane.showConfirmDialog(menueAnimation.this,"Wollen sie beenden..?","Beenden?",JOptionPane.YES_NO_OPTION);
			if(nummber== JOptionPane.YES_OPTION)
			{
				System.exit(0);
			} else {
				
			}
			
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
