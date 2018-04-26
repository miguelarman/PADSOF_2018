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
import controllers.GoBackController;
import controllers.SearchResultController;
import windows.tableModels.SearchResultTableModel;

public class SearchResultWindow extends JFrame {
	
	private JButton viewOffer;
	private JButton goBackButton;
	private JTable table;
	
	public SearchResultWindow(List<Offer> list) {
		super("The offers you have searched for");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		// TODO crear la tabla con todos los resultados

		SearchResultTableModel dataModel = new SearchResultTableModel(list);

		this.table = new JTable(dataModel);
		
		table.setAutoCreateRowSorter(true);

		JScrollPane scrollBar = new JScrollPane(table);

		JPanel tablePanel = new JPanel();
		tablePanel.add(scrollBar, BorderLayout.CENTER);

		cont.add(tablePanel);
		
		this.goBackButton = new JButton("Go back");
		cont.add(this.goBackButton, BorderLayout.SOUTH);
		
		this.setSize(750, 500);
		this.setVisible(false);
	}
	
	public void setGoBackController(GoBackController g) {
		this.goBackButton.addActionListener(g);
	}
	
	public void setController(SearchResultController c) {
		this.viewOffer.addActionListener(c);
	}
	
	
	public static void main(String... args) {
		
		List<Offer> list = new ArrayList<Offer>();
		list.add(new HolidayOffer(LocalDate.now(), 2.0, 2.0, "aaaaaa", null, null));
		SearchResultWindow w = new SearchResultWindow(list);
		
		w.setVisible(true);
	}
}
