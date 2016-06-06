package winnerScreen;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * JTextFieldPanel
 * 
 *
 */
class SimplePanel extends JPanel {

	// buttons
	private JButton reset;
	private JButton close;
	
	
	// textfield
	private JTextField winner_TF;
	

	// reference to the listener
	private SimpleListener simpleListener;
	
	// reference to the frame
	private SimpleFrame simpleFrame;
	
	private JLabel winnerLabel;
	
	
	/**
	 * add actionListeners
	 */
	private void addActionListeners(){
		reset.addActionListener(simpleListener);
		close.addActionListener(simpleListener);
	}

	/**
	 * constructor
	 */
	public SimplePanel(SimpleFrame simpleFrame) {
		
		// reference to the frame
		this.simpleFrame=simpleFrame;

		// create listener object + reference to the panel as parameter
		simpleListener = new SimpleListener(this);

		//****************************************************************
		Font font = new Font("Arial",Font.BOLD+Font.ITALIC,30);
		// create JButton + text 
		// reset button
		reset = new JButton("Login");
		// set the font 
		reset.setFont(new Font("Arial",Font.BOLD,25));
		// close button
		
		
		close = new JButton("Close");
		
		winner_TF = new JTextField();
		winner_TF.setEditable(false);
		
		
		winnerLabel = new JLabel("Gewonnen hat:");
		winnerLabel.setFont(font);

		// anonymous textfield panel
		JPanel mainPanel_PNL;
		JPanel textfieldPanel_PNL;
		JPanel LsideFieldPanel_PNL;
		JPanel RsideFieldPanel_PNL;
		JPanel ButtonsFieldPanel_PNl;
		// panel in the center
		mainPanel_PNL = new JPanel();
		textfieldPanel_PNL = new JPanel();
		LsideFieldPanel_PNL = new JPanel();
		RsideFieldPanel_PNL = new JPanel();
		ButtonsFieldPanel_PNl = new JPanel();
		
		// GridLayout 2 rows and 2 columns
		mainPanel_PNL.setLayout(new GridLayout(1,3));
		mainPanel_PNL.setBackground(Color.red);
		textfieldPanel_PNL.setLayout(new GridLayout(3,1));
		textfieldPanel_PNL.setBackground(Color.blue);
		LsideFieldPanel_PNL.setLayout(new GridLayout());
		LsideFieldPanel_PNL.setBackground(Color.orange);
		RsideFieldPanel_PNL.setLayout(new GridLayout());
		RsideFieldPanel_PNL.setBackground(Color.black);
		ButtonsFieldPanel_PNl.setLayout(new GridLayout());
		
		
		
		// create textfields
		/*name_TF=new JTextField();
		name_TF.setPreferredSize((new Dimension(300,100)));
		name_TF.setHorizontalAlignment(JLabel.CENTER);
		yearOfBirth_TF=new JTextField();
		//copy paste disable
		yearOfBirth_TF.setTransferHandler(null);
		yearOfBirth_TF.setHorizontalAlignment(JLabel.CENTER);
		yearOfBirth_TF.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				if (yearOfBirth_TF.getText().length()>3) arg0.consume();
				// TODO Auto-generated method stub
				if (!(arg0.getKeyChar()>='0' && arg0.getKeyChar()<='9')){
					arg0.consume();
				}				
			}			
		});*/
		
		//-------------------------------
		mainPanel_PNL.add(LsideFieldPanel_PNL);
		mainPanel_PNL.add(textfieldPanel_PNL);
		mainPanel_PNL.add(RsideFieldPanel_PNL);
		
		
		
		textfieldPanel_PNL.add(winnerLabel);
		textfieldPanel_PNL.add(winner_TF);
		
		ButtonsFieldPanel_PNl.add(reset);
		ButtonsFieldPanel_PNl.add(close);
		
		
		
		
		//---------------------------------
		
		// add components
		// set background color
		// set layout manager of the panel
		this.setLayout(new BorderLayout());

		// to the north
		//this.add(login, BorderLayout.NORTH);
		// to the center
		this.add(mainPanel_PNL);
		this.add(ButtonsFieldPanel_PNl,BorderLayout.SOUTH);
		//this.add(textfieldPanel_PNL, BorderLayout.CENTER);
		
		// to the south
		//this.add(registrieren, BorderLayout.SOUTH);
		
		// add action listeners
		addActionListeners();
	}
	
	

	/**
	 * @return the reset_BTN
	 */
	public JButton getReset() {
		return reset;
	}

	/**
	 * @param reset_BTN the reset_BTN to set
	 */
	public void setreset(JButton reset_BTN) {
		this.reset = reset_BTN;
	}

	/**
	 * @return the close_BTN
	 */
	public JButton getClose_BTN() {
		return close;
	}

	/**
	 * @param close_BTN the close_BTN to set
	 */
	public void setclose(JButton close_BTN) {
		this.close = close_BTN;
	}

	/**
	 * @return the name_TF
	 */
	public JTextField getwinner_TF() {
		return winner_TF;
	}

	/**
	 * @param name_TF the name_TF to set
	 */
	public void setwinner_TF(JTextField winner_TF) {
		this.winner_TF = winner_TF;
	}

	/**
	 * @return the town_TF
	 */
	

	/**
	 * @return the simpleFrame
	 */
	public SimpleFrame getSimpleFrame() {
		return simpleFrame;
	}


	
	
}
