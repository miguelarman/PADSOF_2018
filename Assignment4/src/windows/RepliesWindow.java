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

import application.App;
import application.opinion.Comment;
import application.opinion.Opinion;
import controllers.GoBackController;
import controllers.RepliesWindowController;
import exceptions.NoRowSelectedException;
import windows.tableModels.OfferOpinionsTableModel;

public class RepliesWindow extends JFrame {

	private JTextField comment;
	private JTextField rating;
	private JButton addRating;
	private JButton addReply;
	private JButton viewReplies;
	private OfferOpinionsTableModel dataModel;
	private JTable table;

	public RepliesWindow(Comment opinion) {
		
		super("Replies");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		
		// TODO poner el comentario arriba?
		
		JLabel title = new JLabel("Replies to opinion from " + opinion.getCommenter().getName() + ". \"" + opinion.getText() + "\"");
		cont.add(title, BorderLayout.NORTH);
		
		dataModel = new OfferOpinionsTableModel(opinion.getReplies());
		table = new JTable(dataModel);
		
		table.getTableHeader().setReorderingAllowed(false);
//		table.setAutoCreateRowSorter(true);
		
//		table.setPreferredSize(new Dimension(450/*table.getSize().width*/, 200));
//		table.setPreferredScrollableViewportSize(table.getPreferredSize());
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//		table.setFillsViewportHeight(true);
		

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
		this.setVisible(false);
	}

	public void setController(RepliesWindowController r) {
		this.addRating.addActionListener(r);
		this.addReply.addActionListener(r);
		this.viewReplies.addActionListener(r);
	}

	public void setGoBackController(GoBackController g) {
		// TODO Auto-generated method stub
		
	}

	public Opinion getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();
		
		return this.dataModel.getRow(selectedRow);
	}
	
	public static void main(String...strings) throws Exception {
		App app = App.openApp();
		app.login("multirole", "multirole");
		
		Comment c = new Comment("My comment");
		c.addReply("I agree with you");
		((Comment)c.getComments().get(0)).addReply("You are totally correct");
		c.rateComment(5.0);
		
		RepliesWindow w = new RepliesWindow(c);
		RepliesWindowController controller = new RepliesWindowController(app, w);
		w.setController(controller);
		
		
		w.setVisible(true);
	}

}
