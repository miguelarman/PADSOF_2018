package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import application.App;
import application.offer.House;
import application.offer.LivingOffer;
import application.offer.Offer;
import application.users.Host;
import controllers.GoBackController;
import controllers.MyOffersController;
import exceptions.AUserIsAlreadyLoggedException;
import exceptions.IncorrectPasswordException;
import exceptions.NoRowSelectedException;
import exceptions.UnexistentUserException;
import exceptions.UserIsBannedException;
import windows.tableModels.MyHousesTableModel;
import windows.tableModels.SearchResultTableModel;

public class MyOffersWindow extends JFrame {

	private SearchResultTableModel dataModel;
	private JTable table;
	private JButton goBackButton;
	private JButton viewOffer;
	private JButton createOffer;

	public MyOffersWindow(List<Offer> offers) {
		super("My offers");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		
		JLabel label = new JLabel("Here are the offers you have created:");
		cont.add(label, BorderLayout.NORTH);
		
		dataModel = new SearchResultTableModel(offers);

		this.table = new JTable(dataModel);
		
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollBar = new JScrollPane(table);

		JPanel tablePanel = new JPanel();
		tablePanel.add(scrollBar, BorderLayout.CENTER);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(0,1));
		this.createOffer = new JButton("Create an offer");
		this.viewOffer = new JButton("View offer");
		buttonsPanel.add(createOffer);
		buttonsPanel.add(viewOffer);
		cont.add(buttonsPanel, BorderLayout.EAST);

		cont.add(tablePanel, BorderLayout.CENTER);
		
		this.goBackButton = new JButton("Go back");
		cont.add(this.goBackButton, BorderLayout.SOUTH);
				
		this.setSize(620, 525);
		this.setVisible(false);
	}

	public void setController(MyOffersController o) {
		this.createOffer.addActionListener(o);
		this.viewOffer.addActionListener(o);
	}

	public void setGoBackController(GoBackController gb) {
		this.goBackButton.addActionListener(gb);
	}

	public Offer getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();

		return this.dataModel.getRow(selectedRow);
	}
	
	public static void main(String...strings) throws Exception {
		List<Offer> list = new ArrayList<Offer>();
		House house = new House("zip", "city", new Host("host", "host", "host", "host", "host"));
		list.add(new LivingOffer(null, 0.0, 0.0, "description", house, 2));
		MyOffersWindow w = new MyOffersWindow(list);
		App app = App.openApp();
		app.login("host", "host");
		w.setController(new MyOffersController(app, w));
		w.setVisible(true);
	}

	public void addOfferToTable(Offer createdOffer) {
		SearchResultTableModel model = (SearchResultTableModel) this.table.getModel();
		model.addOfferToTable(createdOffer);
	}

}
