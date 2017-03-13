package first.model;

public class Model {

	private String name;
	private int expectedResult;
	private int[] features;
	
	public Model(String name, int expectedResult, int[] inputsEarth) {
		this.name = name;
		this.expectedResult = expectedResult;
		this.features = inputsEarth;
	}

	public int getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(int expectedResult) {
		this.expectedResult = expectedResult;
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
