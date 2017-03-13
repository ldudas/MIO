package perceptron;

public class FinalResultsTestMain {

	public static void main(String[] args) {
		double[] finalResults = {8.570493224435225E-62, 7.251041270133988E-45, 2.6416891554213732E-24, 1.0468535490100925E-60, 2.3798674107038605E-71, 9.942841435775678E-7, 1.3681752532114325E-70, 0.09552678330589379, 27.828892061101573, 2.661860258153514E-52};
		
		Perceprton perceptron = new Perceprton();
		perceptron.setWeights(finalResults);
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

		
		System.out.println("expected: 1, actual:"+perceptron.process(inputsEarth));
		System.out.println("expected: 1, actual:"+perceptron.process(inputsMars));
		System.out.println("expected: 0, actual:"+perceptron.process(inputsVenus));
		System.out.println("expected: 0, actual:"+perceptron.process(inputsJupiter));
		
	}

}
