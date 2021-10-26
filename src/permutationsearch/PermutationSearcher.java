package permutationsearch;

import java.math.BigInteger;
import java.util.ArrayList;

import babble.Babble;

public class PermutationSearcher {

	private Babble bob = new Babble();
	private BigInteger base, length, maxPermutations;
	
	public PermutationSearcher(int base, int length) {
		this.base = new BigInteger(""+base);
		this.length = new BigInteger(""+length);
		this.maxPermutations = this.base.pow(length);
	}
	
	public void searchForPermutations(SearchKey search) {
		if(search.getPosition() >= 0 && search.getPosition() < length.intValue()) {
			if(search.getValue() >= 0 && search.getValue() < base.intValue()) {
				BigInteger firstPermutation = base.pow(search.getPosition()).multiply(new BigInteger(""+search.getValue()));
				BigInteger rangeLength = base.pow(search.getPosition());
				BigInteger jumpToNextRange = base.pow(search.getPosition()).multiply(base);
				
				BigInteger currentPermutation = firstPermutation;
				for(BigInteger i = currentPermutation; i.compareTo(maxPermutations) < 0; i = i.add(jumpToNextRange)) {
					Range range = new Range(currentPermutation, currentPermutation.add(rangeLength.subtract(BigInteger.ONE)));
					range.print();
					currentPermutation = currentPermutation.add(jumpToNextRange);
				}
			}
		}
	}
	
	public void searchForPermutationAtIndex(SearchKey search, BigInteger index) {
		if(search.getPosition() >= 0 && search.getPosition() < length.intValue()) {
			if(search.getValue() >= 0 && search.getValue() < base.intValue()) {
				BigInteger firstPermutation = base.pow(search.getPosition()).multiply(new BigInteger(""+search.getValue()));
				BigInteger rangeLength = base.pow(search.getPosition());
				BigInteger jumpToNextRange = base.pow(search.getPosition()).multiply(base);
				
				BigInteger currentPermutation = firstPermutation;
				Range r =generateRangeAtIndex(currentPermutation, maxPermutations, jumpToNextRange, rangeLength, index);
				if(r!=null) {
					r.print();
				}
			}
		}
	}
	
	public void searchForPermutationsInRange(SearchKey search, Range range) {
		if(search.getPosition() >= 0 && search.getPosition() < length.intValue()) {
			if(search.getValue() >= 0 && search.getValue() < base.intValue()) {
				BigInteger firstPermutation = base.pow(search.getPosition()).multiply(new BigInteger(""+search.getValue()));
				BigInteger rangeLength = base.pow(search.getPosition());
				BigInteger jumpToNextRange = base.pow(search.getPosition()).multiply(base);
				
				BigInteger currentPermutation = firstPermutation.add(jumpToNextRange.multiply(range.getLowerBound().divide(jumpToNextRange)));
				BigInteger endPermutation = firstPermutation.add(jumpToNextRange.multiply(range.getUpperBound().add(BigInteger.ONE).divide(jumpToNextRange)));
				
				for(BigInteger i = currentPermutation; i.compareTo(endPermutation) < 0; i = i.add(jumpToNextRange)) {
					Range r = new Range(currentPermutation, currentPermutation.add(rangeLength.subtract(BigInteger.ONE)));
					r.print();
					currentPermutation = currentPermutation.add(jumpToNextRange);
				}
			}
		}
	}
	
	public void searchForPermutationsInRangeAtIndex(SearchKey search, Range range, BigInteger index) {
		if(search.getPosition() >= 0 && search.getPosition() < length.intValue()) {
			if(search.getValue() >= 0 && search.getValue() < base.intValue()) {
				BigInteger firstPermutation = base.pow(search.getPosition()).multiply(new BigInteger(""+search.getValue()));
				BigInteger rangeLength = base.pow(search.getPosition());
				BigInteger jumpToNextRange = base.pow(search.getPosition()).multiply(base);
				
				BigInteger currentPermutation = firstPermutation.add(jumpToNextRange.multiply(range.getLowerBound().divide(jumpToNextRange)));
				BigInteger endPermutation = firstPermutation.add(jumpToNextRange.multiply(range.getUpperBound().add(BigInteger.ONE).divide(jumpToNextRange)));
				
				Range r =generateRangeAtIndex(currentPermutation, endPermutation, jumpToNextRange, rangeLength, index);
				if(r!=null) {
					r.print();
				}
			}
		}
	}
	
	public void searchForPermutationsInRange(ArrayList<SearchKey> search, Range range) {
		ArrayList<SearchKey> searchCopy = new ArrayList<SearchKey>();
		searchCopy.addAll(search);
		if(searchCopy.get(0).getPosition() >= 0 && searchCopy.get(0).getPosition() < length.intValue()) {
			if(searchCopy.get(0).getValue() >= 0 && searchCopy.get(0).getValue() < base.intValue()) {
				BigInteger firstPermutation = base.pow(searchCopy.get(0).getPosition()).multiply(new BigInteger(""+search.get(0).getValue()));
				BigInteger rangeLength = base.pow(searchCopy.get(0).getPosition());
				BigInteger jumpToNextRange = base.pow(searchCopy.get(0).getPosition()).multiply(base);
				
				BigInteger currentPermutation = firstPermutation.add(jumpToNextRange.multiply(range.getLowerBound().divide(jumpToNextRange)));
				BigInteger endPermutation = firstPermutation.add(jumpToNextRange.multiply(range.getUpperBound().add(BigInteger.ONE).divide(jumpToNextRange)));
				
				searchCopy.remove(0);
				if(searchCopy.size()==0) {
					for(BigInteger i = currentPermutation; i.compareTo(endPermutation) < 0; i = i.add(jumpToNextRange)) {
						Range r = new Range(currentPermutation, currentPermutation.add(rangeLength.subtract(BigInteger.ONE)));
						r.print();
						currentPermutation = currentPermutation.add(jumpToNextRange);
					}
				}else {
					for(BigInteger i = currentPermutation; i.compareTo(endPermutation) < 0; i = i.add(jumpToNextRange)) {
						searchForPermutationsInRange(searchCopy, new Range(currentPermutation, currentPermutation.add(rangeLength.subtract(BigInteger.ONE))));
						currentPermutation = currentPermutation.add(jumpToNextRange);
					}
				}
			}
		}
	}
	
	public void searchForPermutations(ArrayList<SearchKey> search) {
		search = ArrayUtils.sortSearchKeysDescending(search);
		ArrayList<SearchKey> searchCopy = new ArrayList<SearchKey>();
		searchCopy.addAll(search);
		if(searchCopy.get(0).getPosition() >= 0 && searchCopy.get(0).getPosition() < length.intValue()) {
			if(searchCopy.get(0).getValue() >= 0 && searchCopy.get(0).getValue() < base.intValue()) {
				BigInteger firstPermutation = base.pow(searchCopy.get(0).getPosition()).multiply(new BigInteger(""+searchCopy.get(0).getValue()));
				BigInteger rangeLength = base.pow(searchCopy.get(0).getPosition());
				BigInteger jumpToNextRange = base.pow(searchCopy.get(0).getPosition()).multiply(base);
				
				BigInteger currentPermutation = firstPermutation;
				searchCopy.remove(0);
				if(searchCopy.size()==0) {
					for(BigInteger i = currentPermutation; i.compareTo(maxPermutations) < 0; i = i.add(jumpToNextRange)) {
						Range range = new Range(currentPermutation, currentPermutation.add(rangeLength.subtract(BigInteger.ONE)));
						range.print();
						currentPermutation = currentPermutation.add(jumpToNextRange);
					}
				}else {
					for(BigInteger i = currentPermutation; i.compareTo(maxPermutations) < 0; i = i.add(jumpToNextRange)) {
						searchForPermutationsInRange(searchCopy, new Range(currentPermutation, currentPermutation.add(rangeLength.subtract(BigInteger.ONE))));
						currentPermutation = currentPermutation.add(jumpToNextRange);
					}
				}
			}
		}
	}
	
	public ArrayList<Range> searchForPermutationsInRangeReturnArray(ArrayList<SearchKey> search, Range range) {
		ArrayList<SearchKey> searchCopy = new ArrayList<SearchKey>();
		searchCopy.addAll(search);
		ArrayList<Range> ranges = new ArrayList<Range>();
		if(searchCopy.get(0).getPosition() >= 0 && searchCopy.get(0).getPosition() < length.intValue()) {
			if(searchCopy.get(0).getValue() >= 0 && searchCopy.get(0).getValue() < base.intValue()) {
				BigInteger firstPermutation = base.pow(searchCopy.get(0).getPosition()).multiply(new BigInteger(""+search.get(0).getValue()));
				BigInteger rangeLength = base.pow(searchCopy.get(0).getPosition());
				BigInteger jumpToNextRange = base.pow(searchCopy.get(0).getPosition()).multiply(base);
				
				BigInteger currentPermutation = firstPermutation.add(jumpToNextRange.multiply(range.getLowerBound().divide(jumpToNextRange)));
				BigInteger endPermutation = firstPermutation.add(jumpToNextRange.multiply(range.getUpperBound().add(BigInteger.ONE).divide(jumpToNextRange)));
				
				searchCopy.remove(0);
				if(searchCopy.size()==0) {
					for(BigInteger i = currentPermutation; i.compareTo(endPermutation) < 0; i = i.add(jumpToNextRange)) {
						Range r = new Range(currentPermutation, currentPermutation.add(rangeLength.subtract(BigInteger.ONE)));
						ranges.add(r);
						currentPermutation = currentPermutation.add(jumpToNextRange);
					}
				}else {
					for(BigInteger i = currentPermutation; i.compareTo(endPermutation) < 0; i = i.add(jumpToNextRange)) {
						ranges.addAll(searchForPermutationsInRangeReturnArray(searchCopy, new Range(currentPermutation, currentPermutation.add(rangeLength.subtract(BigInteger.ONE)))));
						currentPermutation = currentPermutation.add(jumpToNextRange);
					}
				}
			}
		}
		return ranges;
	}
	
	public ArrayList<Range> searchForPermutationsReturnArray(ArrayList<SearchKey> search) {
		search = ArrayUtils.sortSearchKeysDescending(search);
		ArrayList<SearchKey> searchCopy = new ArrayList<SearchKey>();
		searchCopy.addAll(search);
		ArrayList<Range> ranges = new ArrayList<Range>();
		if(searchCopy.get(0).getPosition() >= 0 && searchCopy.get(0).getPosition() < length.intValue()) {
			if(searchCopy.get(0).getValue() >= 0 && searchCopy.get(0).getValue() < base.intValue()) {
				BigInteger firstPermutation = base.pow(searchCopy.get(0).getPosition()).multiply(new BigInteger(""+searchCopy.get(0).getValue()));
				BigInteger rangeLength = base.pow(searchCopy.get(0).getPosition());
				BigInteger jumpToNextRange = base.pow(searchCopy.get(0).getPosition()).multiply(base);
				
				BigInteger currentPermutation = firstPermutation;
				searchCopy.remove(0);
				if(searchCopy.size()==0) {
					for(BigInteger i = currentPermutation; i.compareTo(maxPermutations) < 0; i = i.add(jumpToNextRange)) {
						Range range = new Range(currentPermutation, currentPermutation.add(rangeLength.subtract(BigInteger.ONE)));
						ranges.add(range);
						currentPermutation = currentPermutation.add(jumpToNextRange);
					}
				}else {
					for(BigInteger i = currentPermutation; i.compareTo(maxPermutations) < 0; i = i.add(jumpToNextRange)) {
						ranges.addAll(searchForPermutationsInRangeReturnArray(searchCopy, new Range(currentPermutation, currentPermutation.add(rangeLength.subtract(BigInteger.ONE)))));
						currentPermutation = currentPermutation.add(jumpToNextRange);
					}
				}
			}
		}
		return ranges;
	}
	
	public void printBruteForce() {
		for(BigInteger i = BigInteger.ZERO; i.compareTo(maxPermutations) < 0; i = i.add(BigInteger.ONE)) {
			int[] data = bob.getBigDataIntsWithLength(i, base.intValue(), length.intValue());
			System.out.print(i.intValue() + " : ");
			for(int j = data.length-1; j >= 0; j--) {
				System.out.print(data[j] + " ");
			}
			System.out.println();
		}
	}
	
	public Range generateRangeAtIndex(BigInteger currentPermutation, BigInteger endPermutation, BigInteger jumpToNextRange, BigInteger rangeLength, BigInteger index) {
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
	
	public BigInteger getBase() {
		return base;
	}
	
	public BigInteger getLength() {
		return length;
	}
	
	public BigInteger getMaxPermutations() {
		return maxPermutations;
	}
	
	public int[] generateData(BigInteger position) {
		return bob.getBigDataIntsWithLength(position, base.intValue(), length.intValue());
	}
	
	public BigInteger generatePosition(int[] data) {
		return bob.getBigPositionInts(data, base.intValue());
	}
}
