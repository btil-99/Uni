package petshop;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class incompleteField extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	//creates dialog box warning user if not all necessary fields are filled in
	public incompleteField(){
		setBounds(100, 100, 300, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
	
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	
		JLabel lblAdded = new JLabel("Please enter all necessary details");
		lblAdded.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdded.setBounds(45, 20, 200, 17);
		panel.add(lblAdded);
	
		JButton okbtn = new JButton("Ok");
		okbtn.setBounds(85, 60, 100, 30);
		panel.add(okbtn);
		okbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
			}
		});
	}

	public static void showIncompleteField() {
		try {
			incompleteField dialog = new incompleteField();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}