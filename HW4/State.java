public class State{
    private double[] location;
    private String name;

    public State(double[] location, String name) {
		this.location = location;
		this.name = name;
	}

    public double[] getLocation() {
		return location;
	}

    public String getName() {
		return name;
	}
}