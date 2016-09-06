import java.awt.Button;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Test {
    static Button button;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Frame frame=new Frame();
        button=new Button();
        button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("click");
				button.setEnabled(false);
			}
        	
		});
        frame.add(button);
        frame.setSize(300, 300);
        frame.setVisible(true);
        
        
	}

}
