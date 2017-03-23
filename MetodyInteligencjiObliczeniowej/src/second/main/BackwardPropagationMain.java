package second.main;

import first.perceptron.Perceptron;
import second.backwardPropagation.BackwardPropagationLearning;
import second.dataPreparator.SecondDataPreparator;
import second.model.SecondModel;

public class BackwardPropagationMain 
{
	public static void main(String[] args) 
	{
		Perceptron perceptron1 = new Perceptron();
		double[] initialWeights1 = { 0.3, 0.7};
		perceptron1.setWeights(initialWeights1);
		
		Perceptron perceptron2 = new Perceptron();
		double[] initialWeights2 = { 0.5, 0.5};
		perceptron2.setWeights(initialWeights2);
		
		Perceptron perceptron3 = new Perceptron();
		double[] initialWeights3 = { 0.3, 0.2};
		perceptron3.setWeights(initialWeights3);
		
		Perceptron[] firstLayer = {perceptron1,perceptron2,perceptron3};
		
		Perceptron perceptron4 = new Perceptron();
		double[] initialWeights4 = { 0.2, 0.5, 0.7};
		perceptron4.setWeights(initialWeights4);
		
		Perceptron perceptron5 = new Perceptron();
		double[] initialWeights5 = { 0.5, 0.4, 0.12};
		perceptron5.setWeights(initialWeights5);
		
		Perceptron perceptron6 = new Perceptron();
		double[] initialWeights6 = { 0.1, 0.3, 0.8};
		perceptron6.setWeights(initialWeights6);
		
		Perceptron[] secondLayer = {perceptron4,perceptron5,perceptron6};
		
		SecondModel[] models = SecondDataPreparator.prepareLearningData();
		
		BackwardPropagationLearning backward = new BackwardPropagationLearning(firstLayer, secondLayer, models);
		backward.learn();
		
	}
}
