package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import application.offer.*;
import application.users.MultiRoleUser;
import controllers.GoBackController;
import controllers.SearchResultController;
import exceptions.NoRowSelectedException;
import windows.tableModels.SearchResultTableModel;

public class SearchResultWindow extends JFrame {
	
	private JButton viewOffer;
	private JButton goBackButton;
	private JTable table;
	private SearchResultTableModel dataModel;
	
	public SearchResultWindow(List<Offer> list) {
		super("Search result");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("Here are the offers you have searched for:");
		cont.add(label, BorderLayout.NORTH);
		
		dataModel = new SearchResultTableModel(list);

		this.table = new JTable(dataModel);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollBar = new JScrollPane(table);

		JPanel tablePanel = new JPanel();
		tablePanel.add(scrollBar, BorderLayout.CENTER);
		
		viewOffer = new JButton("View offer");
		tablePanel.add(viewOffer, BorderLayout.EAST);

		cont.add(tablePanel, BorderLayout.CENTER);
		
		this.goBackButton = new JButton("Go back");
		cont.add(this.goBackButton, BorderLayout.SOUTH);
		
		this.setSize(750, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}
	
	public void setGoBackController(GoBackController g) {
		this.goBackButton.addActionListener(g);
	}
	
	public void setController(SearchResultController c) {
		this.viewOffer.addActionListener(c);
	}
	
	public Offer getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();
		
		return this.dataModel.getRow(selectedRow);
	}
	
	public static void main(String... args) {
		
		List<Offer> list = new ArrayList<Offer>();
		House h = new House("28049", null, new MultiRoleUser(null, null, null, null, null));
		list.add(new HolidayOffer(LocalDate.now(), 2.0, 2.0, "aaaaaa", h, null));
		
		for (int i = 0; i < 3; i++) {
			list.add(new HolidayOffer(LocalDate.now().plusDays(2), 20.0, 20.0, "bbbbbb", h, null));
		}
		SearchResultWindow w = new SearchResultWindow(list);
		
		w.setController(new SearchResultController(null, w));
		w.setGoBackController(new GoBackController(new LoginWindow(), w));
		
		w.setVisible(true);
	}

}
