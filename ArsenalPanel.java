import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ArsenalPanel extends JLabel {
	
	private static final ImageIcon ICN  = new ImageIcon(LawnInterface.LAWN_ARSENAL_PANEL);
	private static final int xPos = 15;
	private static final int yPos = 0;
	private static final int width = ICN.getIconWidth();
	private static final int height = ICN.getIconHeight();
	
	public ArsenalPanel() {
		super(ICN);
		this.setBounds(xPos, yPos, width, height);
	}
	
}