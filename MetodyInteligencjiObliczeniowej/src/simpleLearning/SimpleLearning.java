package simpleLearning;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import model.Model;
import perceptron.Perceprton;

public class SimpleLearning {

	private static Random random = new Random();
	private static double weigthChangePercent = 0.5;
	private static int weightsToChange = 5;
	
	private Perceprton perceptron;
	private Model[] models;
	
	private Set<Integer> weightsToChangeIndexes;
	
	public SimpleLearning(Perceprton perceptron, Model[] models) {
		this.perceptron=perceptron;
		this.models=models;
		weightsToChangeIndexes = new HashSet<>();
	}
	
	public void learn(){
		boolean learned = false;
		while(!learned){
			boolean weightsChanged = false;
			for(Model currentModel:models){
				boolean currentLearned = false;
				while(!currentLearned){
					
					System.out.println(currentModel.getName()+" ex-result:"+currentModel.getExpectedResult());
					System.out.println(Arrays.toString(perceptron.getWeights()));
					int result = perceptron.process(currentModel.getFeatures());
					
					System.out.println("result: "+result+"\n");
					
					currentLearned =  currentModel.getExpectedResult()==result;
					if(!currentLearned){
						changePerceptronWeights(result,currentModel.getExpectedResult());
						weightsChanged = true;
					}
				}
			}
			learned = !weightsChanged;
		}
	}

	private void changePerceptronWeights(int result, int expectedResult) {
		double[] perceptronWeights = perceptron.getWeights();
		pickWeightsToChange(perceptronWeights);
		changeWeights(perceptronWeights,expectedResult-result);
		perceptron.saveWeights();
	}

	private void changeWeights(double[] perceptronWeights, int changeFactor) {
		for(Integer weightIndex: weightsToChangeIndexes){
			perceptronWeights[weightIndex] = perceptronWeights[weightIndex] + (changeFactor*(perceptronWeights[weightIndex]*weigthChangePercent));
		}
	}

	private void pickWeightsToChange(double[] perceptronWeight) {
		weightsToChangeIndexes.clear();
		while(weightsToChangeIndexes.size()!=weightsToChange){
			weightsToChangeIndexes.add(random.nextInt(perceptronWeight.length));
		}
	}

}
