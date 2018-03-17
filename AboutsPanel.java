import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutsPanel extends JPanel{
    private static JPanel rightPanel = new JPanel(new CardLayout());

    private final static String ABOUTS_PANEL = "ABOUTS";
    private final static String CREDITS_PANEL = "CREDITS";
    private final static String HELP_PANEL = "HELP";
    private final static String BACK_PANEL = "BACK";

    private static JButton aboutButton = new JButton(AboutsPanel.ABOUTS_PANEL);
    private static JButton creditButton = new JButton(AboutsPanel.CREDITS_PANEL);
    private static JButton helpButton = new JButton(AboutsPanel.HELP_PANEL);
    private static JButton backButton = new JButton(AboutsPanel.BACK_PANEL);

    private static CardLayout cl = new CardLayout();


    private JPanel leftPanel = new JPanel();
    private GridBagConstraints gbc = new GridBagConstraints();
    private BufferedImage background;

    public AboutsPanel() {
        try {
            this.background = ImageIO.read(getClass().getResource("skins/abouts.jpg"));
        } catch(Exception e) {}
        this.setLayout();
        this.setComponents();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.drawImage(background, 0, 0, this);
        this.setLayout();
        this.setComponents();
    }

    public void setLayout(){
        this.setLayout(new BorderLayout());
    }

    public void setComponents(){
        // font
        Font myFont = new Font("Serif", Font.BOLD, 5);
        Font newFont = myFont.deriveFont(20F);

        //panels
        this.leftPanel.setLayout(new GridBagLayout());
        this.leftPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 0, 0));
        this.leftPanel.setOpaque(false);

        AboutsPanel.rightPanel.setLayout(cl);
        AboutsPanel.rightPanel.setOpaque(false);                
        AboutsPanel.rightPanel.setBorder(BorderFactory.createEmptyBorder(65, 0, 0, 180));
        this.gbc.insets= new Insets(5, 135, 5, 165);

        //buttons on the left panel that are always shown
        this.gbc.gridx = 0;
        this.gbc.gridx = 1;
        AboutsPanel.aboutButton.setOpaque(false);
        AboutsPanel.aboutButton.setContentAreaFilled(false);
        AboutsPanel.aboutButton.setBorderPainted(false);
        AboutsPanel.aboutButton.setFont(newFont);

        this.gbc.gridx = 0;
        this.gbc.gridx = 2;
        AboutsPanel.creditButton.setOpaque(false);
        AboutsPanel.creditButton.setContentAreaFilled(false);
        AboutsPanel.creditButton.setBorderPainted(false);
        AboutsPanel.creditButton.setFont(newFont);

        this.gbc.gridx = 0;
        this.gbc.gridx = 3;
        AboutsPanel.helpButton.setOpaque(false);
        AboutsPanel.helpButton.setContentAreaFilled(false);
        AboutsPanel.helpButton.setBorderPainted(false);
        AboutsPanel.helpButton.setFont(newFont);

        this.gbc.gridx = 0;
        this.gbc.gridx = 4;
        AboutsPanel.backButton.setOpaque(false);
        AboutsPanel.backButton.setContentAreaFilled(false);
        AboutsPanel.backButton.setBorderPainted(false);
        AboutsPanel.backButton.setFont(newFont);

        //labels
        JLabel aboutsLabel = new JLabel();
        JLabel creditsLabel = new JLabel();
        JLabel helpLabel = new JLabel();

        aboutsLabel.setIcon(new ImageIcon("skins/about.png"));
        creditsLabel.setIcon(new ImageIcon("skins/credit.png"));
        helpLabel.setIcon(new ImageIcon("skins/note.png"));

        //adds to the left panel
        this.leftPanel.add(AboutsPanel.aboutButton, this.gbc);
        this.leftPanel.add(AboutsPanel.creditButton, this.gbc);
        this.leftPanel.add(AboutsPanel.helpButton, this.gbc);
        this.leftPanel.add(AboutsPanel.backButton, this.gbc);

        AboutsPanel.rightPanel.add(aboutsLabel, this.ABOUTS_PANEL);
        AboutsPanel.rightPanel.add(creditsLabel, this.CREDITS_PANEL);
        AboutsPanel.rightPanel.add(helpLabel, this.HELP_PANEL);

        this.add(this.leftPanel, BorderLayout.WEST);
        this.add(AboutsPanel.rightPanel, BorderLayout.EAST);

        AboutsPanel.aboutButton.addActionListener(new PVZButtonsListener(AboutsPanel.rightPanel, AboutsPanel.ABOUTS_PANEL)); 
        AboutsPanel.creditButton.addActionListener(new PVZButtonsListener(AboutsPanel.rightPanel, AboutsPanel.CREDITS_PANEL)); 
        AboutsPanel.helpButton.addActionListener(new PVZButtonsListener(AboutsPanel.rightPanel, AboutsPanel.HELP_PANEL)); 
    }

    public JButton getBackButton(){
        return this.backButton;
    }
}
