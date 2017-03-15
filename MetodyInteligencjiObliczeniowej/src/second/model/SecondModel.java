package second.model;

public class SecondModel {

	private String name;
	private double[] expectedResults;
	private double[] features;
	
	public SecondModel(String name, double[] expectedResults, double[] features) {
		this.name = name;
		this.expectedResults = expectedResults;
		this.features = features;
	}

	public double[] getExpectedResults() {
		return expectedResults;
	}

	public void setExpectedResults(double[] expectedResults) {
		this.expectedResults = expectedResults;
	}

	public double[] getFeatures() {
		return features;
	}

	public void setFeatures(double[] features) {
		this.features = features;
	}

	public String getName() {
		return name;
	}
}
