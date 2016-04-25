package menue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class menueAnimation extends JFrame{

	JLabel text;
	JButton button;
	JButton lvlEditor;
	JLabel background;
	JLabel  pic;
    Icon img1;
    Icon img2;
    
   
	public menueAnimation(){
		
		img1 = new ImageIcon(getClass().getResource("Bombe.png"));
		img2 = new ImageIcon(getClass().getResource("Bomberman.png"));
		setVisible(true);
		setSize(9000,700);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Unser Programm");
		setResizable(true);
		setLayout(new BorderLayout());
		//setLayout(null);
		
		
		
		
		button = new JButton ("Klick mich");
		button.setBounds(560,80,200,25);
		button.addActionListener(new Listener());
		button.addMouseListener(new Listener());
		button.setLayout(new GridLayout());
		add(button);
		
		lvlEditor = new JButton ("lvlEditor");
		lvlEditor.setBounds(560,160,200,25);
		lvlEditor.addActionListener(new Listener());
		lvlEditor.addMouseListener(new Listener());
		add(lvlEditor);
		
		pic = new JLabel(img1);
		pic.setBounds(640, 0, 60, 60);
		add(pic);
		
		background = new JLabel(img2);
		background.setBounds(0, 0, 500, 300);
		add(background);
		
		
		
		
		addWindowListener(new WindowHandler());
		
		
	}
	
	private class Listener implements java.awt.event.ActionListener , MouseListener{

		
		
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

		
		
	}
	
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
