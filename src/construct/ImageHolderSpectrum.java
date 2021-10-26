package construct;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.util.ArrayList;

import permutationsearch.PermutationSearcher;
import permutationsearch.PermutationTree;
import permutationsearch.PermutationTree;
import utils.ImageUtils;

public class ImageHolderSpectrum {

	private PermutationTree pt;
	private BigInteger pos;
	private BufferedImage image;
	private int width, height;
	private ArrayList<Color> spectrum = new ArrayList<Color>();
	
	public ImageHolderSpectrum(PermutationTree pt, BigInteger pos, int width, int height, ArrayList<Color> spectrum) {
		this.pt = pt;
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.spectrum = spectrum;
		generateImage();
	}

	private void generateImage() {
		int[] data = pt.getData(pos);
		image = ImageUtils.convertDataToImageSpectrum(data, width, height, spectrum);
	}
	
	public void setPos(BigInteger pos) {
		if(this.pos.compareTo(pos) != 0) {
			this.pos = pos;
			generateImage();
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	
}
