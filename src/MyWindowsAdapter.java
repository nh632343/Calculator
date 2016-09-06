import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MyWindowsAdapter extends WindowAdapter {

	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		super.windowClosing(arg0);
		System.exit(0);
	}
     
}
