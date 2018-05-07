package windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controllers.GoBackController;

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class SuggestionsWindow extends JFrame{

	/**
	* ID needed for the class to be Serializable
	*/
	private static final long serialVersionUID = 7334966751127560777L;
	
	/**
	 * Button to go to the previous window
	 */
	private JButton goBackButton;

	/**
	 * Constructor method of the class SuggestionsWindow
	 * @param suggestions Suggestions made that an admin
	 */
	public SuggestionsWindow(String suggestions) {
		super("Suggestions");
		JPanel cont = new JPanel();
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

		cont.setBorder(padding);
		cont.setLayout(new BorderLayout());
		
		JPanel messagePanel = new JPanel(new FlowLayout());
		goBackButton = new JButton("Go back");
		JLabel message = new JLabel("<html>" + suggestions.replaceAll("\n", "<br>") + "</html>");
		messagePanel.add(message);
		cont.add(goBackButton, BorderLayout.SOUTH);
		cont.add(message, BorderLayout.CENTER);
		cont.add(new JLabel("Here are the suggestions made by an admin:"), BorderLayout.NORTH);
		this.setContentPane(cont);
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}
	
	/**
	 * Method that assigns the goBackButton with the goBackController
	 * @param gb Controller that allows you to go to the previous window
	 */
	public void setGoBackController(GoBackController gb) {
		goBackButton.addActionListener(gb);
	}
}
