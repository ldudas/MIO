package first.model;

public class Model {

	private String name;
	private double expectedResult;
	private double[] features;
	
	public Model(String name, int expectedResult, double[] features) {
		this.name = name;
		this.expectedResult = expectedResult;
		this.features = features;
	}

	public double getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(int expectedResult) {
		this.expectedResult = expectedResult;
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
