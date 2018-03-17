import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PVZGameLayout extends JFrame{
    private final static String HOME_PANEL = "Home";
    private final static String ABOUTS_PANEL = "Abouts";
    private final static String USERNAME_PANEL = "Username";
    private final static String HIGHSCORE_PANEL = "Leaderboard";
    private final static String GAME_PANEL = "Game";

    private final static String GAME_OVER_PANEL = "Game Over";
    private final static String LEVEL_UP_PANEL = "Level Up";

    private final static String TITLE = "Professors vs. studentZ";

    private final int WIDTH = 1210;
    private final int HEIGHT = 760;

    private static CardLayout cl;

    public PVZGameLayout(){
        super(PVZGameLayout.TITLE);
        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.setResizable(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        final JPanel mainPanel = new JPanel();
        HomePanel homePanel = new HomePanel();
        AboutsPanel aboutsPanel = new AboutsPanel();
        UsernamePanel usernamePanel = new UsernamePanel();
        HighScorePanel highscorePanel =  new HighScorePanel(); 
        GamePanel gamePanel = new GamePanel();
        LevelUp levelUp = new LevelUp(); 
        GameOver gameOverPanel = new GameOver();

        this.cl = new CardLayout();
        mainPanel.setLayout(cl);
        mainPanel.add(homePanel, PVZGameLayout.HOME_PANEL);
        mainPanel.add(aboutsPanel, PVZGameLayout.ABOUTS_PANEL);
        mainPanel.add(usernamePanel, PVZGameLayout.USERNAME_PANEL);
        mainPanel.add(highscorePanel, PVZGameLayout.HIGHSCORE_PANEL);
        mainPanel.add(gamePanel, PVZGameLayout.GAME_PANEL);
        mainPanel.add(levelUp, PVZGameLayout.LEVEL_UP_PANEL);        
        mainPanel.add(gameOverPanel, PVZGameLayout.GAME_OVER_PANEL);        

        Container container = this.getContentPane();
        container.add(mainPanel);
        this.cl.show(mainPanel, HOME_PANEL);

        homePanel.getStartButton().addActionListener(new PVZButtonsListener(mainPanel, USERNAME_PANEL)); 
        homePanel.getAboutsButton().addActionListener(new PVZButtonsListener(mainPanel, ABOUTS_PANEL));
        homePanel.getLeaderboardButton().addActionListener(new PVZButtonsListener(mainPanel, HIGHSCORE_PANEL));
        aboutsPanel.getBackButton().addActionListener(new PVZButtonsListener(mainPanel, HOME_PANEL));
        usernamePanel.getCancelButton().addActionListener(new PVZButtonsListener(mainPanel, HOME_PANEL));
        usernamePanel.getSaveButton().addActionListener(new PVZButtonsListener(mainPanel, GAME_PANEL));
        highscorePanel.getBackButton().addActionListener(new PVZButtonsListener(mainPanel, HOME_PANEL));
        // gameOverPanel.getRestartButton().addActionListener(new PVZButtonsListener(mainPanel, GAME_PANEL));
        gameOverPanel.getMenuButton().addActionListener(new PVZButtonsListener(mainPanel, HOME_PANEL));

        gameOverPanel.getRestartButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                GamePanel gamePanel = new GamePanel();
                mainPanel.add(gamePanel, PVZGameLayout.GAME_PANEL);
                cl.show(mainPanel, GAME_PANEL);
            }
        });

        levelUp.getMenuButton().addActionListener(new PVZButtonsListener(mainPanel, HOME_PANEL));

        levelUp.getContinueButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                GamePanel gamePanel = new GamePanel();
                mainPanel.add(gamePanel, PVZGameLayout.GAME_PANEL);
                cl.show(mainPanel, GAME_PANEL);
            }
        });

        this.pack();
        this.setVisible(true);
    }
}