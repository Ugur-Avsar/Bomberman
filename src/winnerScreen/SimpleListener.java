package winnerScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.*;

/**
 * 
 * JTextFieldListener
 *
 */
public class SimpleListener implements ActionListener {

	// Reference to the graphical components
    private SimplePanel panel;
  
    
    
    /**
     * 
     * @param p
     */
    public SimpleListener(SimplePanel p){
        panel=p;
        
            }
    
    
	/**
     * Button pressed, ....
     */
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    	
    	// gets the source of the component
        Object source = e.getSource();
        
        if (source==panel.getReset()){
          
        }
        if (source==panel.getClose_BTN()){
            
         }
        
     }
}
