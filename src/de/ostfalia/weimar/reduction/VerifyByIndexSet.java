package de.ostfalia.weimar.reduction;

import java.util.Set;

/**
 * Problem is verifiable by an index Set, a set of Integers.
 *
 */
public interface VerifyByIndexSet {

	/**
	 * verify this problem with the given index set.
	 * @param indices set of Integers which are indices into the 
	 * 	data structure of the problem.
	 * @return true if this index set is a solution of the problem.
	 */
	boolean verify(Set<Integer> indices);
	
	/**
	 * return the size of the problem, i.e. the upper limit for the indices.
	 * @return the largest possible index+1
	 */
	int size();
}