package permutationsearch;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class PermutationTree {

	private int base, length;
	private PermutationSearcher permutationSearcher;
	private HybridTree hybridTree;
	private DepthRangeTable table;
	
	public PermutationTree(int base, int length, ArrayList<SearchKey> search) {
		this.base = base;
		this.length = length;
		permutationSearcher = new PermutationSearcher(base, length+1);
		
		search.add(new SearchKey(0,0));
		
		hybridTree = new HybridTree(search, permutationSearcher.getBase(), permutationSearcher.getLength());
		table = hybridTree.generateDepthRangeTable();
	}
	
	public BigInteger getIndexesAvaliable() {
		return hybridTree.generateTotalRangeCount();
	}
	
	public BigInteger getPosition(int[] data) {
		if(data.length == length) {
			int[] padding  = {0};
			int[] paddedData = ArrayUtils.concat(padding, data);
			return permutationSearcher.generatePosition(paddedData);
		}else {
			return null;
		}
	}
	
	public int[] getData(BigInteger position) {
		int[] data = permutationSearcher.generateData(position);
		return Arrays.copyOfRange(data, 1, data.length);
	}
	
	public Range getIndex(BigInteger i) {
		return hybridTree.getRangeAtIndex(i, table);
	}
	
	public HybridTree getHybridTree() {
		return hybridTree;
	}
	
	
}
