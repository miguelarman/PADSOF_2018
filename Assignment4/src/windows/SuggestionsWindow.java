package windows;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.GoBackController;

public class SuggestionsWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7334966751127560777L;
	private JButton goBackButton;

	public SuggestionsWindow(String suggestions) {
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		goBackButton = new JButton("Go back");
		JLabel message = new JLabel(suggestions);
		cont.add(goBackButton, BorderLayout.SOUTH);
		cont.add(message, BorderLayout.CENTER);
		this.setSize(300, 200);
		this.setVisible(false);
	}
	
	public void setGoBackController(GoBackController gb) {
		goBackButton.addActionListener(gb);
	}
}
