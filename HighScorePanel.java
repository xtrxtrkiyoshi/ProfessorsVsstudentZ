import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class HighScorePanel extends JPanel {
	
	static JPanel backPanel = new JPanel();
	final static String BACK = "BACK";
	static Icon backIcon = new ImageIcon("skins/blueback.png");
	static JButton backButton = new JButton(backIcon);

	private BufferedImage background;

	public HighScorePanel() {
		try {
			this.background = ImageIO.read(getClass().getResource("skins/highscore.png"));
		} catch(Exception e) {}
		this.setLayout();
		this.setComponents();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawImage(background, 0, 0, this);
	}

	public void setLayout(){
		this.setLayout(new BorderLayout());
	}

	public void setComponents(){
		//panel
		backPanel.setOpaque(false);
		backPanel.setBorder(BorderFactory.createEmptyBorder(565, 0, 0, 900));

		//button
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);

		//add to panel
		backPanel.add(backButton);

		this.add(backPanel);
	}

	public JButton getBackButton(){
        return this.backButton;
    }
}


