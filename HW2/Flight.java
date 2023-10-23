public class Flight {
	protected int year; 
	protected int month; 
	protected int day;
	protected int flightNum; 
	protected int depDelay; 
	protected int arrDelay;
	protected String oriAirport;
	protected String desAirport;
	
	public Flight(int year, int month, int day, int flightNum, int depDelay, int arrDelay, String oriAirport, String desAirport) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.flightNum = flightNum;
		this.depDelay = depDelay;
		this.arrDelay = arrDelay;
		this.oriAirport = oriAirport;
		this.desAirport = desAirport;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getFlightNum() {
		return flightNum;
	}
	
	public int getDepDelay() {
		return depDelay;
	}
	
	public int getArrDelay() {
		return arrDelay;
	}
	
	public String getOriAirport() {
		return oriAirport;
	}
	
	public String getDesAirport() {
		return desAirport;
	}
	
	public String getDate() {
		return String.valueOf(month + "/" + day + "/" + year);
	}
	
	public String toString() {
		return String.valueOf("Flight" + flightNum + "on " + month + "/" + day + "/" + year + " had a delay of " + depDelay + " leaving " + oriAirport + " and a delay of " + arrDelay + " arriving at " + desAirport);
	}
}
