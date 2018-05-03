package houses;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import application.offer.House;
import controllers.GoBackController;
import controllers.MyHousesController;
import exceptions.NoRowSelectedException;
import windows.tableModels.HouseCharacteristicsTableModel;
import windows.tableModels.MyHousesTableModel;

public class MyHousesWindow extends JFrame {

	private JTable table;
	private MyHousesTableModel dataModel;
	private JButton addHouse;
	private JButton viewHouse;
	private JButton goBack;

	public MyHousesWindow(List<House> houses) {
		super("My houses");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		
		// TODO Auto-generated constructor stub
		
		// Create the table
		this.dataModel = new MyHousesTableModel(houses);
		table = new JTable(dataModel);
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollBar = new JScrollPane(table);
		cont.add(scrollBar, BorderLayout.CENTER);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(0,1));
		this.addHouse = new JButton("Add house");
		this.viewHouse = new JButton("View house");
		buttonsPanel.add(addHouse);
		buttonsPanel.add(viewHouse);
		cont.add(buttonsPanel, BorderLayout.EAST);
		
		this.goBack = new JButton("Go back");	
		cont.add(goBack, BorderLayout.SOUTH);
		
		
		this.setSize(400, 400);
		this.setVisible(false);
	}

	public void setController(MyHousesController h) {
		this.addHouse.addActionListener(h);
		this.viewHouse.addActionListener(h);
	}

	public void setGoBackController(GoBackController gb) {
		 this.goBack.addActionListener(gb);
	}
	
	public House getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();
		
		return this.dataModel.getRow(selectedRow);
	}

	public void addHouse(House h) {
		MyHousesTableModel model = (MyHousesTableModel) this.table.getModel();
		model.addHouse(h);
	}

}
