package winnerScreen;
import java.beans.Encoder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;




/**
 * StartFrameTest
 *
 */
public class SimpleFrameTest {
    public static void main(String[] args){

    	SwingUtilities.invokeLater(new Runnable() {
    	    public void run() {
    	    	 SimpleFrame frame;
    	    	 
    	    	 
    	    	
    	    	
    	    	
  			          
    				
				try {
					frame = new SimpleFrame();
	    	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 
    	    }
    	});

           
   }
}
