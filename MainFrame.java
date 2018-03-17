import java.awt.Dimension;
import javax.swing.JFrame;

public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Instructors vs. Students");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1210, 760));
		frame.setResizable(false);
		
		Lawn lawn = new Lawn();
		
		Thread mainGame = new Thread(lawn);
		mainGame.start();

		frame.add(lawn);
		frame.pack();
		frame.setVisible(true);
	}
}