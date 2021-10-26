package babble;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Converter {

	public static ArrayList<Integer> convertToArray(int position, int symbolCount) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		int current = position;
		while(true)
		{
			int quotient = current / symbolCount;
			int remainder = current % symbolCount;
			
			current = quotient;
			
			array.add(remainder);
			
			if(quotient == 0) {
				break;
			}
		}
		
		return array;
	}
	
	public static int convertToInteger(ArrayList<Integer> array, int symbolCount) {
		int position = 0;
		for(int i = array.size()-1; i >= 0; i--) {
			position += array.get(i)*Math.pow(symbolCount, i);
		}
		
		return position;
	}
	
	public static ArrayList<BigInteger> convertToBigArray(BigInteger position, int symbolCount) {
		ArrayList<BigInteger> array = new ArrayList<BigInteger>();
		
		BigInteger current = position;
		while(true)
		{
			BigInteger quotient = current.divide(BigInteger.valueOf((long)symbolCount));
			BigInteger remainder = current.mod(BigInteger.valueOf((long)symbolCount));
			
			current = quotient;
			
			array.add(remainder);
			
			if(quotient.intValue() == 0) {
				break;
			}
		}
		
		return array;
	}
	
	public static ArrayList<BigInteger> convertToBigArrayWithLength(BigInteger position, int symbolCount, int length) {
		ArrayList<BigInteger> array = new ArrayList<BigInteger>();
		
		BigInteger current = position;
		int count = 0;
		while(true)
		{
			if(count>=length) {
				break;
			}
			BigInteger quotient = current.divide(BigInteger.valueOf((long)symbolCount));
			BigInteger remainder = current.mod(BigInteger.valueOf((long)symbolCount));
			
			current = quotient;
			
			array.add(remainder);
			
			count++;
		}
		
		return array;
	}
	
	public static BigInteger convertToBigInteger(ArrayList<BigInteger> array, int symbolCount) {
		BigInteger position = BigInteger.valueOf(0);
		for(int i = array.size()-1; i >= 0; i--) {
			position = BigInteger.valueOf(0).add(position).add(array.get(i).multiply(BigInteger.valueOf(symbolCount).pow(i)));
		}
		
		return position;
	}
	
	public static BigInteger randomBigIntegerInRange(BigInteger min, BigInteger max) {
	    Random rand = new Random();
	    BigInteger result = new BigInteger(max.bitLength(), rand);
	    while( result.compareTo(max) >= 0 || result.compareTo(min) <= 0) {
	        result = new BigInteger(max.bitLength(), rand);
	    }
	    return result;
	}
	
}
