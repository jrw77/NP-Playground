package de.ostfalia.weimar.reduction;

import java.math.BigInteger;
import java.util.Set;

public class Select implements VerifyByIndexSet {
	BigInteger[] numbers;
	BigInteger z;
	
	public Select(BigInteger[] numbers, BigInteger z){
		this.numbers = numbers;
		this.z = z;
	}

	/* (non-Javadoc)
	 * @see de.ostfalia.weimar.reduction.VerifyByIndexSet#verify(java.util.Set)
	 */
	@Override
	public boolean verify(Set<Integer> indices){
		BigInteger sum = BigInteger.ZERO;
		for (int i : indices){
			sum  = sum.add(numbers[i]);
		}
		return sum.equals(z);
	}
	
	public int size(){
		return numbers.length;
	}
}
