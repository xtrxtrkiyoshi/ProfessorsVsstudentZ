import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class StudentArsenal extends JLabel implements MouseMotionListener, ArsenalInterface{
	protected String name;	
	protected int life;

	protected int xPos;
	protected int yPos;
	protected int height;
	protected int width;

	protected int lane;
	protected boolean isPlanted;
	protected boolean isActive;
	protected Lawn lawn;

	protected static ImageIcon image;
	
	public StudentArsenal(String name, int life, int xPos, int yPos, int lane, Lawn lawn, ImageIcon image) {
		super(image);
		this.name = name;
		this.life = life;
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = image.getIconHeight();
		this.width = image.getIconWidth();

		this.lane = lane;
		this.isPlanted = false;
		this.isActive = true;
		this.lawn = lawn;
		
		this.image = image;
		this.addMouseMotionListener(this);
	}
	
	public void setPlanted() {
		this.isPlanted = true;
	}

	public int getLife() {
		return this.life;
	}
	
	// ABSTRACT METHOD IMPLEMENTATIONS: MouseMotionListener		
	public void mouseDragged(MouseEvent e) {
		if(this.isPlanted == false) {
			xPos = e.getXOnScreen() - 40;
			yPos = e.getYOnScreen() - 40;
			this.setBounds(this.xPos, this.yPos, this.width, this.height);
		}
		
	}
	public void mouseMoved(MouseEvent e) {}
	
	// ABSTRACT METHOD IMPLEMENTATIONS: ArsenalInterface
	public void activate() {
		this.isActive = true;
	}
	
	// THREAD
	public void decreaseLife(int damage/* , BasicInstructor bi */){
		if(this.life > 0) this.life -= damage;
		else if(this.life == 0) {
			//bi.vacancy = false;
			this.setBounds(this.xPos, this.yPos + 3000, this.width, this.height);
			this.lawn.getStudentArsenal().remove(this);
			this.lawn.removeLawnComponent(this);
		}
		try {
			Thread.sleep(ArsenalInterface.PUNCH_ARSENAL_SLEEP_MS);
		} catch(Exception e) {}
	}
}