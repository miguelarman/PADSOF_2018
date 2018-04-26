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
		super("Pestañas");
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		pestanias = new JTabbedPane();
		
		logoutButton = new JButton("Log out");
		
		JComponent personalPanel = new JPanel();
		
		// Adding content to personalPanel
		JLabel profileLabel = new JLabel("View your profile:");
		JButton profileButton = new JButton("Profile");
		personalPanel.add(profileLabel);
		personalPanel.add(profileButton);
		personalPanel.add(logoutButton);
		
		pestanias.addTab("Personal zone", null, personalPanel);
		
		// We add panels depending on the user's role
		if (role == Role.GUEST || role == Role.MULTIROLE) {
			JComponent guestPanel = new JPanel();
			
			// TODO Adding content to guestPanel
			
			guestPanel.add(logoutButton);
			
			pestanias.addTab("Guest zone", null, guestPanel);
		}
		
		if (role == Role.HOST || role == Role.MULTIROLE) {
			JComponent hostPanel = new JPanel();
			
			// TODO Adding content to hostPanel
			
			hostPanel.add(logoutButton);
			
			pestanias.addTab("Host zone", null, hostPanel);
		}
		
		if (role == Role.ADMIN) {
			JComponent adminPanel = new JPanel();
			
			// TODO Adding content to adminPanel
			
			
			adminPanel.add(logoutButton);
			
			
			pestanias.addTab("Admin zone", null, adminPanel);
		}

		cont.add(pestanias,BorderLayout.CENTER);
		cont.add(logoutButton, BorderLayout.SOUTH);
		
		this.setSize(400, 500);
		this.setVisible(false);
	}

	public void setController(PersonalWindowController c) {
		// TODO Auto-generated method stub
		
	}

}
