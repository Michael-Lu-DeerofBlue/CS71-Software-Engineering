
public class FlightCounter {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Please specify a date");
			return;
		}
		Flight[] flights = FlightReader.readFlights("phl-flights.txt");
		int counter = 0;
		for (Flight flight : flights) {
			if (args[0].equals(flight.getDate())) {
				counter++;
			}
		}
		System.out.println("Number of flights on " + args[0] + ": " + counter);
	}
	
}
