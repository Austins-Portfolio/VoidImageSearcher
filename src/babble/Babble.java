package babble;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Babble {

	private HashMap<Character, Integer> symbolTable = new HashMap<>();
	private HashMap<Integer, Character> valueTable = new HashMap<>();
	
	public Babble() {
		
	}
	
	public Babble(char[] chars) {
		for(char c: chars) {
			addSymbol(c);
		}
	}
	
	public Babble(String string) {
		char[] chars = string.toCharArray();
		for(char c: chars) {
			addSymbol(c);
		}
	}
	
	public Babble(HashMap<Character, Integer> symbolTable, HashMap<Integer, Character> valueTable) {
		this.symbolTable = symbolTable;
		this.valueTable = valueTable;
	}
	
	public void addSymbol(Character c) {
		int size = symbolTable.size();
		symbolTable.put(c, size);
		valueTable.put(size, c);
	}
	
	public int getPosition(String text) {
		ArrayList<Integer> symbols = new ArrayList<Integer>();
		char[] characters = text.toCharArray();
		for(int i = 0; i < characters.length; i++) {
			symbols.add(symbolTable.get(characters[i]));
		}
		
		return Converter.convertToInteger(symbols, symbolTable.size());
	}
	
	public String getText(int position) {
		ArrayList<Integer> symbols = Converter.convertToArray(position, symbolTable.size());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < symbols.size(); i++) {
			sb.append(valueTable.get(symbols.get(i)));
		}
		
		return sb.toString();
	}
	
	public BigInteger getBigPosition(String text) {
		ArrayList<BigInteger> symbols = new ArrayList<BigInteger>();
		char[] characters = text.toCharArray();
		for(int i = 0; i < characters.length; i++) {
			symbols.add(BigInteger.valueOf(symbolTable.get(characters[i])));
		}
		
		return Converter.convertToBigInteger(symbols, symbolTable.size());
	}
	
	public String getBigText(BigInteger position) {
		ArrayList<BigInteger> symbols = Converter.convertToBigArray(position, symbolTable.size());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < symbols.size(); i++) {
			sb.append(valueTable.get(symbols.get(i).intValue()));
		}
		
		return sb.toString();
	}
	
	public BigInteger getBigPositionInts(int[] ints, int size) {
		ArrayList<BigInteger> symbols = new ArrayList<BigInteger>();
		for(int i = 0; i < ints.length; i++) {
			symbols.add(BigInteger.valueOf(ints[i]));
		}
		
		return Converter.convertToBigInteger(symbols, size);
	}
	
	public int[] getBigDataInts(BigInteger position, int size) {
		ArrayList<BigInteger> symbols = Converter.convertToBigArray(position, size);
		
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for(int i = 0; i < symbols.size(); i++) {
			ints.add(symbols.get(i).intValue());
		}
		
		int[] final_ints = new int[ints.size()];
		
		for(int i = 0; i < ints.size(); i++) {
			final_ints[i] = ints.get(i);
		}
		
		return final_ints;
	}
	
	public int[] getBigDataIntsWithLength(BigInteger position, int size, int length) {
		ArrayList<BigInteger> symbols = Converter.convertToBigArrayWithLength(position, size, length);
		
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for(int i = 0; i < symbols.size(); i++) {
			ints.add(symbols.get(i).intValue());
		}
		
		int[] final_ints = new int[ints.size()];
		
		for(int i = 0; i < ints.size(); i++) {
			final_ints[i] = ints.get(i);
		}
		
		return final_ints;
	}
}
