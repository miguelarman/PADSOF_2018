package windows;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WindowTest {
	public static void main(String... args) {
		JFrame window = new JFrame("Test window");

		// get container, assign layout
		Container container = window.getContentPane();
		container.setLayout(new FlowLayout());

		// create components
		JLabel label = new JLabel("Name");
		final JTextField field = new JTextField(10);
		JButton button = new JButton("Click here");
		JButton button2 = new JButton("Borrar");
		field.setText("nombre");

		// associate actions to components
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (field.getText() != null && !field.getText().isEmpty()) {
					if (field.getText().equals("España")) {
						JOptionPane.showMessageDialog(null, "Arriba España");
					} else {
						JOptionPane.showMessageDialog(null, "Buenos días " + field.getText());
					}
				}
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText("");
			}
		});

		// add components to container
		container.add(label);
		container.add(field);
		container.add(button);
		container.add(button2);

		// show window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(400, 500);
		window.setVisible(true);
	}
}
