package first.main;

import first.dataPreparator.FirstDataPreparator;
import first.model.Model;
import first.perceptron.Perceptron;
import first.simpleLearning.SimpleLearning;

public class PerceptronSimpleLearningMain {

	public static void main(String[] args) {
		Perceptron perceptron = new Perceptron();

		Model[] models = FirstDataPreparator.prepareLearningData();

		double[] initialWeights = { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };

		perceptron.setWeights(initialWeights);
		perceptron.saveWeights();

		SimpleLearning simpleLearning = new SimpleLearning(perceptron, models);
		simpleLearning.learn();
		perceptron.saveLearningWeightsToFile();

		System.out.println("End");
	}
}
