package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import application.offer.Offer;
import application.opinion.Comment;
import application.opinion.Opinion;
import application.opinion.Rating;
import controllers.OfferOpinionsWindowController;
import exceptions.NoRowSelectedException;
import windows.tableModels.OfferOpinionsTableModel;

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class OfferOpinionsWindow extends JFrame {
	
	/**
	* ID needed for the class to be Serializable
	*/
	private static final long serialVersionUID = 3219031551265265948L;
	
	/**
	 * Field to introduce a text comment
	 */
	private JTextField comment;
	
	/**
	 * Field to introduce a rating
	 */
	private JTextField rating;
	
	/**
	 * Button to add a reply to the selected comment
	 */
	private JButton addReplyButton;
	
	/**
	 * Button to add a rating
	 */
	private JButton addRatingButton;
	
	/**
	 * Structure to deploy the replies of an offer
	 */
	private JTable table;
	
	/**
	 * Button to watch the replies of a comment
	 */
	private JButton viewRepliesButton;
	
	/**
	 * Model to show the comments
	 */
	private OfferOpinionsTableModel dataModel;

	/**
	 * Constructor to the class OfferOpini
	 * @param offer
	 */
	public OfferOpinionsWindow(Offer offer) {
		super("Opinions of the offer");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		dataModel = new OfferOpinionsTableModel(offer.getOpinions());
		this.table = new JTable(dataModel);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollBar = new JScrollPane(table);

		JPanel tablePanel = new JPanel();
		tablePanel.add(scrollBar, BorderLayout.CENTER);
		
		this.viewRepliesButton = new JButton("View replies");
		tablePanel.add(viewRepliesButton, BorderLayout.EAST);

		cont.add(tablePanel, BorderLayout.CENTER);
		
		
		JPanel replyPanel = new JPanel();
		replyPanel.setLayout(new GridLayout(2, 2));
		this.comment = new JTextField(30);	this.comment.setText("Your opinion about this offer");
		this.rating = new JTextField(4);	this.rating.setText("Your rating");
		this.addRatingButton = new JButton("Rate this offer");
		this.addReplyButton = new JButton("Comment on this offer");
		replyPanel.add(this.comment);
		replyPanel.add(this.addReplyButton);
		replyPanel.add(this.rating);
		replyPanel.add(this.addRatingButton);
				
		cont.add(replyPanel, BorderLayout.SOUTH);

		
		this.setSize(700, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	public void setController(OfferOpinionsWindowController c) {
		this.addReplyButton.addActionListener(c);
		this.addRatingButton.addActionListener(c);
		this.viewRepliesButton.addActionListener(c);
	}

	public String getWrittenComment() {
		return this.comment.getText();
	}

	public String getWrittenRating() {
		return this.rating.getText();
	}
	
	public Opinion getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();
		
		return this.dataModel.getRow(selectedRow);
	}

	public void addComment(String comment) {
		OfferOpinionsTableModel panel = (OfferOpinionsTableModel)table.getModel();
		panel.addOpinion(new Comment(comment));
	}

	public void addRating(Double numericalRating) {
		OfferOpinionsTableModel panel = (OfferOpinionsTableModel)table.getModel();
		panel.addOpinion(new Rating(numericalRating));
	}

}
