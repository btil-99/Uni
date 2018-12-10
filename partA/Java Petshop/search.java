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

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class search extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField givenNameField;
	private JTextField mainColourField;

	/**
	 * Create the dialog.
	 */
	public search() {
		setBounds(100, 100, 451, 323);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSearch = new JLabel("Search");
			lblSearch.setBounds(15, 11, 75, 32);
			lblSearch.setFont(new Font("Tahoma", Font.BOLD, 20));
			contentPanel.add(lblSearch);
		}
		
		JLabel lblGivenName = new JLabel("Given Name:");
		lblGivenName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGivenName.setBounds(15, 48, 114, 23);
		contentPanel.add(lblGivenName);
		
		givenNameField = new JTextField();
		givenNameField.setBounds(10, 68, 99, 23);
		contentPanel.add(givenNameField);
		givenNameField.setColumns(10);
		
		JLabel lblCommonName = new JLabel("Common Name:");
		lblCommonName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCommonName.setBounds(15, 90, 114, 23);
		contentPanel.add(lblCommonName);
		
		JLabel lblSex = new JLabel("Sex:");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSex.setBounds(15, 135, 46, 23);
		contentPanel.add(lblSex);
		
		JLabel lblMainColour = new JLabel("Main Colour:");
		lblMainColour.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMainColour.setBounds(15, 180, 81, 23);
		contentPanel.add(lblMainColour);
		
		mainColourField = new JTextField();
		mainColourField.setBounds(10, 200, 99, 23);
		contentPanel.add(mainColourField);
		mainColourField.setColumns(10);
		
		String sex[]= {"","Female","Male"};
		JComboBox<String> commonNameBox = new JComboBox<String>(sex);
		commonNameBox.setBounds(10, 153, 99, 23);
		contentPanel.add(commonNameBox);
		
		String commonNames[]={"","Dog", "Cat", "Rabbit", "Golden Hamster", "Roborovski Hamster", "Guinea Pig", "Edwards's Fig Parrot", "Norwegian Blue", "Hyacinth Macaw", "Yellow Canary", "Goldfish", "Koi", "Common Barbel", "Boa Constrictor", "Corn Snake", "Black-necked Spitting Cobra"};
		JComboBox<String> sexComboBox = new JComboBox<String>(commonNames);
		sexComboBox.setBounds(10, 110, 99, 23);
		contentPanel.add(sexComboBox);
		
		JLabel lblClass = new JLabel("Class:");
		lblClass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblClass.setBounds(170, 48, 53, 23);
		contentPanel.add(lblClass);
		
		String animalClass[]= {"","Mammalia","Aves","Actinopterygii","Reptilia"};
		JComboBox<String> classBox = new JComboBox<String>(animalClass);
		classBox.setBounds(163, 68, 99, 23);
		contentPanel.add(classBox);
		
		String order[]= {"","Carnivora","Lagomorpha","Rodentia","Psittaciformes", "Passeriformes","Cypriniformes","Actinopterygii","Squamata","Squamata"};
		JLabel lblOrder = new JLabel("Order:");
		lblOrder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOrder.setBounds(173, 95, 46, 14);
		contentPanel.add(lblOrder);
		
		JComboBox<String> orderBox = new JComboBox<String>(order);
		orderBox.setBounds(163, 111, 99, 23);
		contentPanel.add(orderBox);
		
		JLabel lblFamily = new JLabel("Family:");
		lblFamily.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFamily.setBounds(170, 140, 53, 14);
		contentPanel.add(lblFamily);
		
		String family[]= {"","Canidae","Felidae","Leporidae","Cricetidae","Caviidae","Psittaculidae","Psittacidae","Fringillidae","Cyprinidae","Boidae","Colubridae","Elapidae"};
		JComboBox<String> familyBox = new JComboBox<String>(family);
		familyBox.setBounds(163, 154, 99, 23);
		contentPanel.add(familyBox);
		
		JLabel lblGenus = new JLabel("Genus:");
		lblGenus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGenus.setBounds(170, 185, 46, 14);
		contentPanel.add(lblGenus);
		
		String genus[]= {"","Canis","Felis","Oryctolagus","Mesocricetus","Phodopus","Cavia","Psittaculirostris","Mopsitta","Anodorhynchus","Critjagra","Carassius","Cyprinus","Barbus","Boa","Pantherophis","Naja"};
		JComboBox<String> genusBox = new JComboBox<String>(genus);
		genusBox.setBounds(163, 201, 99, 23);
		contentPanel.add(genusBox);
		
		JLabel lblSpecies = new JLabel("Species:");
		lblSpecies.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSpecies.setBounds(320, 53, 67, 14);
		contentPanel.add(lblSpecies);
		
		String species[]= {"","Canis lupus","Felis silvestris","Oryctolagus cuniculus","Mesocricetus auratus", "Phodopus roborovskii","Cavia porcellus","Psittaculirostris edwardsii","Mopsitta tanta","Anodorhynchus hyacinthinus","Crithagra flaviventris","Carassius auratus","Cyprinus rubrofuscus", "Barbus barbus","Boa constrictor","Pantherophis guttatus","Naja nigricollis"};
		JComboBox<String> speciesBox = new JComboBox<String>(species);
		speciesBox.setBounds(320, 69, 99, 23);
		contentPanel.add(speciesBox);
		
		JLabel lblMinLegs = new JLabel("Min Legs:");
		lblMinLegs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMinLegs.setBounds(320, 95, 67, 14);
		contentPanel.add(lblMinLegs);
		
		String legs[]= {"","0","1","2","3","4"};
		JComboBox<String> minLegsBox = new JComboBox<String>(legs);
		minLegsBox.setBounds(320, 111, 99, 23);
		contentPanel.add(minLegsBox);
		
		JLabel lblMaxLegs = new JLabel("Max Legs:");
		lblMaxLegs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMaxLegs.setBounds(320, 140, 81, 14);
		contentPanel.add(lblMaxLegs);
		
		JComboBox<String> maxLegsBox = new JComboBox<String>(legs);
		maxLegsBox.setBounds(320, 154, 99, 23);
		contentPanel.add(maxLegsBox);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
			}
		});
			
			/*{
				JButton clearButton = new JButton("Clear");
				cancelButton.setActionCommand("Clear");
				buttonPane.add(clearButton);
			}*/
		
	}
	
	public static void showSearch() {
		try {
			search dialog = new search();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
