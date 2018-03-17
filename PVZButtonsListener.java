import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

public class PVZButtonsListener implements ActionListener{
	private JPanel cards;
	private String buttonPanel;
	
	public PVZButtonsListener(JPanel cards, String buttonPanel) {
		this.cards = cards;
		this.buttonPanel = buttonPanel;
	}

	public void actionPerformed(ActionEvent e){
		CardLayout cl = (CardLayout)(cards.getLayout());
		cl.show(cards, buttonPanel);
	}
}