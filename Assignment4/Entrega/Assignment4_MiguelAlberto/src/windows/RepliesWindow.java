package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import application.opinion.Comment;
import application.opinion.Opinion;
import application.opinion.Rating;
import controllers.RepliesWindowController;
import exceptions.NoRowSelectedException;
import windows.tableModels.OfferOpinionsTableModel;

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class RepliesWindow extends JFrame {

	/**
	* ID needed for the class to be Serializable
	*/
	private static final long serialVersionUID = -8771376063056966740L;
	
	/**
	 * Field to introduce a comment to a comment
	 */
	private JTextField comment;
	
	/**
	 * Field to introduce a rating to a comment
	 */
	private JTextField rating;
	
	/**
	 * Button to add a rating to a comment
	 */
	private JButton addRating;
	
	/**
	 * Button to add a reply to a comment
	 */
	private JButton addReply;
	
	/**
	 * Button to view the replies to a comment
	 */
	private JButton viewReplies;
	
	/**
	 * Model to show the replies to a comment
	 */
	private OfferOpinionsTableModel dataModel;
	
	/**
	 * Structure to deploy the replies to a comment
	 */
	private JTable table;
	
	/**
	 * Comment whose replies are displayed on screen
	 */
	private Comment opinion;

	/**
	 * Constructor of the class RepliesWindow
	 * @param opinion Comment displayed on screen
	 */
	public RepliesWindow(Comment opinion) {
		
		super("Replies");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		
		this.opinion = opinion;
		
		JLabel title = new JLabel("Replies to opinion from " + opinion.getCommenter().getName() + ". \"" + opinion.getText() + "\"");
		cont.add(title, BorderLayout.NORTH);
		
		dataModel = new OfferOpinionsTableModel(opinion.getReplies());
		table = new JTable(dataModel);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollBar = new JScrollPane(table);

		JPanel tablePanel = new JPanel();
		tablePanel.add(scrollBar, BorderLayout.CENTER);
		
		this.viewReplies = new JButton("View replies");
		tablePanel.add(viewReplies, BorderLayout.EAST);

		cont.add(tablePanel, BorderLayout.CENTER);
		
		JPanel replyPanel = new JPanel();
		replyPanel.setLayout(new GridLayout(2, 2));
		this.comment = new JTextField(30);	this.comment.setText("Your reply to this comment");
		this.rating = new JTextField(4);	this.rating.setText("Your rating");
		this.addRating = new JButton("Rate this comment");
		this.addReply = new JButton("Reply to this comment");
		replyPanel.add(this.comment);
		replyPanel.add(this.addReply);
		replyPanel.add(this.rating);
		replyPanel.add(this.addRating);
		
		cont.add(replyPanel, BorderLayout.SOUTH);
		
		
		
		this.setSize(750, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	/**
	 * Method that assigns the addRating, addReply and viewReplies with the RepliesWindowController
	 * @param r Controller that allows you to do the needed functionality
	 */
	public void setController(RepliesWindowController r) {
		this.addRating.addActionListener(r);
		this.addReply.addActionListener(r);
		this.viewReplies.addActionListener(r);
	}

	/**
	 * Method that gives you the selected row of the table
	 * @return The selected opinion in the table
	 * @throws NoRowSelectedException When no row has been selected
	 */
	public Opinion getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();
		
		return this.dataModel.getRow(selectedRow);
	}

	/**
	 * Getter method for the comment
	 * @return The comment whose replies are displayed on screen
	 */
	public Comment getComment() {
		return this.opinion;
	}

	/**
	 * Getter method for the text in the comment field
	 * @return The reply that is being introduced
	 */
	public String getWrittenComment() {
		return this.comment.getText();
	}

	/**
	 * Getter method for the text in the rating field
	 * @return The rating that is being introduced
	 */
	public String getWrittenRating() {
		return this.rating.getText();
	}

	/**
	 * Method to add a rating to the table
	 * @param r Rating to be added
	 */
	public void addRating(Double r) {
		OfferOpinionsTableModel model = (OfferOpinionsTableModel)table.getModel();
		model.addOpinion(new Rating(r));
	}

	/**
	 * Method to add a comment to the table
	 * @param answer Comment to be added
	 */
	public void addReply(String answer) {
		OfferOpinionsTableModel model = (OfferOpinionsTableModel)table.getModel();
		model.addOpinion(new Comment(answer));
	}

}
