package petshop;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class animalAdded extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/*
	 Create the dialog letting the user know that animal has been added
	 */
	public animalAdded() {
		setBounds(100, 100, 325, 158);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAnimalAddedClick = new JLabel("Animal added. Click 'Add Animal' on the main");
		lblAnimalAddedClick.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAnimalAddedClick.setBounds(10, 11, 289, 27);
		contentPanel.add(lblAnimalAddedClick);
		
		JLabel lblPageToAdd = new JLabel("page to update table");
		lblPageToAdd.setForeground(Color.BLACK);
		lblPageToAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPageToAdd.setBounds(88, 36, 185, 27);
		contentPanel.add(lblPageToAdd);
		
		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			setVisible(false);
			}
		});
	}
	public static void showAddedAnimal() {
		try {
			animalAdded dialog = new animalAdded();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
