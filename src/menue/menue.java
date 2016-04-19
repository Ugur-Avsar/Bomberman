package menue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.Caret;

import com.sun.prism.Image;

public class menue extends JFrame implements ActionListener , MouseListener{
	private boolean MouseON = false;
	private JPanel  panel;
	private JButton startGame;
	private JButton LVLBuilder;
	private JButton credits;
	private JButton exit;
	private java.awt.Image Image;
	private java.awt.Image bomb;
	private java.awt.Image background;
	private int PicPositionX;
	private int PicPositionY;
	
	public static void main(String[] args)  throws Exception{
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menue frame = new menue("Welcome"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(500, 600);
		frame.setVisible(true);
		
		
	}
	
	
	public menue(String string)
	{
		 
		super();
		gui();
		PicPositionX=225;
		PicPositionY=50;
		
		
		ImageIcon I = new ImageIcon("C:/Users/Kevin/Pictures/Bomberman.png");
		Image = I.getImage();
		
		ImageIcon b = new ImageIcon("C:/Users/Kevin/Pictures/Bombe.png");
		bomb = b.getImage();
		
		ImageIcon bc = new ImageIcon("C:/Users/Kevin/Pictures/WhiteBoardpng.png");
		background = bc.getImage();
		
		
	}

	public void changeToTrue()
	{
		MouseON=true;
	}
	public void changeToFalse()
	{
		MouseON=false;
	}
	public void setMouseON( boolean MouseON)
	{
		this.MouseON = MouseON;
	}
	
	public boolean grtMouseON()
	{
		return MouseON;
	}
	public void gui() {
		// TODO Auto-generated method stub
		LVLBuilder= new JButton("LVlBuilder");
		LVLBuilder.setBounds(250,300,100,100);
		LVLBuilder.addActionListener(this);
		LVLBuilder.addMouseListener(this);
		
		add(LVLBuilder);
		
		startGame = new JButton("Start");
		startGame.setBounds(180, 100, 100, 100);
		startGame.addActionListener(this);
		startGame.addMouseListener(this);
		add(startGame);
		
		repaint();
		
		
		
		
		
		
		
				
	}
	
	public void paint(Graphics g) {

	
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background,0,0,500,600,null);
		
		g2d.drawImage(Image, 0, 0,500,600 ,null);
		
		if( MouseON == true)
		{
		g2d.drawImage(bomb, 225 , 50 ,50 , 50, null);
		System.out.println("Bild");
		}
		
		
		}
	
	
		
		
		
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		

	}

//------------------------------------------------
// Mouse 
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
		startGame.setBackground(Color.black);
		changeToTrue();
		repaint();
		System.out.println(MouseON);
		
	
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		startGame.setBackground(Color.white);
		changeToFalse();
		repaint();
		
		System.out.println(MouseON);
		
	}
	//-------------------------------------------

}
