package second.model;

public class SecondModel {

	private String name;
	private int[] expectedResults;
	private int[] features;
	
	public SecondModel(String name, int[] expectedResults, int[] inputsEarth) {
		this.name = name;
		this.expectedResults = expectedResults;
		this.features = inputsEarth;
	}
	
	public int[] getExpectedResults() {
		return expectedResults;
	}

	public void setExpectedResults(int[] expectedResults) {
		this.expectedResults = expectedResults;
	}

	public int[] getFeatures() {
		return features;
	}

	public void setFeatures(int[] features) {
		this.features = features;
	}

	public String getName() {
		return name;
	}
}
