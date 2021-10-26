package construct;

import java.awt.image.BufferedImage;
import java.math.BigInteger;

import permutationsearch.PermutationSearcher;
import utils.ImageUtils;

public class ImageHolder {

	private PermutationSearcher ps;
	private BigInteger pos;
	private BufferedImage image;
	private int width, height;
	
	public ImageHolder(PermutationSearcher ps, BigInteger pos, int width, int height) {
		this.ps = ps;
		this.pos = pos;
		this.width = width;
		this.height = height;
		generateImage();
	}
	
	private void generateImage() {
		int[] data = ps.generateData(pos);
		image = ImageUtils.convertDataToImage(data, width, height);
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
