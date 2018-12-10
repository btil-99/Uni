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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class addAnimal extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField givenNameField;
	private JTextField priceField;
	private JTextField mainColourField;
	private JTextField arrivalField;
	private JTextField sellingField;
	private JComboBox<String> commonNameField;
	private JComboBox<String> sexField;
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private LocalDateTime currentDay = LocalDateTime.now();

	//function to make dialog box visible
	public static void showAddAnimal() {
		try {
			addAnimal dialog = new addAnimal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//Create the dialog
	 
	public addAnimal() {
		setBounds(100, 100, 504, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblGivenName = new JLabel("Given Name: ");
				lblGivenName.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblGivenName.setBounds(10, 14, 99, 17);
				panel.add(lblGivenName);
			}
			{
				givenNameField = new JTextField();
				givenNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
				givenNameField.setBounds(122, 11, 99, 23);
				panel.add(givenNameField);
				givenNameField.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("Common Name: ");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel.setBounds(10, 55, 105, 14);
				panel.add(lblNewLabel);
			}
			
			//populate combo box with animal common names
			String commonNames[]={"","Dog", "Cat", "Rabbit", "Golden Hamster", "Roborovski Hamster", "Guinea Pig", "Edwards's Fig Parrot", "Norwegian Blue", "Hyacinth Macaw", "Yellow Canary", "Goldfish", "Koi", "Common Barbel", "Boa Constrictor", "Corn Snake", "Black-necked Spitting Cobra"};
			commonNameField = new JComboBox<String>(commonNames);
			commonNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			commonNameField.setBounds(122, 54, 99, 23);
			panel.add(commonNameField);
			
			JLabel lblPrice = new JLabel("Price: ");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPrice.setBounds(10, 97, 46, 14);
			panel.add(lblPrice);
			
			priceField = new JTextField();
			priceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			priceField.setBounds(122, 96, 99, 23);
			panel.add(priceField);
			priceField.setColumns(10);
			
			JLabel lblSex = new JLabel("Sex: ");
			lblSex.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblSex.setBounds(10, 137, 46, 14);
			panel.add(lblSex);
			
			//populate combobox with different sexes
			String sex[]= {"","Female","Male"};
			//JComboBox<String> sexField = new JComboBox<String>(sex);
			sexField = new JComboBox<String>(sex);
			sexField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			sexField.setBounds(122, 136, 99, 23);
			panel.add(sexField);
			
			JLabel lblMainColour = new JLabel("Main Colour: ");
			lblMainColour.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblMainColour.setBounds(265, 17, 91, 14);
			panel.add(lblMainColour);
			
			mainColourField = new JTextField();
			mainColourField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			mainColourField.setBounds(355, 14, 99, 23);
			mainColourField.setText(null);
			panel.add(mainColourField);
			mainColourField.setColumns(10);
			
			JLabel lblArrivalDate = new JLabel("Arrival Date: ");
			lblArrivalDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblArrivalDate.setBounds(265, 57, 91, 14);
			panel.add(lblArrivalDate);
			
			arrivalField = new JTextField();
			arrivalField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			arrivalField.setBounds(355, 54, 99, 23);
			panel.add(arrivalField);
			arrivalField.setColumns(10);
			
			JLabel lblSellingDate = new JLabel("Selling Date:");
			lblSellingDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblSellingDate.setBounds(265, 93, 75, 23);
			panel.add(lblSellingDate);
			
			sellingField = new JTextField();
			sellingField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			sellingField.setBounds(355, 96, 99, 23);
			panel.add(sellingField);
			sellingField.setColumns(10);
		}
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.NORTH);
		
		JLabel lblAddAnimal = new JLabel("Add Animal");
		lblAddAnimal.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblAddAnimal);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel addAnimalLabel = new JLabel("Click 'Add Animal' button on main page to add to table");
				addAnimalLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				buttonPane.add(addAnimalLabel);
			}
			{
				JButton addButton = new JButton("Add");
				addButton.setActionCommand("Add");
				buttonPane.add(addButton);
				getRootPane().setDefaultButton(addButton);
				//adds a new animal to array list with details the user has inputed
				addButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						//checks whether whether all necessary fields are filled else dialog box shows up asking to fill in
						//all necessary details. If arrival date is left blank, current date is set for arrival date
						if(givenNameField.getText() !=null &&
						priceField.getText() !=null &&
						mainColourField.getText() != null &&
						commonNameField.getSelectedItem() != "" &&
						sexField.getSelectedItem() != "" && arrivalField.getText()== null){
							
							String givenNameVal = givenNameField.getText();
							String priceVal = priceField.getText();
							String mainColourVal = mainColourField.getText();
							String arrivalVal = arrivalField.getText();
							String sellingVal = sellingField.getText();
							String commonNameVal = (String)commonNameField.getSelectedItem();
							String sexVal = (String)sexField.getSelectedItem();
							
							petshop.pet.add(new animals(givenNameVal, commonNameVal, priceVal, sexVal, mainColourVal, arrivalVal, sellingVal));

							animalAdded animalAdded = new animalAdded();
							animalAdded.showAddedAnimal();
						}
						else if(givenNameField.getText() !=null &&
						priceField.getText() !=null &&
						mainColourField.getText() != null &&
						commonNameField.getSelectedItem() != "" &&
						sexField.getSelectedItem() != "" && arrivalField.getText()!=null) {
							
							String givenNameVal = givenNameField.getText();
							String priceVal = priceField.getText();
							String mainColourVal = mainColourField.getText();
							String arrivalVal = dtf.format(currentDay);
							String sellingVal = sellingField.getText();
							String commonNameVal = (String)commonNameField.getSelectedItem();
							String sexVal = (String)sexField.getSelectedItem();
							
							petshop.pet.add(new animals(givenNameVal, commonNameVal, priceVal, sexVal, mainColourVal, arrivalVal, sellingVal));
							
							animalAdded animalAdded = new animalAdded();
							animalAdded.showAddedAnimal();
							
						}
						else{
							incompleteField incompleteField = new incompleteField();
							incompleteField.showIncompleteField();
						}
					}
				});
			}
				//clears user input if clear button is clicked
				JButton clearButton = new JButton("Clear");
				clearButton.setActionCommand("Clear");
				buttonPane.add(clearButton);
				clearButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						clear();
					}
				});
			
		}
	}
	//function to clear user input
	public void clear() {
		givenNameField.setText(null);
		priceField.setText(null);
		mainColourField.setText(null);
		arrivalField.setText(null);
		sellingField.setText(null);
		commonNameField.setSelectedIndex(0);
		sexField.setSelectedIndex(0);
	}
	
	//function to sort animals first by selling date and then by arrival date
	public void sortAnimals(ArrayList<animals> pet) {
		pet.sort(Comparator.comparing(animals::getSellingDate, Comparator.nullsFirst(String::compareTo)).thenComparing(animals::getArrivalDate));
	}
	
	public void addAnimal() {
		
		if(givenNameField.getText() !=null &&
			priceField.getText() !=null &&
			mainColourField.getText() != null &&
			commonNameField.getSelectedItem() != "" &&
			sexField.getSelectedItem() != ""){
				
			String givenNameVal = givenNameField.getText();
			String priceVal = priceField.getText();
			String mainColourVal = mainColourField.getText();
			String arrivalVal = arrivalField.getText();
			String sellingVal = sellingField.getText();
			String commonNameVal = (String)commonNameField.getSelectedItem();
			String sexVal = (String)sexField.getSelectedItem();
			
			petshop.pet.add(new animals(givenNameVal, commonNameVal, priceVal, sexVal, mainColourVal, arrivalVal, sellingVal));
			petshop petshop = new petshop();
			petshop.createTable(petshop.pet, petshop.table);
		}
			
			
	}
	
}
