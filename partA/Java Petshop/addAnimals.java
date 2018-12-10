package petshop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;

public class addAnimals {
	File selectedFile = null;
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private LocalDateTime currentDay = LocalDateTime.now();
	
	//function which allows user to choose an animal file which is then stored into an array list
	public void adddingAnimals() {
		JFileChooser jfc = new JFileChooser();
		int returnValue = jfc.showOpenDialog(null);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			try {
				BufferedReader br = new BufferedReader(new FileReader(selectedFile));
				
				Object[]tableLines = br.lines().toArray();
				String[] animalArray = new String[7];
				//checks whether an array is of length 7, if not, it adds empty objects until length is 7,
				//if arrival date is empty, current date is inserted 
				for(int i=0; i<tableLines.length; i++) {
					String line = tableLines[i].toString().trim();
					String[] dataRow = line.split(",");
					for(int j=0; j<7; j++) {
						if(j<dataRow.length) {
							animalArray[j]=dataRow[j];
						}else {
							animalArray[j]="";
							if(animalArray[5]=="") {
								animalArray[5]=dtf.format(currentDay);
							}
						}
					}
					//adds the arrays in an array list
					petshop.pet.add(new animals(animalArray));
				} 		
				br.close();
			}
			catch(Exception ex) {
					
			}
		}
	}
}
