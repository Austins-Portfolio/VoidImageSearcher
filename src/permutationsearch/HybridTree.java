package permutationsearch;

import java.util.ArrayList;
import java.math.BigInteger;

public class HybridTree {
	private BigInteger base, length, currentPermutation, endPermutation, jumpToNextRange, rangeLength;
	private BigInteger totalRangeCount = BigInteger.ZERO;
	private BigInteger treeCount = BigInteger.ZERO;
	private ArrayList<SearchKey> masterSearchKey = new ArrayList<SearchKey>();

	public HybridTree(ArrayList<SearchKey> search, BigInteger base, BigInteger length) {
		BigInteger maxPermutations = base.pow(length.intValue());
		
		search = ArrayUtils.sortSearchKeysDescending(search);
		masterSearchKey.addAll(search);
		this.base = base;
		this.length = length;
		ArrayList<SearchKey> searchCopy = new ArrayList<SearchKey>();
		searchCopy.addAll(search);
		if(searchCopy.get(0).getPosition() >= 0 && searchCopy.get(0).getPosition() < length.intValue()) {
			if(searchCopy.get(0).getValue() >= 0 && searchCopy.get(0).getValue() < base.intValue()) {
				BigInteger firstPermutation = base.pow(searchCopy.get(0).getPosition()).multiply(new BigInteger(""+searchCopy.get(0).getValue()));
				BigInteger rangeLength = base.pow(searchCopy.get(0).getPosition());
				BigInteger jumpToNextRange = base.pow(searchCopy.get(0).getPosition()).multiply(base);
				
				BigInteger currentPermutation = firstPermutation;
				
				this.currentPermutation = currentPermutation;
				this.endPermutation = maxPermutations;
				this.jumpToNextRange = jumpToNextRange;
				this.rangeLength = rangeLength;
				searchCopy.remove(0);
				if(searchCopy.size()==0) {
					totalRangeCount = generateRangeCount();
				}else {
					treeCount = generateTreeCount();
					totalRangeCount = generateTotalRangeCount();
				}
			}
		}
	}
	
	public HybridTree(ArrayList<SearchKey> search, BigInteger base, BigInteger length, Range range) {
		masterSearchKey.addAll(search);
		this.base = base;
		this.length = length;
		ArrayList<SearchKey> searchCopy = new ArrayList<SearchKey>();
		searchCopy.addAll(search);
		if(searchCopy.get(0).getPosition() >= 0 && searchCopy.get(0).getPosition() < length.intValue()) {
			if(searchCopy.get(0).getValue() >= 0 && searchCopy.get(0).getValue() < base.intValue()) {
				BigInteger firstPermutation = base.pow(searchCopy.get(0).getPosition()).multiply(new BigInteger(""+search.get(0).getValue()));
				BigInteger rangeLength = base.pow(searchCopy.get(0).getPosition());
				BigInteger jumpToNextRange = base.pow(searchCopy.get(0).getPosition()).multiply(base);
				
				BigInteger currentPermutation = firstPermutation.add(jumpToNextRange.multiply(range.getLowerBound().divide(jumpToNextRange)));
				BigInteger endPermutation = firstPermutation.add(jumpToNextRange.multiply(range.getUpperBound().add(BigInteger.ONE).divide(jumpToNextRange)));
				
				this.currentPermutation = currentPermutation;
				this.endPermutation = endPermutation;
				this.jumpToNextRange = jumpToNextRange;
				this.rangeLength = rangeLength;
				
				searchCopy.remove(0);
				if(searchCopy.size()==0) {
					totalRangeCount = generateRangeCount();
				}else {
					treeCount = generateTreeCount();
					totalRangeCount = generateTotalRangeCount();
				}
			}
		}
	}
	
	public HybridTree(ArrayList<SearchKey> search, BigInteger base, BigInteger length, Range range, boolean init) {
		masterSearchKey.addAll(search);
		this.base = base;
		this.length = length;
		ArrayList<SearchKey> searchCopy = new ArrayList<SearchKey>();
		searchCopy.addAll(search);
		if(searchCopy.get(0).getPosition() >= 0 && searchCopy.get(0).getPosition() < length.intValue()) {
			if(searchCopy.get(0).getValue() >= 0 && searchCopy.get(0).getValue() < base.intValue()) {
				BigInteger firstPermutation = base.pow(searchCopy.get(0).getPosition()).multiply(new BigInteger(""+search.get(0).getValue()));
				BigInteger rangeLength = base.pow(searchCopy.get(0).getPosition());
				BigInteger jumpToNextRange = base.pow(searchCopy.get(0).getPosition()).multiply(base);
				
				BigInteger currentPermutation = firstPermutation.add(jumpToNextRange.multiply(range.getLowerBound().divide(jumpToNextRange)));
				BigInteger endPermutation = firstPermutation.add(jumpToNextRange.multiply(range.getUpperBound().add(BigInteger.ONE).divide(jumpToNextRange)));
				
				this.currentPermutation = currentPermutation;
				this.endPermutation = endPermutation;
				this.jumpToNextRange = jumpToNextRange;
				this.rangeLength = rangeLength;
				
				searchCopy.remove(0);
				if(searchCopy.size()==0) {
					totalRangeCount = generateRangeCount();
				}else {
					treeCount = generateTreeCount();
					if(init) {
						totalRangeCount = generateTotalRangeCount();
					}
				}
			}
		}
	}
	
	private Range generateRangeAtIndex(BigInteger currentPermutation, BigInteger endPermutation, BigInteger jumpToNextRange, BigInteger rangeLength, BigInteger index) {
		BigInteger rangeCount = endPermutation.subtract(currentPermutation).add(BigInteger.ONE).divide(jumpToNextRange);
		if(index.compareTo(BigInteger.ZERO) >= 0 && index.compareTo(rangeCount) < 0) {
			currentPermutation = currentPermutation.add(jumpToNextRange.multiply(index));
			Range result = new Range(currentPermutation, currentPermutation.add(rangeLength.subtract(BigInteger.ONE)));
			return result;
		}
		return null;
	}
	
	public BigInteger generateRangeCount(BigInteger currentPermutation, BigInteger endPermutation, BigInteger jumpToNextRange, BigInteger rangeLength) {
		return endPermutation.subtract(currentPermutation).add(BigInteger.ONE).divide(jumpToNextRange);
	}
	
	public Range generateRangeAtIndex(BigInteger index) {
		return generateRangeAtIndex(this.currentPermutation, this.endPermutation, this.jumpToNextRange, this.rangeLength, index);
	}
	
	public BigInteger generateRangeCount() {
		return generateRangeCount(this.currentPermutation, this.endPermutation, this.jumpToNextRange, this.rangeLength);
	}
	
	public BigInteger generateTreeCount() {
		BigInteger count = BigInteger.ZERO;
		count = endPermutation.divide(jumpToNextRange).subtract(currentPermutation.divide(jumpToNextRange));
		return count;
	}
	
	public BigInteger generateTotalRangeCount() {
		if(treeCount.compareTo(BigInteger.ZERO) >  0) {
			HybridTree tree = getTreeAtIndex(BigInteger.ZERO, false);
			return tree.generateTotalRangeCount().multiply(treeCount);
		}else {
			return generateRangeCount();
		}
	}
	
	public DepthRangeTable generateDepthRangeTable() {
		if(treeCount.compareTo(BigInteger.ZERO) >  0) {
			HybridTree tree = getTreeAtIndex(BigInteger.ZERO, false);
			DepthRangeTable table = tree.generateDepthRangeTable();
			BigInteger tRanges = table.getRangesForDepth(masterSearchKey.size()-1).multiply(treeCount);
			return table.putAndReturn(masterSearchKey.size(), tRanges);
		}else {
			BigInteger tRanges = generateRangeCount();
			DepthRangeTable table = new DepthRangeTable();
			return table.putAndReturn(masterSearchKey.size(), tRanges);
		}
	}
	
	public BigInteger getTreeCount() {
		return treeCount;
	}
	
	public BigInteger getTotalRangeCount() {
		return totalRangeCount;
	}
	
	private HybridTree getTreeAtIndex(BigInteger currentPermutation, BigInteger endPermutation, BigInteger jumpToNextRange, BigInteger rangeLength, BigInteger index) {
		if(index.compareTo(BigInteger.ZERO) >= 0 && index.compareTo(treeCount) < 0) {
			currentPermutation = currentPermutation.add(jumpToNextRange.multiply(index));
			Range range = new Range(currentPermutation, currentPermutation.add(rangeLength.subtract(BigInteger.ONE)));
			ArrayList<SearchKey> keyClone = new ArrayList<SearchKey>();
			keyClone.addAll(masterSearchKey);
			keyClone.remove(0);
			HybridTree tree = new HybridTree(keyClone, base, length, range);
			return tree;
		}
		return null;
	}
	
	private HybridTree getTreeAtIndex(BigInteger currentPermutation, BigInteger endPermutation, BigInteger jumpToNextRange, BigInteger rangeLength, BigInteger index, boolean init) {
		if(index.compareTo(BigInteger.ZERO) >= 0 && index.compareTo(treeCount) < 0) {
			currentPermutation = currentPermutation.add(jumpToNextRange.multiply(index));
			Range range = new Range(currentPermutation, currentPermutation.add(rangeLength.subtract(BigInteger.ONE)));
			ArrayList<SearchKey> keyClone = new ArrayList<SearchKey>();
			keyClone.addAll(masterSearchKey);
			keyClone.remove(0);
			HybridTree tree = new HybridTree(keyClone, base, length, range, init);
			return tree;
		}
		return null;
	}
	
	public HybridTree getTreeAtIndex(BigInteger index) {
		return getTreeAtIndex(currentPermutation, endPermutation, jumpToNextRange, rangeLength, index);
	}
	
	public HybridTree getTreeAtIndex(BigInteger index, boolean init) {
		return getTreeAtIndex(currentPermutation, endPermutation, jumpToNextRange, rangeLength, index, init);
	}
	
	public Range getRangeAtIndex(BigInteger index) {
		if(index.compareTo(BigInteger.ZERO) >= 0 && index.compareTo(totalRangeCount) < 0) {
			if(treeCount.compareTo(BigInteger.ZERO) > 0) {
				BigInteger size = getTreeAtIndex(BigInteger.ZERO, false).generateTotalRangeCount();
				BigInteger treeLocation = index.divide(size);
				BigInteger newIndex = index.subtract(size.multiply(treeLocation));
				
				return getTreeAtIndex(treeLocation).getRangeAtIndex(newIndex);
			}else {
				return generateRangeAtIndex(index);
			}
		}else {
			return null;
		}
	}
	
	public Range getRangeAtIndex(BigInteger index,DepthRangeTable table) {
		totalRangeCount = table.getRangesForDepth(masterSearchKey.size());
		if(index.compareTo(BigInteger.ZERO) >= 0 && index.compareTo(totalRangeCount) < 0) {
			if(treeCount.compareTo(BigInteger.ZERO) > 0) {
				BigInteger size = table.getRangesForDepth(masterSearchKey.size()-1);
				BigInteger treeLocation = index.divide(size);
				BigInteger newIndex = index.subtract(size.multiply(treeLocation));
				
				return getTreeAtIndex(treeLocation, false).getRangeAtIndex(newIndex, table);
			}else {
				return generateRangeAtIndex(index);
			}
		}else {
			return null;
		}
	}
}
