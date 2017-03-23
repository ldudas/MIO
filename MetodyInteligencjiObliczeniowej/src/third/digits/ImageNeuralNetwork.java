package third.digits;

/*
 * Encog(tm) Java Examples v3.4
 * http://www.heatonresearch.com/encog/
 * https://github.com/encog/encog-java-examples
 *
 * Copyright 2008-2016 Heaton Research, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *   
 * For more information on Heaton Research copyrights, licenses 
 * and trademarks visit:
 * http://www.heatonresearch.com/copyright
 */


import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.encog.Encog;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.scg.ScaledConjugateGradient;
import org.encog.persist.EncogDirectoryPersistence;
import org.encog.platformspecific.j2se.data.image.ImageMLData;
import org.encog.platformspecific.j2se.data.image.ImageMLDataSet;
import org.encog.util.downsample.Downsample;
import org.encog.util.downsample.RGBDownsample;
import org.encog.util.downsample.SimpleIntensityDownsample;
import org.encog.util.simple.EncogUtility;

public class ImageNeuralNetwork {

	private static final int DOWNSAMPLE_HEIGHT = 15;
	private static final int DOWNSAMPLE_WIDTH = 15;
	private static final DownsampleType DOWNSAMPLE_TYPE = DownsampleType.SIMPLE;
    private static final int INPUTS = 2;
    private static final int INPUTS_COPY = 5;
    private static final String PATH_TO_INPUTS = "C:\\Users\\£ukasz\\git\\MIO\\MetodyInteligencjiObliczeniowej\\images\\";
    private static final int HIDDEN_1 = 100;
    private static final int HIDDEN_2 = 0;
    private static final int TRAIN_MINUTES = 1; 
    private static final double STRATEGY_ERROR = 0.25;
    private static final int STRATEGY_CYCLES = 50;
    private static final String NETWORK_FILE_NAME = "network.eg";
    
	private final List<ImagePair> imageList = new ArrayList<ImagePair>();
	private final Map<String, Integer> identity2neuron = new HashMap<String, Integer>();
	private final Map<Integer, String> neuron2identity = new HashMap<Integer, String>();
	private ImageMLDataSet training;
	private int outputCount;
	private BasicNetwork network;
	private Downsample downsample;
	
	private static final boolean TRAIN = false;
	
	public static void main(final String[] args) throws IOException {
		ImageNeuralNetwork inn = new ImageNeuralNetwork();
		
		if(TRAIN){
			inn.createTrainingSet();
			inn.loadInput();
			inn.setUpNetwork();
			inn.trainNetwork();
			inn.saveNetwork();
			inn.recognisePattern();
		}else{
			inn.loadInput();
			inn.loadNetwork();
			inn.createDownsample();
			inn.recognisePattern();
		}

		Encog.getInstance().shutdown();
	}

	
	private void loadNetwork() {
		 network = (BasicNetwork)EncogDirectoryPersistence.loadObject(new File(NETWORK_FILE_NAME));
	}


	private void saveNetwork() {
		EncogDirectoryPersistence.saveObject(new File(NETWORK_FILE_NAME), network);
	}

	private int assignIdentity(final String identity) {
		if (this.identity2neuron.containsKey(identity.toLowerCase())) {
			return this.identity2neuron.get(identity.toLowerCase());
		}

		final int result = this.outputCount;
		this.identity2neuron.put(identity.toLowerCase(), result);
		this.neuron2identity.put(result, identity.toLowerCase());
		this.outputCount++;
		return result;
	}

	private void createDownsample(){
		if (DOWNSAMPLE_TYPE==DownsampleType.RGB) {
			this.downsample = new RGBDownsample();
		} else {
			this.downsample = new SimpleIntensityDownsample();
		}
	}
	
	private void createTrainingSet() {
		createDownsample();

		this.training = new ImageMLDataSet(this.downsample, false, 1, -1);
		System.out.println("Training set created");
	}

	
	private void loadInput() throws IOException {
		 for(int i = 0; i < INPUTS; i++)
	        {
	           for(int j = 0; j < INPUTS_COPY; j++)
	            {
	                String filePath = PATH_TO_INPUTS.concat("\\letters\\").concat("let").concat(Integer.toString(i))
	                                .concat("_").concat(Integer.toString(j)).concat(".jpg");
	                File file = new File(filePath);
	                String identity = Character.toString ((char)('A' + i));
	                imageList.add(new ImagePair(file, assignIdentity(identity)));
	              //  System.out.println("Added input image:" + filePath);
	            }
	            
	            
	        }
	/*
		File folder = new File(PATH_TO_INPUTS+"numbers\\");
	
	    for (File subDir : folder.listFiles())
	    {
	        for (File file : subDir.listFiles())
	        {
	            imageList.add(new ImagePair(file, assignIdentity(subDir.getName())));
	            System.out.println("Added input image:" + file.getPath());
	        }
	    }*/
	}

	private void setUpNetwork() throws IOException {
		System.out.println("Downsampling images...");

		for (final ImagePair pair : this.imageList) {
			final MLData ideal = new BasicMLData(this.outputCount);
			final int idx = pair.getIdentity();
			for (int i = 0; i < this.outputCount; i++) {
				if (i == idx) {
					ideal.setData(i, 1);
				} else {
					ideal.setData(i, -1);
				}
			}

			final Image img = ImageIO.read(pair.getFile());
			final ImageMLData data = new ImageMLData(img);
			this.training.add(data, ideal);
		}

		this.training.downsample(DOWNSAMPLE_HEIGHT, DOWNSAMPLE_WIDTH);

		this.network = EncogUtility.simpleFeedForward(this.training
				.getInputSize(), HIDDEN_1, HIDDEN_2,
				this.training.getIdealSize(), true);
		System.out.println("Created network: " + this.network.toString());
	}

	private void trainNetwork() throws IOException {
		System.out.println("Training Beginning... Output patterns="
				+ this.outputCount);

		/*final Backpropagation train = new Backpropagation(network, training);
	       train.addStrategy(new SmartLearningRate());
	       train.addStrategy(new SmartMomentum());*/
		
		/*final ResilientPropagation train = new ResilientPropagation(this.network, this.training);
		train.addStrategy(new ResetStrategy(STRATEGY_ERROR, STRATEGY_CYCLES));*/
		
	
		ScaledConjugateGradient train = new ScaledConjugateGradient(network, training);

		EncogUtility.trainConsole(train, this.network, this.training,
					TRAIN_MINUTES);
		
		System.out.println("Training Stopped...");
	}

	public void recognisePattern() throws IOException {
		final File file = new File(PATH_TO_INPUTS+"whatIs\\whatIs.jpg");
		final Image img = ImageIO.read(file);
		final ImageMLData input = new ImageMLData(img);
		input.downsample(this.downsample, false, DOWNSAMPLE_HEIGHT,DOWNSAMPLE_WIDTH, 1, -1);
		final int winner = this.network.winner(input);
		System.out.println("there seems to be letter "
				+ this.neuron2identity.get(winner)+" in whatIs.jpg");
	}
}
