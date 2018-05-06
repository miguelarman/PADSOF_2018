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
import controllers.RepliesWindowController;
import exceptions.NoRowSelectedException;
import windows.tableModels.OfferOpinionsTableModel;

public class RepliesWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8771376063056966740L;
	private JTextField comment;
	private JTextField rating;
	private JButton addRating;
	private JButton addReply;
	private JButton viewReplies;
	private OfferOpinionsTableModel dataModel;
	private JTable table;
	private Comment opinion;

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

	public void setController(RepliesWindowController r) {
		this.addRating.addActionListener(r);
		this.addReply.addActionListener(r);
		this.viewReplies.addActionListener(r);
	}

	public Opinion getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();
		
		return this.dataModel.getRow(selectedRow);
	}

	public Comment getComment() {
		return this.opinion;
	}

	public String getWrittenComment() {
		return this.rating.getText();
	}

	public String getWrittenRating() {
		return this.rating.getText();
	}

}
