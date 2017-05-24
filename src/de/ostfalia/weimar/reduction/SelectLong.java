package de.ostfalia.weimar.reduction;

import java.util.Arrays;
import java.util.Set;

public class SelectLong implements VerifyByIndexSet{
	long[] numbers;
	long z;
	
	public SelectLong(long[] numbers, long z){
		this.numbers = numbers;
		this.z = z;
	}
	
	public boolean verify(Set<Integer> indices){
		long sum = 0L;
		for (int i : indices){
			sum  += numbers[i];
		}
		return sum == z;
	}
	
	@Override
	public String toString(){
		return "Select("+Arrays.toString(numbers)+"; "+z+")";
	}
	
	public String solutionString(Set<Integer> indices){
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (int i : indices){
			if (first ){
				first = false;
			}else{
				sb.append(" + ");
			}
			sb.append(numbers[i]);
		}
		sb.append(" = ");
		sb.append(z);
		return sb.toString();
	}

	@Override
	public int size() {
		return numbers.length;
	}

}
