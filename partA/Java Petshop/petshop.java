package petshop;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.io.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class petshop extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTable table;
	File selectedFile = null;
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private LocalDateTime currentDay = LocalDateTime.now();
	static ArrayList<animals> pet = new ArrayList<animals>();
	
	//launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					petshop frame = new petshop();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//create main page
	public petshop() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblPetShop = new JLabel("Pet Shop ");
		lblPetShop.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		panel.add(lblPetShop);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{89, 0};
		gbl_panel_1.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		
		JButton btnWriteAnimals = new JButton("Write Animals");
		
		//when add animals button is run, functions addAnimals and createTable are called
		JButton btnAddAnimals = new JButton("Add Animals");
		btnAddAnimals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//addAnimals();
				addAnimals addAnimals = new addAnimals();
				addAnimals.adddingAnimals();
				createTable(pet, table);
			}
		});
		
		GridBagConstraints gbc_btnAddAnimals = new GridBagConstraints();
		gbc_btnAddAnimals.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddAnimals.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddAnimals.anchor = GridBagConstraints.NORTH;
		gbc_btnAddAnimals.gridx = 0;
		gbc_btnAddAnimals.gridy = 0;
		panel_1.add(btnAddAnimals, gbc_btnAddAnimals);
		
		JButton btnAddAnimalDetails = new JButton("Add Animal Details");
		GridBagConstraints gbc_btnAddAnimalDetails = new GridBagConstraints();
		gbc_btnAddAnimalDetails.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddAnimalDetails.gridx = 0;
		gbc_btnAddAnimalDetails.gridy = 3;
		panel_1.add(btnAddAnimalDetails, gbc_btnAddAnimalDetails);
		
		/* opens a new dialog where user can enter a new animal details 
		   which can then be updated when 
		   add animal button is clicked */
		
		btnAddAnimalDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAnimal addAnimal = new addAnimal();
				addAnimal.showAddAnimal();
				
			}
			
		});
		
		JButton btnAddAnimal = new JButton("Add Animal");
		GridBagConstraints gbc_btnAddAnimal = new GridBagConstraints();
		gbc_btnAddAnimal.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddAnimal.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddAnimal.gridx = 0;
		gbc_btnAddAnimal.gridy = 4;
		panel_1.add(btnAddAnimal, gbc_btnAddAnimal);
		
		//add animal button updates the table from the ArrayList
		btnAddAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTable(pet, table);
			}
			
		});
		
		JButton btnSellAnimal = new JButton("Sell Animal");
		GridBagConstraints gbc_btnSellAnimal = new GridBagConstraints();
		gbc_btnSellAnimal.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSellAnimal.insets = new Insets(0, 0, 5, 0);
		gbc_btnSellAnimal.gridx = 0;
		gbc_btnSellAnimal.gridy = 5;
		panel_1.add(btnSellAnimal, gbc_btnSellAnimal);
		
		//sell animal function is called which adds current date to selling date when animal is sold
		btnSellAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellAnimal(table);
			}
			
		});
		GridBagConstraints gbc_btnWriteAnimals = new GridBagConstraints();
		gbc_btnWriteAnimals.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnWriteAnimals.insets = new Insets(0, 0, 5, 0);
		gbc_btnWriteAnimals.gridx = 0;
		gbc_btnWriteAnimals.gridy = 6;
		panel_1.add(btnWriteAnimals, gbc_btnWriteAnimals);
		
		//writes animals to file grouped in available and sold, and sorted by selling date and the latter by arrival date
		btnWriteAnimals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addAnimal addAnimal = new addAnimal();
					addAnimal.sortAnimals(pet);
					//writeAnimal(pet);
					writeAnimals writeAnimals = new writeAnimals();
					writeAnimals.writeAnimal(pet);
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		JButton btnSearch = new JButton("Search");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 7;
		panel_1.add(btnSearch, gbc_btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search search = new search();
				search.showSearch();
			}
		});
		
		JButton btnComputeRevenue = new JButton("Compute Revenue");
		GridBagConstraints gbc_btnComputeRevenue = new GridBagConstraints();
		gbc_btnComputeRevenue.insets = new Insets(0, 0, 5, 0);
		gbc_btnComputeRevenue.gridx = 0;
		gbc_btnComputeRevenue.gridy = 8;
		panel_1.add(btnComputeRevenue, gbc_btnComputeRevenue);
		
		//opens a new dialog box where user can see how much revenue is made for selected day/month
		btnComputeRevenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				computeRevenue computeRevenue = new computeRevenue();
				computeRevenue.showComputeRevenue();
				
			}
			
		});
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		//creates a blank table on opening the application 
		createTable(pet, table);
		
	}

	//function to create a table with animal data from ArrayList
	public void createTable(ArrayList<animals> pet, JTable table) {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {
		},
				new Object[] {"Name", "Common Name", "Price", "Sex", "Main Colour", "Arrival Date", "Selling Date"
		});	
		for(animals animals : pet) {
			model.addRow(new Object[] {animals.getGivenName(), animals.getCommonName(), animals.getPrice(), animals.getSex(), animals.getMainColour(), animals.getArrivalDate(), animals.getSellingDate()});
			
		}
		table.setModel(model);
	}
	
	//adds a current date for selling data for an animal if the user clicks sell animal button after selecting an animal row in table
	//else dialog box pops up saying the animal is already sold
	private void sellAnimal(JTable table) {
		//checks whether table row is selected, if not, a dialog box asking to select an animal shows up
		if(table.getSelectedRow()!=-1) {
			//checks whether selling date row is empty, if so, adds current date to selling date for selected animal
			//else dialog box comes up saying the animal is already sold
			if((String.valueOf(table.getValueAt(table.getSelectedRow(), 6)).equals(""))) {
				pet.get(table.getSelectedRow()).setSellingDate(dtf.format(currentDay));
				
				createTable(pet, table);
			}
			else {
				animalSold animalSold = new animalSold();
				animalSold.showAnimalSold();
			}
		}
		else {
			selectRow selectRow = new selectRow();
			selectRow.showSelectRow();
		}
	}
	
	//function to decrease price if animal certain amount of time has passed since animals arrival 
	//and still not been sold
	public void discounts() {
		NumberFormat df = new DecimalFormat("#0.00");
		for(animals animal : pet) {
			LocalDate date = LocalDate.parse(animal.getArrivalDate());
			if(date.isBefore( LocalDate.now().minusMonths(4))) {
				
				animal.setPrice(df.format(Double.valueOf(animal.getPrice())*0.2));
			}
			else if(date.isBefore( LocalDate.now().minusMonths(2))) {
				animal.setPrice(df.format(Double.valueOf(animal.getPrice())*0.9));
			}
			else {
				animal.setPrice(df.format(Double.valueOf(animal.getPrice())));
			}
		}
	}	
}
