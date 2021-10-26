package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import permutationsearch.ArrayUtils;
import permutationsearch.SearchKey;

public class ImageUtils {

	public static BufferedImage convertDataToImage(int[] data, int width, int height) {
		BufferedImage solution = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < solution.getWidth()*solution.getHeight()*3; i+=3) {
			int x = (i/3) % solution.getWidth();
			int y = (i/3) / solution.getHeight();
			Color color = new Color(data[i], data[i+1], data[i+2]);
			solution.setRGB(x, y, color.getRGB());
		}
		return solution;
	}
	
	public static BufferedImage convertDataToImageSpectrum(int[] data, int width, int height, ArrayList<Color> spectrum) {
		BufferedImage solution = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < solution.getWidth()*solution.getHeight(); i++) {
			int x = i % solution.getWidth();
			int y = i / solution.getHeight();
			Color color = spectrum.get(data[i]);
			solution.setRGB(x, y, color.getRGB());
		}
		return solution;
	}
	
	public static ArrayList<Color> sortSpectrum(ArrayList<Color> spectrum) {
		for(int i = 0; i < spectrum.size(); i++) {
			for(int k = i; k < spectrum.size(); k++) {
				Color c1 = spectrum.get(i);
				Color c2 = spectrum.get(k);
				int a1 = (c1.getRed()+c1.getGreen()+c1.getBlue())/3;
				int a2 = (c2.getRed()+c2.getGreen()+c2.getBlue())/3;
				if(a2 < a1) {
					Collections.swap(spectrum, k, i);
				}
			}
		}
		return spectrum;
	}
	
	public static BufferedImage convertImageToColorSpectrum(ArrayList<Color> spectrum, BufferedImage image) {
		spectrum  = ImageUtils.sortSpectrum(spectrum);
		int[] colorAverages = new int[spectrum.size()];
		
		for(int i = 0; i < spectrum.size(); i++) {
			Color color = spectrum.get(i);
			colorAverages[i] = (color.getRed()+color.getGreen()+color.getBlue())/3;
		}
		
		BufferedImage solution = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int x = 0; x < image.getWidth(); x++) {
			for(int y = 0; y < image.getHeight(); y++) {
				int pixel = image.getRGB(x, y);
				int alpha = (pixel >> 24) & 0xff;
			    int red = (pixel >> 16) & 0xff;
			    int green = (pixel >> 8) & 0xff;
			    int blue = (pixel) & 0xff;
			    int average = (red+green+blue)/3;
			    
			    int loc = ArrayUtils.findClosestPosition(colorAverages, average);
			    solution.setRGB(x, y, spectrum.get(loc).getRGB());
			}
		}
		
		return solution;
	}
	
	public static ArrayList<SearchKey> convertImageSearchPositions(ArrayList<Color> spectrum, BufferedImage image) {
		spectrum  = ImageUtils.sortSpectrum(spectrum);
		int[] colorAverages = new int[spectrum.size()];
		
		for(int i = 0; i < spectrum.size(); i++) {
			Color color = spectrum.get(i);
			colorAverages[i] = (color.getRed()+color.getGreen()+color.getBlue())/3;
		}
		
		ArrayList<SearchKey> search = new ArrayList<SearchKey>();
		
		for(int x = 0; x < image.getWidth(); x++) {
			for(int y = 0; y < image.getHeight(); y++) {
				int pixel = image.getRGB(x, y);
				int alpha = (pixel >> 24) & 0xff;
				if(alpha == 255) {
					int red = (pixel >> 16) & 0xff;
			    	int green = (pixel >> 8) & 0xff;
			    	int blue = (pixel) & 0xff;
			    	int average = (red+green+blue)/3;
			    	int value = ArrayUtils.findClosestPosition(colorAverages, average);
			    	
			    	int pos = y*image.getWidth();
			    	pos += x;
			    	search.add(new SearchKey(pos+1, value));
				}
			}
		}
		
		return search;
	}
	
	public static BufferedImage loadImage() {
		JFileChooser jfc = new JFileChooser();
		jfc.showOpenDialog(null);
		try {
			return ImageIO.read(jfc.getSelectedFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void saveImage(BufferedImage image) {
		JFileChooser jfc = new JFileChooser();
		jfc.showSaveDialog(null);
		try {
			File file = new File(jfc.getSelectedFile().getAbsolutePath()+".png");
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
