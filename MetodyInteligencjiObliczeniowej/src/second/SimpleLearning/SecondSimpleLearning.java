package second.SimpleLearning;

import java.util.Arrays;

import first.perceptron.Perceptron;
import second.model.SecondModel;

public class SecondSimpleLearning {
	
	private static double errorThreshold = 0.01;
	
	private Perceptron[] perceptrons;
	private SecondModel[] models;
	
	public SecondSimpleLearning(Perceptron[] perceptrons, SecondModel[] models) {
		this.models=models;
		this.perceptrons=perceptrons;
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
						System.out.println("Learning perceptron "+i);
						System.out.println(currentModel.getName() + " ex-result:" + currentModel.getExpectedResults()[i]);
						System.out.println(Arrays.toString(perceptron.getWeights()));

						double result = perceptron.process(currentModel.getFeatures());

						System.out.println("result: " + result + "\n");

						currentLearned = Math.abs(currentModel.getExpectedResults()[i] - result) <= errorThreshold;
						if (!currentLearned) 
						{
							changeWeights(currentModel.getFeatures(), perceptron.getWeights(), currentModel.getExpectedResults()[i]-result);
							//perceptron.saveWeights();
							weightsChanged = true;
						}
					}
				}
				perceptronLearned = !weightsChanged;
			}
		}
	
	}

	private void changeWeights(double[] features,double[] perceptronWeights, double changeFactor) {
		for (int i=0;i<perceptronWeights.length;i++){
			perceptronWeights[i] = perceptronWeights[i] + changeFactor*features[i];
		}
	}
}
