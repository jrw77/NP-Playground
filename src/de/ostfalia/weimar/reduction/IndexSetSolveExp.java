package de.ostfalia.weimar.reduction;

import java.util.Set;

/**
 * An exponential solver for all Problems solvable by am IndexSet.
 * @author weimar
 *
 */
public class IndexSetSolveExp {

	/**
	 * Solve exponentially, uses 2^n steps and only works for n<64.
	 * @param select Which problem to solve
	 * @return Ths set which solves this problem or null  if the problem has no solution.
	 */
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
