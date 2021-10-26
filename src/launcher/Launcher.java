package launcher;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import construct.ImageHolderSpectrum;
import construct.SliderListener;
import permutationsearch.ArrayUtils;
import permutationsearch.DepthRangeTable;
import permutationsearch.PermutationTree;
import permutationsearch.Range;
import permutationsearch.SearchKey;
import utils.ImageUtils;

public class Launcher {

	public static void main(String[] args) {
		//createSearch(); //problem with tree building
		//createSearch2();
		//ImageTest();
		createSearch3();
		//createSearch4();
	}
	
	public static void createSearch() {
		ArrayList<Color> spectrum = new ArrayList<Color>();
		spectrum.add(new Color(0, 0, 0));
		spectrum.add(new Color(51, 51, 51));
		spectrum.add(new Color(102, 102, 102));
		spectrum.add(new Color(153, 153, 153));
		spectrum.add(new Color(204, 204, 204));
		spectrum.add(new Color(238,238,238));
		
		int width = 16;
		int height = 16;
		int depth = spectrum.size();
		int scale = 12;
		
		int windowWidth = 400;
		int windowHeight = 400;
		
		JFrame frame = new JFrame();
		frame.setTitle("VoidImageSearcher");
		frame.setSize(windowWidth, windowHeight);
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel masterPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(masterPanel, BoxLayout.Y_AXIS);
		masterPanel.setLayout(boxlayout);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setSize(width*scale, height*scale);
		masterPanel.add(panel);
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, Integer.MAX_VALUE, 0);
		slider.setSize(windowWidth, 40);
		masterPanel.add(slider);
		
		JSlider slider2 = new JSlider(JSlider.HORIZONTAL, 1, 100, 1);
		slider2.setSize(windowWidth, 40);
		masterPanel.add(slider2);
		
		frame.add(masterPanel);
		
		SliderListener listener = new SliderListener(slider);
		slider.addChangeListener(listener);
		
		SliderListener listener2 = new SliderListener(slider2);
		slider2.addChangeListener(listener2);
		
		ArrayList<SearchKey> search = new ArrayList<SearchKey>();
		search.add(new SearchKey(32, depth-1));
		search.add(new SearchKey(32*2, depth-1));
		search.add(new SearchKey(32*3, depth-1));
		search.add(new SearchKey(32*4, depth-1));
		//search.add(new SearchKey(38, 1));
		
		System.out.println("Beginning tree construction!");
		long start = System.currentTimeMillis();
		PermutationTree tree = new PermutationTree(depth, width*height, search);
		//System.out.println("Total Ranges:" + tree.getIndexesAvaliable());
		long end = System.currentTimeMillis();
		System.out.println("Constructing the tree took " + ((end-start)/1000) + " seconds to complete!");
		ImageHolderSpectrum imageHolder = new ImageHolderSpectrum(tree, new BigInteger(""+listener.getPos()), width, height, spectrum);
		
		int lastPos = -1;
		int lastPower = -1;
		BufferedImage solution = null;
		while(true) {
			int currentPos = listener.getPos();
			int currentPower = listener2.getPos();
			if(currentPos != lastPos || currentPower != lastPower) {
				Range r = tree.getIndex(new BigInteger(""+listener.getPos()).pow(listener2.getPos()));
				if(r!=null) {
					BigInteger imagePosition = r.getLowerBound();
					imageHolder.setPos(imagePosition);
					solution = imageHolder.getImage();
					lastPos = currentPos;
					lastPower = currentPower;
				}
			}
			Graphics2D g2d = (Graphics2D) panel.getGraphics();
			g2d.drawImage(solution, (panel.getWidth()/2)-((width*scale)/2),(panel.getHeight()/2)-((height*scale)/2), (solution.getWidth()*scale), solution.getHeight()*scale, null);
		}
	}
	
	public static void createSearch2() {
		ArrayList<Color> spectrum = new ArrayList<Color>();
		spectrum.add(new Color(0, 0, 0));
		spectrum.add(new Color(51, 51, 51));
		spectrum.add(new Color(102, 102, 102));
		spectrum.add(new Color(153, 153, 153));
		spectrum.add(new Color(204, 204, 204));
		spectrum.add(new Color(238,238,238));

		int width = 32;
		int height = 32;
		int depth = spectrum.size();
		int scale = 8;
		
		int windowWidth = 400;
		int windowHeight = 400;
		
		JFrame frame = new JFrame();
		frame.setTitle("VoidImageSearcher");
		frame.setSize(windowWidth, windowHeight);
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel masterPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(masterPanel, BoxLayout.Y_AXIS);
		masterPanel.setLayout(boxlayout);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setSize(width*scale, height*scale);
		masterPanel.add(panel);
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, Integer.MAX_VALUE, 0);
		slider.setSize(windowWidth, 40);
		masterPanel.add(slider);
		
		JSlider slider2 = new JSlider(JSlider.HORIZONTAL, 1, 1000, 1);
		slider2.setSize(windowWidth, 40);
		masterPanel.add(slider2);
		
		frame.add(masterPanel);
		
		SliderListener listener = new SliderListener(slider);
		slider.addChangeListener(listener);
		
		SliderListener listener2 = new SliderListener(slider2);
		slider2.addChangeListener(listener2);
		
		ArrayList<SearchKey> search = new ArrayList<SearchKey>();
		for(int i = 1; i <= width*height; i+=2) {
			search.add(new SearchKey(i, depth-1));
		}
		System.out.println(search.size());
		
		System.out.println("Beginning tree construction!");
		long start = System.currentTimeMillis();
		PermutationTree tree = new PermutationTree(depth, width*height, search);
		System.out.println("Total Ranges:" + tree.getIndexesAvaliable());
		long end = System.currentTimeMillis();
		System.out.println("Constructing the tree took " + ((end-start)/1000) + " seconds to complete!");
		ImageHolderSpectrum imageHolder = new ImageHolderSpectrum(tree, new BigInteger(""+listener.getPos()), width, height, spectrum);
		
		int lastPos = -1;
		int lastPower = -1;
		BufferedImage solution = null;
		while(true) {
			int currentPos = listener.getPos();
			int currentPower = listener2.getPos();
			if(currentPos != lastPos || currentPower != lastPower) {
				System.out.println("Getting:"+new BigInteger(""+listener.getPos()).pow(listener2.getPos()));
				Range r = tree.getIndex(new BigInteger(""+listener.getPos()).pow(listener2.getPos()));
				if(r!=null) {
					BigInteger imagePosition = r.getLowerBound();
					imageHolder.setPos(imagePosition);
					solution = imageHolder.getImage();
					lastPos = currentPos;
					lastPower = currentPower;
				}
			}
			Graphics2D g2d = (Graphics2D) panel.getGraphics();
			g2d.drawImage(solution, (panel.getWidth()/2)-((width*scale)/2),(panel.getHeight()/2)-((height*scale)/2), (solution.getWidth()*scale), solution.getHeight()*scale, null);
		}
	}
	
	public static void ImageTest() {
		ArrayList<Color> spectrum = new ArrayList<Color>();
		spectrum.add(new Color(0, 0, 0));
		spectrum.add(new Color(51, 51, 51));
		spectrum.add(new Color(102, 102, 102));
		spectrum.add(new Color(153, 153, 153));
		spectrum.add(new Color(204, 204, 204));
		spectrum.add(new Color(238,238,238));

		BufferedImage image = ImageUtils.loadImage();
		image = ImageUtils.convertImageToColorSpectrum(spectrum, image);
		ImageUtils.saveImage(image);
		
	}

	public static void createSearch3() {
		BufferedImage imageToSearch = ImageUtils.loadImage();
		ArrayList<Color> spectrum = new ArrayList<Color>();
		spectrum.add(new Color(0, 0, 0));
		spectrum.add(new Color(51, 51, 51));
		spectrum.add(new Color(102, 102, 102));
		spectrum.add(new Color(153, 153, 153));
		spectrum.add(new Color(204, 204, 204));
		spectrum.add(new Color(238,238,238));

		int width = imageToSearch.getWidth();
		int height = imageToSearch.getHeight();
		int depth = spectrum.size();
		int scale = 8;
		
		int windowWidth = 400;
		int windowHeight = 400;
		
		JFrame frame = new JFrame();
		frame.setTitle("VoidImageSearcher");
		frame.setSize(windowWidth, windowHeight);
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel masterPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(masterPanel, BoxLayout.Y_AXIS);
		masterPanel.setLayout(boxlayout);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setSize(width*scale, height*scale);
		masterPanel.add(panel);
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, Integer.MAX_VALUE, 0);
		slider.setSize(windowWidth, 40);
		masterPanel.add(slider);
		
		JSlider slider2 = new JSlider(JSlider.HORIZONTAL, 1, 1000, 1);
		slider2.setSize(windowWidth, 40);
		masterPanel.add(slider2);
		
		frame.add(masterPanel);
		
		SliderListener listener = new SliderListener(slider);
		slider.addChangeListener(listener);
		
		SliderListener listener2 = new SliderListener(slider2);
		slider2.addChangeListener(listener2);
		
		ArrayList<SearchKey> search = ImageUtils.convertImageSearchPositions(spectrum, imageToSearch);
		System.out.println("Searching "+search.size()+" out of "+(width*height)+" positions");
		
		System.out.println("Beginning tree construction!");
		long start = System.currentTimeMillis();
		PermutationTree tree = new PermutationTree(depth, width*height, search);
		System.out.println("Total Ranges:" + tree.getIndexesAvaliable());
		long end = System.currentTimeMillis();
		System.out.println("Constructing the tree took " + ((end-start)/1000) + " seconds to complete!");
		ImageHolderSpectrum imageHolder = new ImageHolderSpectrum(tree, new BigInteger(""+listener.getPos()), width, height, spectrum);
		
		int lastPos = -1;
		int lastPower = -1;
		BufferedImage solution = null;
		while(true) {
			int currentPos = listener.getPos();
			int currentPower = listener2.getPos();
			if(currentPos != lastPos || currentPower != lastPower) {
				System.out.println("Getting:"+new BigInteger(""+listener.getPos()).pow(listener2.getPos()));
				Range r = tree.getIndex(new BigInteger(""+listener.getPos()).pow(listener2.getPos()));
				if(r!=null) {
					BigInteger imagePosition = r.getLowerBound();
					imageHolder.setPos(imagePosition);
					solution = imageHolder.getImage();
					lastPos = currentPos;
					lastPower = currentPower;
				}
			}
			Graphics2D g2d = (Graphics2D) panel.getGraphics();
			g2d.drawImage(solution, (panel.getWidth()/2)-((width*scale)/2),(panel.getHeight()/2)-((height*scale)/2), (solution.getWidth()*scale), solution.getHeight()*scale, null);
		}
	}
	
	public static void createSearch4() {
		ArrayList<Color> spectrum = new ArrayList<Color>();
		spectrum.add(new Color(0, 0, 0));
		spectrum.add(new Color(51, 51, 51));
		spectrum.add(new Color(102, 102, 102));
		spectrum.add(new Color(153, 153, 153));
		spectrum.add(new Color(204, 204, 204));
		spectrum.add(new Color(238,238,238));

		int width = 32;
		int height = 32;
		int depth = spectrum.size();
		int scale = 8;
		
		int windowWidth = 400;
		int windowHeight = 400;
		
		JFrame frame = new JFrame();
		frame.setTitle("VoidImageSearcher");
		frame.setSize(windowWidth, windowHeight);
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel masterPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(masterPanel, BoxLayout.Y_AXIS);
		masterPanel.setLayout(boxlayout);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setSize(width*scale, height*scale);
		masterPanel.add(panel);
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, Integer.MAX_VALUE, 0);
		slider.setSize(windowWidth, 40);
		masterPanel.add(slider);
		
		JSlider slider2 = new JSlider(JSlider.HORIZONTAL, 1, 1000, 1);
		slider2.setSize(windowWidth, 40);
		masterPanel.add(slider2);
		
		frame.add(masterPanel);
		
		SliderListener listener = new SliderListener(slider);
		slider.addChangeListener(listener);
		
		SliderListener listener2 = new SliderListener(slider2);
		slider2.addChangeListener(listener2);
		
		ArrayList<SearchKey> search = new ArrayList<SearchKey>();
		for(int i = 1; i <= width*height; i+=2) {
			search.add(new SearchKey(i, depth-1));
		}
		System.out.println(search.size());
		
		System.out.println("Beginning tree construction!");
		long start = System.currentTimeMillis();
		PermutationTree tree = new PermutationTree(depth, width*height, search);
		System.out.println("Total Ranges:" + tree.getIndexesAvaliable());
		long end = System.currentTimeMillis();
		System.out.println("Constructing the tree took " + ((end-start)/1000) + " seconds to complete!");
		ImageHolderSpectrum imageHolder = new ImageHolderSpectrum(tree, new BigInteger(""+listener.getPos()), width, height, spectrum);
		
		int lastPos = -1;
		int lastPower = -1;
		BufferedImage solution = null;
		while(true) {
			int currentPos = listener.getPos();
			int currentPower = listener2.getPos();
			if(currentPos != lastPos || currentPower != lastPower) {
				System.out.println("Getting:"+new BigInteger(""+listener.getPos()).pow(listener2.getPos()));
				Range r = tree.getIndex(new BigInteger(""+listener.getPos()).pow(listener2.getPos()));
				if(r!=null) {
					BigInteger imagePosition = r.getLowerBound();
					imageHolder.setPos(imagePosition);
					solution = imageHolder.getImage();
					lastPos = currentPos;
					lastPower = currentPower;
				}
			}
			Graphics2D g2d = (Graphics2D) panel.getGraphics();
			g2d.drawImage(solution, (panel.getWidth()/2)-((width*scale)/2),(panel.getHeight()/2)-((height*scale)/2), (solution.getWidth()*scale), solution.getHeight()*scale, null);
		}
	}
}
