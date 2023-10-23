import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FlightReader {
	
	public static Flight[] readFlights(String filename) {
		Flight[] flights = new Flight[0];
		try (Scanner in = new Scanner(new File(filename))){
			while (in.hasNext()) {
				int numOfFlights = Integer.parseInt(in.next());
				flights = new Flight[numOfFlights];
				for (int i = 0; i < numOfFlights; i++) {
					Flight f = new Flight(Integer.parseInt(in.next()), Integer.parseInt(in.next()), Integer.parseInt(in.next()), Integer.parseInt(in.next()), Integer.parseInt(in.next()), Integer.parseInt(in.next()), 
							in.next(), in.next());
					flights[i] = f;
				}
			}
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found. Please check the file name again.");
		}		
		return flights; 
	}
	
}
