
	import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

	public class exitListener extends WindowAdapter {
	  public void windowClosing(WindowEvent event) {
		  try {
			ExClient.exit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.exit(0);
	  }
	}

