package fourth.som;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.encog.mathutil.matrices.Matrix;

public class MapPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7528474872067939033L;
	public static final int CELL_SIZE = 15;
	public static final int WIDTH = 25;
	public static final int HEIGHT = 25;

	private Matrix weights;
	
	public MapPanel(SomColors som)
	{
		this.weights = som.getNetwork().getWeights();
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
	
	@Override
	public void paint(Graphics g)
	{
		for(int y = 0; y< HEIGHT; y++)
		{
			for(int x = 0; x< WIDTH; x++)
			{
				int index = (y*WIDTH)+x;
				int red = convertColor(weights.get(index,0));
				int green = convertColor(weights.get(index,1));
				int blue = convertColor(weights.get(index,2));
				g.setColor(new Color(red,green,blue));
				g.fillRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
			}
		}
	}	
}
