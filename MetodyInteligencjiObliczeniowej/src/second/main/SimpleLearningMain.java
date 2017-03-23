package second.main;

import first.perceptron.Perceptron;
import second.SimpleLearning.SecondSimpleLearning;
import second.dataPreparator.SecondDataPreparator;
import second.model.SecondModel;

public class SimpleLearningMain {
	public static void main(String[] args) {
		Perceptron perceptron1 = new Perceptron();
		double[] initialWeights1 = { 0.5, 0.7};
		perceptron1.setWeights(initialWeights1);
		perceptron1.saveWeights();
		
		Perceptron perceptron2 = new Perceptron();
		double[] initialWeights2 = { 0.3, 0.4};
		perceptron2.setWeights(initialWeights2);
		perceptron2.saveWeights();
		
		Perceptron perceptron3 = new Perceptron();
		double[] initialWeights3 = { 0.1, 0.1};
		perceptron3.setWeights(initialWeights3);
		perceptron3.saveWeights();
		
		Perceptron[] perceptrons = {perceptron1,perceptron2,perceptron3};
		
		SecondModel[] models = SecondDataPreparator.prepareLearningData();
		
		SecondSimpleLearning simpleLearning = new SecondSimpleLearning(perceptrons,models);
		simpleLearning.learn();
		
	}
}
