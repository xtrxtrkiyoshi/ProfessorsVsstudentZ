import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ArsenalIcons extends JLabel implements MouseMotionListener {
	
	protected static final ImageIcon PEN = new ImageIcon("skins/penshooter_icon.png");
	protected static final ImageIcon COF = new ImageIcon("skins/coffeemaker_icon.png");
	protected static final ImageIcon BLOC = new ImageIcon("skins/blockpack_icon.png");
	protected static final ImageIcon PEN_INACTIVE = new ImageIcon("skins/penshooter_icon_inactive.png");
	protected static final ImageIcon COF_INACTIVE = new ImageIcon("skins/coffeemaker_icon_inactive.png");
	protected static final ImageIcon BLOC_INACTIVE = new ImageIcon("skins/blockpack_icon_inactive.png");
	
	protected Lawn lawn;
	
	protected boolean isAvailable;
	
	protected int xPos;
	protected int yPos;
	protected int width;
	protected int height;
	protected int cost;
	protected int recharge;
	protected ImageIcon activeIcon;
	protected ImageIcon inactiveIcon;
	
	public ArsenalIcons(int xPos, int yPos, int cost, int recharge, ImageIcon activeIcon, ImageIcon inactiveIcon, Lawn lawn) {
		super(activeIcon);
		this.activeIcon = activeIcon;
		this.inactiveIcon = inactiveIcon;
		this.xPos = xPos;
		this.yPos = yPos;
		this.recharge = recharge;
		this.height = activeIcon.getIconHeight();
		this.width = activeIcon.getIconWidth();
		this.cost = cost;
		this.lawn = lawn;
		this.setBounds(xPos, yPos, width, height);
		this.addMouseMotionListener(this);
		this.setAvailability();
	}
	
	public void setAvailability() {
		if(this.lawn.getCoffeeCount() >= this.cost) this.isAvailable=true;
		else if(this.lawn.getCoffeeCount() < this.cost) this.isAvailable=false;
		this.setImage();
	}
	
	public void setImage() {
		if(isAvailable) this.setIcon(this.activeIcon);
		else this.setIcon(this.inactiveIcon);
		this.setBounds(xPos, yPos, width, height);
	}
	
	public void mouseDragged(MouseEvent e) {
		if(this.isAvailable) {
			xPos = e.getXOnScreen() - 40;
			yPos = e.getYOnScreen() - 40;
			this.setBounds(this.xPos, this.yPos, this.width, this.height);
		}
	}
	public void mouseMoved(MouseEvent e) {}
	
}