package de.ostfalia.weimar.reduction;

import java.util.Set;

public class IndexSetSolveExp {

	public static Set<Integer> solve(VerifyByIndexSet select){
		int n = select.size();
		
		if (n > 64){
			throw new RuntimeException("way too big for me");
		}
		long count = 0;
		for (long l = 0; l < (1<<n); l++){
			Set<Integer> indices = new SetLongBased(l);
			count ++;
			if (select.verify(indices)){
				return indices;
			}
		}
		System.out.println("tested "+count+" configurations");
		return null;
	}
}
