package fourth.som;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.encog.mathutil.rbf.RBFEnum;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.som.SOM;
import org.encog.neural.som.training.basic.BasicTrainSOM;
import org.encog.neural.som.training.basic.neighborhood.NeighborhoodRBF;
import org.encog.util.arrayutil.NormalizationAction;
import org.encog.util.arrayutil.NormalizedField;

/**
 * A classic SOM example that shows how the SOM groups similar color shades.
 *
 */
public class SomColors extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6762179069967224817L;
	private MapPanel map;
	private SOM network;
	private Thread thread;
	private BasicTrainSOM train;
	private NeighborhoodRBF gaussian;

	public SomColors() {
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.network = createNetwork();
		this.getContentPane().add(map = new MapPanel(this));
		this.gaussian = new NeighborhoodRBF(RBFEnum.Gaussian,MapPanel.WIDTH,
				MapPanel.HEIGHT);
		this.train = new BasicTrainSOM(this.network, 0.01, null, gaussian);
		train.setForceWinner(false);
		this.thread = new Thread(this);
		thread.start();
	}

	public SOM getNetwork() {
		return this.network;
	}

	private SOM createNetwork() {
		SOM result = new SOM(3,MapPanel.WIDTH * MapPanel.HEIGHT);
		result.reset();
		return result;
	}

	public static void main(String[] args) {
		SomColors frame = new SomColors();
		frame.setVisible(true);
	}

	public void run() {
		
		File file = new File("images/man/man.jpg");
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Normalize values with an actual range of (0 to 100) to (-1 to 1)
		NormalizedField norm = new NormalizedField(NormalizationAction.Normalize, null,255,0,1,-1);
		
		int[] pixel;
		List<MLData> samples = new ArrayList<MLData>();
		for (int y = 0; y < bi.getHeight(); y++) {
		    for (int x = 0; x < bi.getWidth(); x++) {
		        pixel = bi.getRaster().getPixel(x, y, new int[3]);
		        
		        MLData data = new BasicMLData(3);
				data.setData(0, norm.normalize(pixel[0]));
				data.setData(1, norm.normalize(pixel[1]));
				data.setData(2, norm.normalize(pixel[2]));
				samples.add(data);
		    }
		}
		
		
	
		this.train.setAutoDecay(10000, 0.8, 0.003, 30, 5);

		for (int i = 0; i < samples.size()/*100000*/; i++) {
			//int idx = (int) (Math.random() * samples.size());
			MLData c = samples.get(i);

			this.train.trainPattern(c);
			this.train.autoDecay();
			this.map.repaint();
			System.out.println("Iteration " + i + "," + this.train.toString());
		}
		
		 BufferedImage newImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		 for (int i = 0; i < newImage.getWidth(); i++) {
             for (int j = 0; j < newImage.getHeight(); j++) {
            	 pixel = bi.getRaster().getPixel(i, j, new int[3]);
 		         MLData data = new BasicMLData(3);
 				 data.setData(0, norm.normalize(pixel[0]));
 				 data.setData(1, norm.normalize(pixel[1]));
 				 data.setData(2, norm.normalize(pixel[2]));
            	 
 				 int winnerIndex = network.classify(data);
            	 int red = convertColor(network.getWeights().get(winnerIndex,0));
 				 int green = convertColor(network.getWeights().get(winnerIndex,1));
 				 int blue = convertColor(network.getWeights().get(winnerIndex,2));
            	 
                 newImage.setRGB(i, j, new Color(red, green, blue).getRGB());
                 
                 System.out.println("Pixel ("+i+","+j+") color: "+pixel[0]+","+pixel[1]+","+pixel[2]+" Winner color: "+red+","+green+","+blue);
             }
         }
		 
		 File outputfile = new File("images/man/manReduced.jpg");
		 try {
			ImageIO.write(newImage, "jpg", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int convertColor(double d)
	{
		//System.out.println(d);
		double result = 128*d;
		result+=128;
		result = Math.min(result, 255);
		result = Math.max(result, 0);
		return (int)result;
	}
}
