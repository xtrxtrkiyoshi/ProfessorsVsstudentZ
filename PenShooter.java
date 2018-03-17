import javax.swing.ImageIcon;
import java.util.Random;


public class PenShooter extends StudentArsenal implements Runnable {
	private int damage;

	private final static ImageIcon IMG = new ImageIcon(ArsenalInterface.PENSHOOTER_IMAGE);
	
	public PenShooter(int xPos, int yPos, Lawn lawn) {
		super(ArsenalInterface.PENSHOOTER_NAME, ArsenalInterface.LIFE, xPos, yPos, ArsenalInterface.LANE_CONSTANT, lawn, IMG);
		this.damage = ArsenalInterface.PEN_DAMAGE;
		this.setImage();
	}

	public void attack() {
		Pen p = new Pen(this.xPos, this.yPos, this.damage, this.lawn);
		this.lawn.add(p);
		Thread th = new Thread(p);
		th.start();
	}
	
	// GETTERS
	public ImageIcon getImageIcon() {
		return this.IMG;
	}
	
	// SETTER
	public void setImage() {
		this.setBounds(this.xPos, this.yPos, this.width, this.height);
	}
	
	// THREAD
	public void run() {
		while(this.life > 0) {
			try{
				for(int i=0; i<30; i+=1){
					Thread.sleep(50);
					if(this.life == 0){
						break;
					}
				}
			} 
			catch(InterruptedException e){
				System.out.println("Thread has been interrupted.");
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			if(this.lawn.getBasicInstructorChecker()[this.lane]>0){
				this.activate();
			}
			else{
				this.isActive=false;
			}

			if(this.isActive == true){
				this.attack();
			}
			
		}
		int x = lawn.getRows().indexOf(this.yPos);
		int y = lawn.getColumns().indexOf(this.xPos);
		lawn.setLawnCheckerAvailable(x, y);
	}
}