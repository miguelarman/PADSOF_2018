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
import application.opinion.Opinion;
import controllers.OfferOpinionsWindowController;
import exceptions.NoRowSelectedException;
import windows.tableModels.OfferOpinionsTableModel;

public class OfferOpinionsWindow extends JFrame {
	
	private JTextField comment;
	private JTextField rating;
	private JButton addReply;
	private JButton addRating;
	private JTable table;
	private JButton viewReplies;
	private OfferOpinionsTableModel dataModel;

	public OfferOpinionsWindow(Offer offer) {
		super("Opinions of the offer");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		// TODO poner algo de la oferta encima? el zip?
		
		
		dataModel = new OfferOpinionsTableModel(offer.getOpinions());
		this.table = new JTable(dataModel);
		
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
		this.comment = new JTextField(30);	this.comment.setText("Your opinion about this offer");
		this.rating = new JTextField(4);	this.rating.setText("Your rating");
		this.addRating = new JButton("Rate this offer");
		this.addReply = new JButton("Comment on this offer");
		replyPanel.add(this.comment);
		replyPanel.add(this.addReply);
		replyPanel.add(this.rating);
		replyPanel.add(this.addRating);
				
		cont.add(replyPanel, BorderLayout.SOUTH);

		
		this.setSize(400,  400);
		this.setVisible(false);
	}

	public void setController(OfferOpinionsWindowController c) {
		this.addReply.addActionListener(c);
		this.addRating.addActionListener(c);
		this.viewReplies.addActionListener(c);
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

}
