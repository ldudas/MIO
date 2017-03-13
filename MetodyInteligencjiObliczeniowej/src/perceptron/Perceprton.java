package perceptron;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Perceprton {

	private static double threshold = 0.5;
	private double[] weights;
	
	private List<List<Double>> learningWeights;
	
	public Perceprton() {
		learningWeights = new ArrayList<>();
	}

	public int process(int[] inputs) {
		double weightedSum = 0.0;
		for(int i=0;i<inputs.length;i++){
			weightedSum += weights[i] * inputs[i];
		}
		double sigmoidalResult = 1.0 / (1.0 + Math.exp(-weightedSum+100));
		System.out.println("ws: "+weightedSum+" sr:"+sigmoidalResult);
		return sigmoidalResult >= threshold ? 1 : 0;
	}

	public void setWeights(double[] weights) {
		this.weights = weights;	
	}

	public double[] getWeights() {
		return weights;
	}

	public void saveWeights() {
		List<Double> weightsToSave = new ArrayList<>();
		for(double weight: weights){
			weightsToSave.add(weight);
		}
		learningWeights.add(weightsToSave);
	}

	public void saveLearningWeightsToFile() {
		try (PrintWriter pw = new PrintWriter("learningWeights.csv")){
			for (List<Double> list : learningWeights) {
				for (Double double1 : list) {
					String doubleString = Double.toString(double1);
					doubleString = doubleString.replace('.', ',');
					pw.print(doubleString+";");
				}
				pw.println();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
