package petshop;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class writeAnimals {

	//function which writes all animals from array list to file 
		public void writeAnimal(ArrayList<animals> pet) throws IOException {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
			try {
				for(animals animal : pet) {
					if(animal.getSellingDate()=="") {
						writer.write(animal.getGivenName() + ", " + animal.getCommonName() + ", " + animal.getPrice()+", "+animal.getMainColour()+", "+animal.getArrivalDate());
					}else {
						writer.write(animal.getGivenName() + ", " + animal.getCommonName() + ", " + animal.getPrice()+", "+animal.getMainColour()+", "+animal.getArrivalDate()+", "+ animal.getSellingDate());
					}
					writer.newLine();
					
				}
				//shows dialog box if animal file has successfully been created 
				animalWritten animalWritten = new animalWritten();
				animalWritten.showAnimalWritten();
				
			}finally{
				if(writer!=null) {
					try {
						writer.close();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			
		}
}
