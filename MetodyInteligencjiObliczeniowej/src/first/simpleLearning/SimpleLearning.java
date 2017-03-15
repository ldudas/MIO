package first.simpleLearning;

import java.util.Arrays;

import first.model.Model;
import first.perceptron.Perceptron;

public class SimpleLearning {
	
	private static double errorThreshold = 0.01;
	
	private Perceptron perceptron;
	private Model[] models;
	
	public SimpleLearning(Perceptron perceptron, Model[] models) {
		this.perceptron=perceptron;
		this.models=models;
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
					double result = perceptron.process(currentModel.getFeatures());
					
					System.out.println("result: "+result+"\n");
					
					currentLearned =  Math.abs(currentModel.getExpectedResult()-result)<=errorThreshold;
					if(!currentLearned){
						changeWeights(currentModel.getFeatures(), perceptron.getWeights(), currentModel.getExpectedResult()-result);
						weightsChanged = true;
					}
				}
			}
			learned = !weightsChanged;
		}
	}
	
	private void changeWeights(double[] features,double[] perceptronWeights, double changeFactor) {
		for (int i=0;i<perceptronWeights.length;i++){
			perceptronWeights[i] = perceptronWeights[i] + changeFactor*features[i];
		}
		perceptron.saveWeights();
	}
}
