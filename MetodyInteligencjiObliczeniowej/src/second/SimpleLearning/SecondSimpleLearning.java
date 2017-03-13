package second.SimpleLearning;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import first.perceptron.Perceptron;
import second.model.SecondModel;

public class SecondSimpleLearning {

	private static Random random = new Random();
	private static double weigthChangePercent = 0.5;
	private static int weightsToChange = 1;
	
	private Perceptron[] perceptrons;
	private SecondModel[] models;
	
	private Set<Integer> weightsToChangeIndexes;
	
	public SecondSimpleLearning(Perceptron[] perceptrons, SecondModel[] models) {
		this.models=models;
		this.perceptrons=perceptrons;
		weightsToChangeIndexes = new HashSet<>();
	}
	
	public void learn() {

		for (int i = 0; i < perceptrons.length; i++) 
		{
			boolean perceptronLearned = false;
			while (!perceptronLearned) 
			{
				Perceptron perceptron = perceptrons[i];
				boolean weightsChanged = false;
				for (SecondModel currentModel : models) 
				{
					boolean currentLearned = false;
					while (!currentLearned) 
					{

						System.out
								.println(currentModel.getName() + " ex-result:" + currentModel.getExpectedResults()[i]);
						System.out.println(Arrays.toString(perceptron.getWeights()));

						int result = perceptron.process(currentModel.getFeatures());

						System.out.println("result: " + result + "\n");

						currentLearned = currentModel.getExpectedResults()[i] == result;
						if (!currentLearned) 
						{
							changePerceptronWeights(perceptron, result, currentModel.getExpectedResults()[i]);
							weightsChanged = true;
						}
					}
				}
				perceptronLearned = !weightsChanged;
			}
		}
	
	}

	private void changePerceptronWeights(Perceptron perceptron, int result, int expectedResult) {
		double[] perceptronWeights = perceptron.getWeights();
		pickWeightsToChange(perceptronWeights.length);
		changeWeights(perceptronWeights,expectedResult-result);
		perceptron.saveWeights();
	}

	private void changeWeights(double[] perceptronWeights, int changeFactor) {
		for(Integer weightIndex: weightsToChangeIndexes){
			perceptronWeights[weightIndex] +=  changeFactor*(perceptronWeights[weightIndex]*weigthChangePercent);
		}
	}

	private void pickWeightsToChange(int perceptronWeightLength) {
		weightsToChangeIndexes.clear();
		while(weightsToChangeIndexes.size()!=weightsToChange){
			weightsToChangeIndexes.add(random.nextInt(perceptronWeightLength));
		}
	}

}
