import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;

public class LevelUp extends JPanel {

	static JPanel panel = new JPanel();
	final static String RESTART = "Restart";
	final static String MAIN_MENU = "Main Menu";
	static Icon restartIcon = new ImageIcon("skins/continuebutton.png");
	static Icon menuIcon = new ImageIcon("skins/menubutton.png");
	static JButton continueButton = new JButton(restartIcon);
	static JButton menuButton = new JButton(menuIcon);

	private Image gameOverImg;

	public LevelUp() {
		try {
			gameOverImg = ImageIO.read(new File("skins/GAMESTOP.png"));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		this.setLayout();
		this.setComponents();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawImage(gameOverImg, 0, 0, null);
	}

	public void setLayout() {
		this.setLayout(new BorderLayout());
	}

	public void setComponents() {
		//panel
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,70,0));

		//button
		continueButton.setOpaque(false);
		continueButton.setContentAreaFilled(false);
		continueButton.setBorderPainted(false);
		menuButton.setOpaque(false);
		menuButton.setBorderPainted(false);
		menuButton.setContentAreaFilled(false);

		//add button to panel
		panel.add(continueButton);
		panel.add(menuButton);

		this.add(panel, BorderLayout.SOUTH);
	}
	public JButton getContinueButton(){
		return this.continueButton;
	}

	public JButton getMenuButton(){
		return this.menuButton;
	}	
}