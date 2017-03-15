package first.main;

import first.perceptron.Perceptron;

public class FinalResultsTestMain {

	public static void main(String[] args) {
		double[] finalResults = {-17380.0, 0.0, 10.0, 2713.0, 1215.0, 2276.0, -8280.0, 753.0, 855.0, 231.0};
		
		Perceptron perceptron = new Perceptron();
		perceptron.setWeights(finalResults);
		//czy planeta jest ziemiopodobna - czy mo¿e istnieæ na niej ¿ycie
		
		double[] inputsEarth =  {60, //masa x10^23kg
				 			  6, //promien x10^3km
				 			  1, //czy skalista
				 			  331, //temp maksymalna K
				 			  185, //temp min K
				 			  288, //temo srednia K
				 			  101, //cisnienie srednie kPa
				 			  149, //odleglosc od gwiazdy x10^6
				 			  78, //% azotu
				 			  21};//% tlenu
		
		double[] inputsMars =  { 6, 
							  3, 
							  1, 
							  293, 
							  133,
							  210,
							  1,
							  228,
							  3,
							  0}; 
		
		double[] inputsVenus =  {40, 
							  6, 
							  1, 
							  773, 
							  710,
							  740,
							  9321,
							  108,
							  3,
							  0}; 

		
		double[] inputsJupiter = {18000, 
							   60, 
							   0, 
							   155, 
							   110,
							   152,
							   70,
							   778,
							   0,
							   0};

		
		System.out.println("expected: 1, actual:"+perceptron.process(inputsEarth));
		System.out.println("expected: 1, actual:"+perceptron.process(inputsMars));
		System.out.println("expected: 0, actual:"+perceptron.process(inputsVenus));
		System.out.println("expected: 0, actual:"+perceptron.process(inputsJupiter));
		
	}

}
