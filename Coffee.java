import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Coffee extends JLabel implements MouseListener, Runnable{
	
	private final static ImageIcon IMG = new ImageIcon("arsenals/coffee.png");
	private int xPos;
	private int yPos;
	private int maxYPos;
	private int width;
	private int height;
	private boolean clicked;
	private static final int VALUE = 25;
	private Lawn lawn;
	Random r = new Random();
	
	public Coffee(int xPos, int yPos, Lawn lawn, boolean produced) {
		super(IMG);
		this.xPos = xPos;
		this.yPos = yPos;
		this.lawn = lawn;
		this.clicked = false;
		if(produced) this.maxYPos = yPos+35;
		else this.maxYPos = r.nextInt(500)+200;
		this.width = IMG.getIconWidth();
		this.height = IMG.getIconHeight();
		this.addMouseListener(this);
		this.setBounds(xPos, yPos, this.width, this.height);
	}
	
	public synchronized void fall(int speed) {
		if(yPos<maxYPos) {
			yPos += speed;
		}
		this.setBounds(xPos, yPos, this.width, this.height);
	}
	
	public void run() {
		while(this.clicked==false) {
			this.fall(2);
			try {
				Thread.sleep(40);
			} catch(Exception e) {}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	public void mouseEntered(MouseEvent e) {
		this.setBounds(xPos, 3000, this.width, this.height);
		this.lawn.removeLawnComponent(this);
		this.lawn.addCoffee();
		this.lawn.setAvailabilityOfPlants();
		this.clicked = true;
	}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {	
	}
	
	
	
}