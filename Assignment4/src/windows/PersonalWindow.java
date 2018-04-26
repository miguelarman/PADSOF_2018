package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.*;

import application.users.RegisteredUser.Role;
import controllers.PersonalWindowController;

public class PersonalWindow extends JFrame {

	private JTabbedPane pestanias;
	private JButton logoutButton;
	
	public PersonalWindow(Role role) {
		super("Pesta�as");
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		pestanias = new JTabbedPane();
		
		logoutButton = new JButton("Log out");
		
		JComponent personalPanel = new JPanel();
		
		// TODO Adding content to personalPanel
		
		pestanias.addTab("Personal zone", null, personalPanel);
		
		// We add panels depending on the user's role
		if (role == Role.GUEST || role == Role.MULTIROLE) {
			JComponent guestPanel = new JPanel();
			
			// TODO Adding content to guestPanel
			
			
			pestanias.addTab("Guest zone", null, guestPanel);
		}
		
		if (role == Role.HOST || role == Role.MULTIROLE) {
			JComponent hostPanel = new JPanel();
			
			// TODO Adding content to hostPanel
			
			
			pestanias.addTab("Host zone", null, hostPanel);
		}
		
		if (role == Role.ADMIN) {
			JComponent adminPanel = new JPanel();
			
			// TODO Adding content to adminPanel
			
			pestanias.addTab("Admin zone", null, adminPanel);
		}

		cont.add(pestanias,BorderLayout.CENTER);
		cont.add(logoutButton, BorderLayout.SOUTH);
		
		this.setSize(400, 500);
		this.setVisible(false);
	}

	public void setLogoutController(PersonalWindowController c) {
		logoutButton.addActionListener(c);
	}
	
	public void setController(PersonalWindowController c) {
		// TODO Auto-generated method stub
		
	}

}
