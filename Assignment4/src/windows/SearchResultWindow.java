package windows;

import java.awt.Container;

import java.util.List;

import javax.swing.*;

import application.offer.*;

public class SearchResultWindow extends JFrame {
	
	public SearchResultWindow(List<Offer> list) {
		super("The offers you have searched for");
		
		Container cont = super.getContentPane();
		
		
		
		// TODO crear la tabla con todos los resultados
		
		
		
		
		
		this.setSize(400, 500);
		this.setVisible(false);
	}
	
	public void setGoBackController(GoBackController g) {
		// TODO
	}
}
