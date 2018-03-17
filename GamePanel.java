import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel {

	public GamePanel() {
		this.setLayout();
		this.setComponents();
	}

	public void setLayout(){
		this.setLayout(new BorderLayout());
	}

	public void setComponents(){
		Lawn lawn = new Lawn();

		Thread mainGame = new Thread(lawn);
		mainGame.start();

		this.add(lawn);
	}
}