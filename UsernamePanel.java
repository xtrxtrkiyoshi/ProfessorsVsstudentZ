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

public class UsernamePanel extends JPanel {

	private static String player;
	private static JPanel mainPanel = new JPanel(new BorderLayout());
	private static JPanel textfieldPanel = new JPanel();

	private final static String NAMETEXTFIELD = "Your Name Here";
	private final static String CANCEL = "CANCEL";
	private final static String SAVE = "START GAME";
	
	private static JTextField nameTextField =  new JTextField(UsernamePanel.NAMETEXTFIELD);
	private static JButton cancelButton = new JButton(UsernamePanel.CANCEL);
	private static JButton saveButton = new JButton(UsernamePanel.SAVE);

	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private String username;
	private BufferedImage background;

	public UsernamePanel() {
		try {
			this.background = ImageIO.read(getClass().getResource("skins/usernames.png"));
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
		Font newFont = myFont.deriveFont(30F);

		//panels
		UsernamePanel.mainPanel.setOpaque(false);
 		this.topPanel.setOpaque(false);
 		this.bottomPanel.setOpaque(false);

        this.topPanel.setBorder(BorderFactory.createEmptyBorder(400, 0, 0, 450));
 		this.bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 150, 400));

 		//button
        UsernamePanel.cancelButton.setOpaque(false);
        UsernamePanel.cancelButton.setContentAreaFilled(false);
        UsernamePanel.cancelButton.setBorderPainted(false);
        UsernamePanel.cancelButton.setFont(newFont);

        UsernamePanel.saveButton.setOpaque(false);
        UsernamePanel.saveButton.setContentAreaFilled(false);
        UsernamePanel.saveButton.setBorderPainted(false);
        UsernamePanel.saveButton.setFont(newFont);

 		//adds to panel
        this.topPanel.add(UsernamePanel.nameTextField);
        this.bottomPanel.add(UsernamePanel.cancelButton);
        this.bottomPanel.add(UsernamePanel.saveButton);

        UsernamePanel.mainPanel.add(this.topPanel, BorderLayout.NORTH);
        UsernamePanel.mainPanel.add(this.bottomPanel, BorderLayout.SOUTH);

        this.add(UsernamePanel.mainPanel);

        //actionListeners
        saveButton.addActionListener(new ActionListener() {         
            public void actionPerformed(ActionEvent e)  {
                if(e.getSource() == saveButton){
               		username = ("");

               		username = nameTextField.getText().trim();
               		player = ("Players' Name: "+ username);

               		nameTextField.setText("");

               		String data = UsernamePanel.player;
               		try{
               			BufferedWriter reader = new BufferedWriter(new FileWriter(new File("highscores.csv"),true));
               			reader.write(data);
               			reader.newLine();
               			reader.close();

               			System.out.println("Player recorded!");
               		}
               		catch(Exception ex){
               			System.out.println(ex);
               		}
                }
            }
        });
	}

	public JButton getCancelButton(){
		return this.cancelButton;
	}

	public JButton getSaveButton(){
		return this.saveButton;
	}

}