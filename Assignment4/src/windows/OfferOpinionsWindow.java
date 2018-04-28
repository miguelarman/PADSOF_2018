package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.offer.Offer;
import controllers.OfferOpinionsWindowController;

public class OfferOpinionsWindow extends JFrame {
	
	private JButton addReply;
	private JTextField comment;
	private JTextField rating;

	public OfferOpinionsWindow(Offer offer) {
		super("Opinions of the offer");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		// TODO crear la tabla con las opiniones y un botón para abrir otra pantalla con las respuestas a esa opinion
		
		JPanel replyPanel = new JPanel();
		replyPanel.setLayout(new BorderLayout());
		this.comment = new JTextField(30);	this.comment.setText("Write here your opinion about this offer");
		this.rating = new JTextField(4);	this.rating.setText("Rate this offer");
		this.addReply = new JButton("Rate this offer");
		replyPanel.add(this.comment, BorderLayout.NORTH);
		replyPanel.add(this.rating, BorderLayout.CENTER);
		replyPanel.add(this.addReply, BorderLayout.EAST);
		
		cont.add(replyPanel, BorderLayout.SOUTH);

		
		this.setSize(400,  400);
		this.setVisible(false);
	}

	public void setController(OfferOpinionsWindowController c) {
		// TODO Auto-generated method stub
		
	}

}
