package permutationsearch;

import java.math.BigInteger;

public class MathUtils {

	public static BigInteger snapTo(BigInteger value, BigInteger snap) {
		return value.divide(snap).multiply(snap);
	}
	
	public static BigInteger snapUp(BigInteger value, BigInteger snap) {
		return value.divide(snap).multiply(snap).add(snap);
	}
	
	public static BigInteger snapDown(BigInteger value, BigInteger snap) {
		return value.divide(snap).multiply(snap).subtract(snap);
	}
	
	public static BigInteger snapToEven(BigInteger value) {
		if(value.mod(BigInteger.TWO).compareTo(BigInteger.ZERO)==0) {
			return value;
		}else {
			return value.add(BigInteger.ONE);
		}
	}
	
	public static BigInteger snapToEvenUp(BigInteger value) {
		if(value.mod(BigInteger.TWO).compareTo(BigInteger.ZERO)==0) {
			return value.add(BigInteger.TWO);
		}else {
			return value.add(BigInteger.ONE);
		}
	}
	
	public static BigInteger snapToEvenDown(BigInteger value) {
		if(value.mod(BigInteger.TWO).compareTo(BigInteger.ZERO)==0) {
			return value.subtract(BigInteger.TWO);
		}else {
			return value.subtract(BigInteger.ONE);
		}
	}
	
	public static BigInteger snapToOdd(BigInteger value) {
		if(value.mod(BigInteger.TWO).compareTo(BigInteger.ZERO)!=0) {
			return value;
		}else {
			return value.add(BigInteger.ONE);
		}
	}
	
	public static BigInteger snapToOddUp(BigInteger value) {
		if(value.mod(BigInteger.TWO).compareTo(BigInteger.ZERO)!=0) {
			return value.add(BigInteger.TWO);
		}else {
			return value.add(BigInteger.ONE);
		}
	}
	
	public static BigInteger snapToOddDown(BigInteger value) {
		if(value.mod(BigInteger.TWO).compareTo(BigInteger.ZERO)!=0) {
			return value.subtract(BigInteger.TWO);
		}else {
			return value.subtract(BigInteger.ONE);
		}
	}
	
	public static boolean isEven(BigInteger number){
	    return number.getLowestSetBit() != 0;
	}
	
	public static BigInteger closestNumber(BigInteger n, BigInteger m)
	{
	    BigInteger n1 = n.subtract(n.mod(m));
	    BigInteger n2 = n.divide(m).subtract(n.mod(m));
	    System.out.println(n1  + " : " + n2);
	    if (n.subtract(n1).abs().compareTo(n.subtract(n2).abs()) < 0) {
	        return n1;
	    }
   
	    return n2;   
	}
	
}
