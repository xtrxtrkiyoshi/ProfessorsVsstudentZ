import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;

public class HomePanel extends JPanel {
	private static JPanel mainPanel = new JPanel(new BorderLayout());
	private static JPanel quitPanel = new JPanel();
	private static JPanel startPanel = new JPanel();
	private static JPanel leaderPanel = new JPanel();
	private static JPanel aboutPanel = new JPanel();

	private final static String QUIT = "QUIT";
	private final static String START = "START";
	private final static String LEADERBOARDS = "LEADERBOARD";
	private final static String ABOUT = "ABOUT";
	
	private BufferedImage background;
	
	private static Icon startIcon = new ImageIcon("skins/startbutton.png");
 	private static Icon quitIcon = new ImageIcon("skins/quit.png");

	private static JButton quit = new JButton(quitIcon);
 	private static JButton start = new JButton(startIcon);

	private static JButton about = new JButton(HomePanel.ABOUT);
	private static JButton leaderboard = new JButton(HomePanel.LEADERBOARDS);

	public HomePanel() {
		try {
			this.background = ImageIO.read(getClass().getResource("skins/homepage.jpg"));
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
		//font
		Font myFont = new Font("Serif", Font.BOLD, 5);
		Font newFont = myFont.deriveFont(20F);

 		//panels
 		HomePanel.mainPanel.setOpaque(false);
 		HomePanel.quitPanel.setOpaque(false);
 		HomePanel.startPanel.setOpaque(false);
 		HomePanel.leaderPanel.setOpaque(false);
 		HomePanel.aboutPanel.setOpaque(false);

 		HomePanel.quitPanel.setBorder(BorderFactory.createEmptyBorder(-15, 120, 0, 0));
 		HomePanel.startPanel.setBorder(BorderFactory.createEmptyBorder(50, 376, 0, 0));
 		HomePanel.leaderPanel.setBorder(BorderFactory.createEmptyBorder(190, 45, 0, 0));
 		HomePanel.aboutPanel.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 35));

 		//buttons
 		HomePanel.quit.setOpaque(false);
		HomePanel.quit.setContentAreaFilled(false);
		HomePanel.quit.setBorderPainted(false);

 		HomePanel.start.setOpaque(false);
		HomePanel.start.setContentAreaFilled(false);
		HomePanel.start.setBorderPainted(false);

 		HomePanel.leaderboard.setOpaque(false);
		HomePanel.leaderboard.setContentAreaFilled(false);
		HomePanel.leaderboard.setBorderPainted(false);
		HomePanel.leaderboard.setFont(newFont);
		HomePanel.leaderboard.setForeground(new Color(0, 0, 0));

 		HomePanel.about.setOpaque(false);
		HomePanel.about.setContentAreaFilled(false);
		HomePanel.about.setBorderPainted(false);
		HomePanel.about.setFont(newFont);
		HomePanel.about.setForeground(new Color(0, 0, 0));

 		//actionListeners
 		HomePanel.quit.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e)  {
                System.exit(0);
            }
        });

        //adds to panels
		HomePanel.quitPanel.add(HomePanel.quit);
 		HomePanel.startPanel.add(HomePanel.start);
 		HomePanel.aboutPanel.add(HomePanel.about);
 		HomePanel.leaderPanel.add(HomePanel.leaderboard);

 		//add to main panels
 		HomePanel.mainPanel.add(HomePanel.startPanel, BorderLayout.NORTH);
 		HomePanel.mainPanel.add(HomePanel.quitPanel, BorderLayout.WEST);
 		HomePanel.mainPanel.add(HomePanel.leaderPanel, BorderLayout.CENTER);
 		HomePanel.mainPanel.add(HomePanel.aboutPanel, BorderLayout.EAST);
 		
 		this.add(HomePanel.mainPanel);
	}

	public JButton getStartButton(){
		return this.start;
	}

	public JButton getAboutsButton(){
		return this.about;
	}

	public JButton getLeaderboardButton(){
		return this.leaderboard;
	}
}