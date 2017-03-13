package perceptron;

import model.Model;
import simpleLearning.SimpleLearning;

public class PerceptronMain {

	public static void main(String[] args) {
		Perceprton perceptron = new Perceprton();
		//czy planeta jest ziemiopodobna - czy mo¿e istnieæ na niej ¿ycie
		
		int[] inputsEarth =  {60, //masa x10^23kg
				 			  6, //promien x10^3km
				 			  1, //czy skalista
				 			  331, //temp maksymalna K
				 			  185, //temp min K
				 			  288, //temo srednia K
				 			  101, //cisnienie srednie kPa
				 			  149, //odleglosc od gwiazdy x10^6
				 			  78, //% azotu
				 			  21};//% tlenu
		Model earth = new Model("Earth",1,inputsEarth);
		
		int[] inputsMars =  { 6, 
							  3, 
							  1, 
							  293, 
							  133,
							  210,
							  1,
							  228,
							  3,
							  0}; 
		Model mars = new Model("Mars",1,inputsMars);
		
		int[] inputsVenus =  {40, 
							  6, 
							  1, 
							  773, 
							  710,
							  740,
							  9321,
							  108,
							  3,
							  0}; 
		Model venus = new Model("Venus",0,inputsVenus);
		
		int[] inputsJupiter = {18000, 
							   60, 
							   0, 
							   155, 
							   110,
							   152,
							   70,
							   778,
							   0,
							   0};
		Model jupiter = new Model("Jupiter",0,inputsJupiter);
		
		double[] weights = {1.0, 
							1.0, 
							1.0, 
							1.0, 
							1.0,
							1.0,
							1.0,
							1.0,
							1.0,
							1.0};
		
		perceptron.setWeights(weights);
		perceptron.saveWeights();
		Model[] models = {earth,mars,venus,jupiter};
		
		SimpleLearning simpleLearning = new SimpleLearning(perceptron,models);
		simpleLearning.learn();
		perceptron.saveLearningWeightsToFile();
		
		System.out.println("End");
	}
}
