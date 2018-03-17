import javax.swing.ImageIcon;
import java.util.Random;

public class CoffeeMaker extends StudentArsenal implements Runnable {
	
	private final static ImageIcon IMG = new ImageIcon(ArsenalInterface.COFFEE_MAKER_IMAGE);
	
	public CoffeeMaker(int xPos, int yPos, Lawn lawn) {
		super(ArsenalInterface.COFFEE_MAKER_NAME, ArsenalInterface.LIFE, xPos, yPos, ArsenalInterface.LANE_CONSTANT, lawn, IMG);
		this.isActive = true;
		this.setImage();
	}
	
	public void makeCoffee() {
		Coffee c = new Coffee(this.xPos+75, this.yPos-5, this.lawn, true);
		this.lawn.addLawnComponent(c);
		Thread t = new Thread(c);
		t.start();
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
		int coffeeMade = 0;	
		while(this.life > 0) {
			try{
				Random r = new Random();
				if(coffeeMade < 3){
					for(int i=0; i<5*20; i+=1){
						Thread.sleep(50); 
						if(this.life == 0){
							break;
						}
					}	
				}
				else{
					for(int i=0; i<ArsenalInterface.COFEE_MAKING_TIME*20; i+=1){
						Thread.sleep(50);
						if(this.life == 0){
							break;
						}
					}
					
				}
				this.makeCoffee();
				coffeeMade += 1;
			} 
			catch(InterruptedException e){
				System.out.println("Thread has been interrupted.");
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			
		}
		int x = lawn.getRows().indexOf(this.yPos);
		int y = lawn.getColumns().indexOf(this.xPos);
		lawn.setLawnCheckerAvailable(x, y);
	}
	
}