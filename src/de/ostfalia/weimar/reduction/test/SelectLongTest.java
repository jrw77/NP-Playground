package de.ostfalia.weimar.reduction.test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.ostfalia.weimar.reduction.SelectLong;
import de.ostfalia.weimar.reduction.IndexSetSolveExp;
import de.ostfalia.weimar.reduction.Select;

public class SelectLongTest {


	@Test (timeout=10)
	public void testToString() {
		long[] n = {1L, 3L, 10L};
		SelectLong select = new SelectLong(n , 4L);
		String res = select.toString();
		assertTrue(" zu kurz ",res.length() > 15);
	}

	@Test (timeout=10)
	public void testVerify1() {
		long[] n = {1L, 3L, 10L};
		SelectLong select = new SelectLong(n , 4L);
		Set<Integer> set = new HashSet<Integer>(3);
		set.add(0);
		set.add(1);
		assertTrue(" This set should verify select. ", select.verify(set));
	}

	@Test (timeout=10)
	public void testVerify2() {
		long[] n = {1L, 3L, 10L};
		SelectLong select = new SelectLong(n , 4L);
		Set<Integer> set = new HashSet<Integer>(3);
		set.add(0);
		set.add(2);
		assertFalse(" This set should not verify select. ", select.verify(set));
	}
	
	@Test
	public void testSolve1() {
		long[] n = {1L, 3L, 10L};
		SelectLong select = new SelectLong(n , 4L);
		Set<Integer> sol = IndexSetSolveExp.solve(select);
		assertTrue(" This set should verify select. ", select.verify(sol));
		System.out.println("Solution to "+select+" is: "
				+select.solutionString(sol));
	}

	@Test
	public void testSolve2() {
		long[] n = {1L, 3L, 10L, 100L, 1000L, 33L, 57L, 42L};
		SelectLong select = new SelectLong(n , 200L);
		Set<Integer> sol = IndexSetSolveExp.solve(select);
		assertTrue(" This set should verify select. ", select.verify(sol));
		System.out.println("Solution to "+select+" is: "
				+select.solutionString(sol));
	}

	@Test
	public void testSolve3() {
		long[] n = {1L, 3L, 10L, 100L, 1000L, 33L, 57L, 42L,
				37L, 72245L, 12345L, 10012L, 444L,
				11L, 101L, 1001L, 34L, 58L, 43L, 
				12L, 102L, 1002L, 35L};
		
		SelectLong select = new SelectLong(n , 19999L);
		Set<Integer> sol = IndexSetSolveExp.solve(select);
		assertTrue(" This set should not verify select. ", sol == null);
	}

	@Test
	public void testSolve4() {
		long[] n = {1L, 3L, 10L, 100L, 1000L, 33L, 57L, 42L,
				37L, 72245L, 12345L, 10012L, 444L,
				11L, 101L, 1001L, 34L, 58L, 43L, 
				12L, 102L, 1002L, 35L};
		BigInteger [] bis = new BigInteger[n.length];
		for (int i=0; i<n.length; i++){
			bis[i] = new BigInteger(Long.toString(n[i]));
		}
		BigInteger sum = new BigInteger(Long.toString(19999L));
		Select select = new Select(bis , sum);
		Set<Integer> sol = IndexSetSolveExp.solve(select);
		assertTrue(" This set should not verify select. ", sol == null);
	}


}
