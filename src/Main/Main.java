package Main;

/**
 * @author Tomasz Kopacz
 */

import javax.swing.SwingUtilities;

import Controller.Controller;
import GUI.Window;

public class Main {
	
	public static void main(String[] args) {
		
		
		{	
			SwingUtilities.invokeLater( new Runnable() {
				public void run() {
					Window app = new Window();
					Controller c = new Controller(app);
					app.setController(c);
					app.setVisible(true);
				}
			 });
		}
	}

}
