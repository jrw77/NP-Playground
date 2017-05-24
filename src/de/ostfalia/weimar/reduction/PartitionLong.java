package de.ostfalia.weimar.reduction;

import java.util.Set;

public class PartitionLong  implements VerifyByIndexSet{
	long [] numbers;
	
	public boolean verify(Set<Integer> indices){
		long sum = 0;
		for (int i : indices){
			sum += numbers[i];
		}
		long sum_total = 0;		
		for (long j : numbers){
			sum_total += j;
		}
		return sum == sum_total-sum;
	}

	@Override
	public int size() {
		return numbers.length;
	}
}
