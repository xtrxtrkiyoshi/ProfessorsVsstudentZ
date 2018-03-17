import java.awt.Rectangle;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Pen extends JLabel implements Runnable{
	private int damage;

	private int xPos;
	private int yPos;
	private int height;
	private int width;
	
	private boolean collided;
	private Lawn lawn;
	private Rectangle rectangle;

	private final int PEN_SPEED = 5;
	private final int DAMAGE_SLEEP_MS = 25;
	private final static ImageIcon IMG = new ImageIcon("arsenals/Pen.png");
	
	public Pen(int xPos, int yPos, int damage, Lawn lawn) {
		super(IMG);

		this.damage = damage;
		
		this.xPos = xPos + 63;
		this.yPos = yPos + 15;
		this.height = IMG.getIconHeight();
		this.width = IMG.getIconWidth();

		this.rectangle = new Rectangle(xPos, yPos, width, height);
		this.lawn = lawn;
		this.collided = false;
		this.setBounds(xPos, yPos, width, height);
	}
	
	// GETTERS
	public int getX() {
		return this.xPos;
	}
	
	public int getY() {
		return this.yPos;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}
	
	// THREAD
	public synchronized void detectCollisionWithBasicInstructor() {
		Iterator<BasicInstructor> itr = this.lawn.getBasicInstructor().iterator();
		while(itr.hasNext()) {
			BasicInstructor bi = itr.next();
			Rectangle instructorBound = new Rectangle(bi.getX(), bi.getY(), bi.getWidth(), bi.getHeight());
			Rectangle ammunitionBound = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			if(instructorBound.intersects(ammunitionBound)) {
				bi.decreaseHP(this.damage);
				this.lawn.removeLawnComponent(this);
				this.collided = true;
				break;
			}	
		}
	}
	
	public synchronized void hurl() {
		this.xPos += this.PEN_SPEED;
		this.setBounds(this.xPos, this.yPos, this.width, this.height);
		this.detectCollisionWithBasicInstructor();
	}
	
	public void run() {
		while(this.collided == false) {
			try {
				this.hurl();
				Thread.sleep(this.DAMAGE_SLEEP_MS);
			} catch(Exception e) {}
		}
	}
}