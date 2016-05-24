package menue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import levels.LevelBuilder;
import main.Game;

public class Listener implements MouseListener ,ActionListener{

	private menueAnimation m;
	private Game g;
	private LevelBuilder lvl;
	
	public Listener(menueAnimation m)
	{
		this.lvl=new LevelBuilder();
		this.m=m;
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
	Object source = e.getSource();
		
		if(source==m.getButton())
		{
			
			m.firstLabel.setIcon(m.img1);
			m.button.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		if(source ==m.getlvlEditor())
		{
			m.pic.setIcon(m.img1);
			m.lvlEditor.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		if(source == m.getoption())
		{
			m.secoundLabel.setIcon(m.img1);
		}
		
		if(source == m.getExit())
		{
			m.mainLabel.setIcon(m.img1);
		}
		
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	Object source = e.getSource();
		
		if(source==m.getButton())
		{
			
			m.firstLabel.setIcon(null);
			m.button.setBorder(BorderFactory.createLineBorder(Color.gray));
		}
		
		if(source ==m.getlvlEditor())
		{
			m.pic.setIcon(null);
			m.lvlEditor.setBorder(BorderFactory.createLineBorder(Color.gray));
		}
		
		if(source == m.getoption())
		{
			m.secoundLabel.setIcon(null);
		}
		if(source == m.getExit())
		{
			m.mainLabel.setIcon(null);
		}
	}
	
   //--------------------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source == m.getButton())
		{
			g.createNewGame();
		}
		
		if(source == m.getlvlEditor())
		{
			lvl.createNewLevelBuilderFrame();
		}
		
		if(source == m.getExit())
		{
			System.exit(0);
			System.out.println("Programm wurde beendet");
		}
		
	}
	
	

}
