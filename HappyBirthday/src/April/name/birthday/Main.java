package April.name.birthday;

import org.jfugue.Pattern;
import org.jfugue.Player;

import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
	public static void main(String[] args) {
		Player player =  new Player();
		Pattern pattern = new Pattern("Ci D C F Ew Ci D C G Fw Ci C6 A F E Dw Bb Bb A F G Fw");
		
		Frame f = new Frame();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//f.add(new Label("Being At India its the least I can do for you :-) :-)"));
		f.setSize(400, 400);
		f.setVisible(true);
		player.play(pattern);
		System.exit(0);
	}
}
