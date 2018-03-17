import java.awt.Rectangle;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LawnMower extends JLabel implements Runnable{
	private int xPos;
	private int yPos;
	private int width;
	private int height;

	private boolean isUsed;
	protected Lawn lawn;

	private final int MOWER_SPEED = 3;
	private final int MOWER_SLEEP_MS = 10;
	private final int EDGE_OF_SCREEN = 1200;

	private static ImageIcon image;

	public LawnMower(int xPos, int yPos, ImageIcon image, Lawn lawn){
		super(image);
		this.xPos = xPos;
		this.yPos = yPos;

		this.image = image;
		this.width = image.getIconWidth();
		this.height = image.getIconHeight();

		this.isUsed = false;
		this.lawn = lawn;
		this.setBounds(xPos, yPos, this.width, this.height);
	}
	
	// GETTERS
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getXPos(){
		return this.xPos;
	}
	
	public int getYPos(){
		return this.yPos;
	}
	
	public void move() {
		this.detectZombie();
		if(this.xPos<1200 && this.isUsed == true) {
			this.xPos += this.MOWER_SPEED;
			this.setBounds(this.xPos, this.yPos, this.width, this.height);
		}
	}
	
	// THREAD
	public synchronized void detectZombie() {
		Iterator<BasicInstructor> itr = this.lawn.getBasicInstructor().iterator();
		while(itr.hasNext()) {
			BasicInstructor bi = itr.next();
			Rectangle instructorBound = new Rectangle(bi.getX(), bi.getY(), bi.getWidth()-20, bi.getHeight()-20);
			Rectangle mowerBound = new Rectangle(this.xPos, this.yPos, this.width-20, this.height-20);
			if(instructorBound.intersects(mowerBound)) {
				this.isUsed = true;
				bi.setBounds(2500, 3000, 0, 0);
				bi.decreaseHP(20);
				break;
			}	
		}
	}
	
	public void run() {
		while(this.xPos<1200) {
			this.move();
			try {
				Thread.sleep(this.MOWER_SLEEP_MS);
			} catch(Exception e) {}
		}
		this.setBounds(2500, 3000, 0, 0);
		this.lawn.removeLawnComponent(this);
	}
}