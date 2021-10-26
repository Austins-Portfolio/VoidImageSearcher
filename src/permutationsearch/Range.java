package permutationsearch;

import java.math.BigInteger;

public class Range {

	private BigInteger lowerBound, upperBound, distance;

	public Range(BigInteger lowerBound, BigInteger upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.distance = upperBound.subtract(lowerBound).add(BigInteger.ONE);
	}

	public BigInteger getLowerBound() {
		return lowerBound;
	}

	public BigInteger getUpperBound() {
		return upperBound;
	}

	public BigInteger getDistance() {
		return distance;
	}
	
	public void print() {
		System.out.println(lowerBound + " -> " + upperBound);
	}
	
	public String toString() {
		return new String(lowerBound + " -> " + upperBound);
	}
	
}
