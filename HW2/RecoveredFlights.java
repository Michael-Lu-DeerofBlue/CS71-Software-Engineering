
public class RecoveredFlights {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Please specify a single airport code");
			return;
		}
		Flight[] flights = FlightReader.readFlights("phl-flights.txt");
		int delayCounter = 0;
		int recoverCounter = 0;
		for (Flight flight : flights) {
			if (args[0].equals(flight.getOriAirport())) {
				if (flight.getDepDelay() > 0) {
					delayCounter++;
					if (flight.getArrDelay() <= 0) {
						recoverCounter++;
					}
				}
			}
		}
		if (delayCounter == 0) {
			System.out.println("There were no delayed flights from "+ args[0]);
			return;
		}
		double precentage = ((double) recoverCounter) / delayCounter * 100.0;
		System.out.printf("%.2f%% of delayed flights from " + args[0] + " recovered", precentage);
	}
}
