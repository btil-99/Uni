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

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class computeRevenue extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField revenueField;
	public static final String[] days = {"","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", 
            "12", "13", "14", "15", "16", "17", "18", "19", "20", 
            "21", "22", "23", "24", "25", "26", "27", "28", "29", 
            "30", "31"};
	public static final String[] years= {"","2014","2015","2016","2017","2018"};
	public static final String months[] = {"","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", 
			"12"};
	private JComboBox<String> dayComboBox;
	private JComboBox<String> monthComboBox;
	private JComboBox<String> yearComboBox;

	/**
	 * Launch the application.
	 */
	public static void showComputeRevenue() {
		try {
			computeRevenue dialog = new computeRevenue();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public computeRevenue() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblComputeRevenue = new JLabel("Compute Revenue");
				lblComputeRevenue.setFont(new Font("Tahoma", Font.BOLD, 20));
				panel.add(lblComputeRevenue);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblMonth = new JLabel("Month: ");
				lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblMonth.setBounds(149, 29, 62, 14);
				panel.add(lblMonth);
			}
			{
				
				//populates month combobox with months as MM

				monthComboBox = new JComboBox<String>(months);
				monthComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
				monthComboBox.setBounds(200, 26, 84, 23);
				panel.add(monthComboBox);
				
			}
			{
				JLabel lblDay = new JLabel("Day:");
				lblDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDay.setBounds(43, 23, 46, 27);
				panel.add(lblDay);
			}
			{
				//populates combobox with days as dd
				dayComboBox = new JComboBox<String>(days);
				dayComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
				dayComboBox.setBounds(83, 26, 54, 23);
				panel.add(dayComboBox);
			}
			{
				JLabel lblRevenue = new JLabel("Revenue: ");
				lblRevenue.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblRevenue.setBounds(107, 140, 71, 14);
				panel.add(lblRevenue);
			}
			{
				revenueField = new JTextField();
				revenueField.setBounds(183, 139, 86, 23);
				panel.add(revenueField);
				revenueField.setColumns(10);
			}
			{
				JButton computeButton = new JButton("Compute");
				computeButton.setBounds(176, 78, 100, 30);
				panel.add(computeButton);
				computeButton.setActionCommand("Compute");
				getRootPane().setDefaultButton(computeButton);
				computeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						compute();
					}
				});
			}
			{
				JLabel lblYear = new JLabel("Year:");
				lblYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblYear.setBounds(308, 31, 54, 14);
				panel.add(lblYear);
			}
			
			//populates combobox with years as yyyy
			yearComboBox = new JComboBox<String>(years);
			yearComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
			yearComboBox.setBounds(348, 28, 66, 23);
			panel.add(yearComboBox);
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	
	//function to calculate revenue
	public void compute() {
		double revenue=0; 
		//creates a date array with user input (dd-mm-yyyy)
		String[] dateInputArray = {(String)dayComboBox.getSelectedItem(), (String)monthComboBox.getSelectedItem(),(String)yearComboBox.getSelectedItem()};
		//if only month and year selected, calculates revenue for the whole selected month
		if(dateInputArray[0]=="" && dateInputArray[1]!="" && dateInputArray[2] !="") {	
			for(animals animal : petshop.pet) {
				if(animal.getSellingDate()!="") {
					if(dateInputArray[1].equals(animal.getSellingDate().split("-")[1])&&dateInputArray[2].equals(animal.getSellingDate().trim().split("-")[0])) {
						revenue = revenue+ Double.parseDouble(animal.getPrice().trim());
					}
				}
			}
			revenueField.setText("£"+revenue);
			
		}
		//if day, month and year is selected, outputs revenue for the one specific day
		else if(dateInputArray[0]!="" && dateInputArray[1]!="" && dateInputArray[2] !="") {
			for(animals animal : petshop.pet) {
				if(animal.getSellingDate()!="") {
					if(dateInputArray[1].equals(animal.getSellingDate().split("-")[1])&&dateInputArray[0].equals(animal.getSellingDate().trim().split("-")[2])) {
						revenue = revenue+ Double.parseDouble(animal.getPrice().trim());
					}
				}
			}
			revenueField.setText("£"+revenue);
		}
		//if either month or year not selected, incomplete field dialog comes up
		else {
			revenueField.setText("");
			incompleteField incompleteField = new incompleteField();
			incompleteField.showIncompleteField();
		}

	}
	
}
