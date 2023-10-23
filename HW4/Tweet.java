public class Tweet{
    private double[] location;
    private String text;

    public Tweet(double[] location, String text) {
		this.location = location;
		this.text = text;
	}

    public double[] getLocation() {
		return location;
	}

    public String getText() {
		return text;
	}
}