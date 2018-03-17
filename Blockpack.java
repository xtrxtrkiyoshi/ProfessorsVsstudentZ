import javax.swing.ImageIcon;

public class Blockpack extends StudentArsenal implements Runnable {
	
	private final static ImageIcon FIRST = new ImageIcon(ArsenalInterface.BLOCKPACK_IMAGE1);
	private final static ImageIcon SECOND = new ImageIcon(ArsenalInterface.BLOCKPACK_IMAGE2);
	private final static ImageIcon LAST = new ImageIcon(ArsenalInterface.BLOCKPACK_IMAGE3);
	
	public Blockpack(int xPos, int yPos, Lawn lawn) {
		super(ArsenalInterface.BLOCKPACK_NAME, ArsenalInterface.FORTIFIED_LIFE, xPos, yPos, ArsenalInterface.LANE_CONSTANT, lawn, FIRST);
		this.isActive = true;
		this.setImage();
	}
	
	// GETTERS
	public ImageIcon getImageIcon() {
		return this.FIRST;
	}
	
	// SETTER
	public void setImage() {
		this.setBounds(this.xPos, this.yPos, this.width, this.height);
	}

	public void stand() {
		if(this.life<=72 && this.life>48) this.setIcon(FIRST);
		else if(this.life<=48 && this.life>24) this.setIcon(SECOND);
		else this.setIcon(LAST);
		this.setImage();
		
	}
	
	
	// THREAD
	public void run() {
		while(this.life > 0) {			
			this.stand();
		}
		int x = lawn.getRows().indexOf(this.yPos);
		int y = lawn.getColumns().indexOf(this.xPos);
		lawn.setLawnCheckerAvailable(x, y);	
	}
	
}