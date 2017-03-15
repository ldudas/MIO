package second.backwardPropagation;


import first.perceptron.Perceptron;
import second.model.SecondModel;

public class BackwardPropagationLearning 
{
	private static double errorThreshold = 0.01;
	
	private Perceptron[] firstLayer;
	private Perceptron[] secondLayer;
	private SecondModel[] models;

	private double [] firstEval = new double[3];
	private double [] secondEval = new double[3];
	
	public BackwardPropagationLearning(Perceptron[] firstLayer, Perceptron[] secondLayer, SecondModel[] models) 
	{
		this.models = models;
		this.firstLayer = firstLayer;
		this.secondLayer = secondLayer;
	}
	
	private void forwardEvaluation(SecondModel model)
	{	
		for(int i = 0 ; i < firstLayer.length; i++)
		{
			firstEval[i] = firstLayer[i].process(model.getFeatures());		
		}
		for(int i = 0 ; i < secondLayer.length; i++)
		{
			secondEval[i] = secondLayer[i].process(firstEval);
		}
	}
	
	private boolean compareResults(double [] expResults)
	{
		boolean compResult = true;
		if(expResults.length == secondEval.length)
		{
			for(int i = 0; i < expResults.length; i++)
			{
				compResult &= Math.abs(expResults[i] - secondEval[i]) <= errorThreshold;
			}
		}
		else
		{
			compResult = false;
		}
		return compResult;
	}
	
	public void learn() 
	{
		boolean networkLearned = false;
		while (!networkLearned) 
		{
			boolean weightsChanged = false;
			for (SecondModel currentModel : models) 
			{
				boolean currentLearned = false;
				while (!currentLearned) 
				{
					forwardEvaluation(currentModel);
					currentLearned = compareResults(currentModel.getExpectedResults());

					if (!currentLearned) 
					{
						changeWeights(currentModel.getFeatures(), evalErrors(currentModel.getExpectedResults()));
						weightsChanged = true;
					}

				}
			}
			networkLearned = !weightsChanged;
		}
	}
	
	private double [] evalErrors(double [] expResults)
	{
		double [] evalErrors = new double [3];
		
		for(int i = 0; i < expResults.length; i++)
		{
			evalErrors[i] = expResults[i] - secondEval[i];
		}
		
		return evalErrors;
	}

	private void changeWeights(double[] features, double [] evalErrors) 
	{
		double [] errorsFirstLayer = new double [3];
		
		for(int i = 0; i < errorsFirstLayer.length; i++)
		{
			for(int j = 0; j < evalErrors.length; j++)
			{
				errorsFirstLayer[i] += evalErrors[j] * secondLayer[j].getWeights()[i];
			}
		}
		
		for (int i = 0; i < firstLayer.length; i++)
		{
			for (int j = 0; j < firstLayer[i].getWeights().length; j++)
			{
				firstLayer[i].getWeights()[j] += errorsFirstLayer[i] * features[j] * 
						Math.exp(firstLayer[i].getWeightedSum()) / Math.pow((Math.exp(firstLayer[i].getWeightedSum()) + 1), 2);
			}
		}
		
		for (int i = 0; i < secondLayer.length; i++)
		{
			for (int j = 0; j < secondLayer[i].getWeights().length; j++)
			{
				secondLayer[i].getWeights()[j] += evalErrors[i] * firstEval[j] * 
						Math.exp(secondLayer[i].getWeightedSum()) / Math.pow((Math.exp(secondLayer[i].getWeightedSum()) + 1), 2);
			}
		}
	}

}
