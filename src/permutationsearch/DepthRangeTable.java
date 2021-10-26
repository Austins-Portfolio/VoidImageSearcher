package permutationsearch;

import java.math.BigInteger;
import java.util.HashMap;

public class DepthRangeTable {

	private HashMap<Integer, BigInteger> table = new HashMap<>();

	public void put(int depth, BigInteger size) {
		if(!table.containsKey(depth)) {
			table.put(depth, size);
		}
	}
	
	public DepthRangeTable putAndReturn(int depth, BigInteger size) {
		if(!table.containsKey(depth)) {
			table.put(depth, size);
		}
		return this;
	}
	
	public BigInteger getRangesForDepth(int depth) {
		return table.get(depth);
	}
	
	public boolean contains(int depth) {
		if(table.containsKey(depth)) {
			return true;
		}
		return false;
	}
	
}
