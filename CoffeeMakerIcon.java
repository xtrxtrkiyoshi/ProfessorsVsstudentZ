import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

public class CoffeeMakerIcon extends ArsenalIcons implements MouseListener {

	private static int origXPos;
	private static int origYPos;
	private int newX;
	private int newY;
	
	public CoffeeMakerIcon(int xPos, int yPos, int cost, Lawn lawn) {
		super(xPos, yPos, cost, ArsenalInterface.COFFEE_MAKER_RECHARGE_TIME, COF, COF_INACTIVE, lawn);
		this.origXPos = xPos;
		this.origYPos = yPos;
		this.addMouseListener(this);
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {
		int x;
		int y;
		if(this.isAvailable) {
			this.newX = Lawn.getColumnCoordinate(e.getXOnScreen() - 40);
			this.newY = Lawn.getRowCoordinate(e.getYOnScreen() - 40);
			this.xPos = origXPos;
			this.yPos = origYPos;
			this.setBounds(xPos, yPos, width, height);
			
			try{
				x = this.lawn.getRows().indexOf(this.newY);
				y = this.lawn.getColumns().indexOf(this.newX);
				if(x!=-1 && y!=-1 && (lawn.getLawnChecker(x, y))==0) { 
					CoffeeMaker cm = new CoffeeMaker(newX, newY, this.lawn);
					lawn.setLawnCheckerUnavailable(x, y); 
					this.lawn.addLawnComponent(cm);
					this.lawn.getStudentArsenal().add(cm);
					cm.lane = x;
					cm.isPlanted = true;
					
					
					Thread th = new Thread(cm);
					th.start();
					
					this.lawn.deductCoffee(this.cost);
					this.lawn.setAvailabilityOfPlants();
				}
			}
			catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
	}
}