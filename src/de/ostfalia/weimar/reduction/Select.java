package de.ostfalia.weimar.reduction;

import java.math.BigInteger;
import java.util.Set;

public class Select {
	BigInteger[] numbers;
	BigInteger z;
	
	public boolean verify(Set<Integer> indices){
		BigInteger sum = BigInteger.ZERO;
		for (int i : indices){
			sum  = sum.add(numbers[i]);
		}
		return sum.equals(z);
	}
}
