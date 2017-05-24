package de.ostfalia.weimar.reduction.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import de.ostfalia.weimar.reduction.SetLongBased;

public class SetLongBasedTest {

	@Test
	public void testSize() {
		SetLongBased slb0 = new SetLongBased(0L);
		SetLongBased slb2 = new SetLongBased(5L);
		assertEquals("Size?", 0, slb0.size());
		assertEquals("Size?", 2, slb2.size());
	}

	@Test
	public void testIsEmpty() {
		SetLongBased slb0 = new SetLongBased(0L);
		SetLongBased slb2 = new SetLongBased(5L);
		assertTrue("empty", slb0.isEmpty());
		assertFalse("empty", slb2.isEmpty());
	}

	@Test
	public void testContains() {
		SetLongBased slb0 = new SetLongBased(0L);
		SetLongBased slb2 = new SetLongBased(5L);
		assertTrue("contains 2", slb2.contains(2));
		assertFalse("contains 1", slb2.contains(1));
		assertFalse("contains 1", slb0.contains(1));
		assertFalse("contains 1", slb2.contains(3));
	}

	@Test
	public void testIterator() {
		SetLongBased slb2 = new SetLongBased(5L);
		Iterator<Integer> it = slb2.iterator();
		
		assertTrue("iterator", it.hasNext());
		assertEquals("iterator next", Integer.valueOf(0), it.next());
		assertTrue("iterator", it.hasNext());
		assertEquals("iterator next", Integer.valueOf(2), it.next());
		assertFalse("iterator", it.hasNext());
	}

	@Test
	public void testAdd() {
		SetLongBased slb2 = new SetLongBased(5L);
		slb2.add(4);
		assertEquals(" same?", "10101" , slb2.toString());
	}

	@Test
	public void testRemove() {
		SetLongBased slb2 = new SetLongBased(5L);
		slb2.remove(0);
		assertEquals(" same?" , "100", slb2.toString());
	}

	@Test
	public void testContainsAll() {
		SetLongBased slb1 = new SetLongBased(7L);
		SetLongBased slb2 = new SetLongBased(5L);
		assertTrue("Should contain all" , slb1.containsAll(slb2));
		SetLongBased slb3 = new SetLongBased(10L);
		assertFalse("Should not contain all" , slb1.containsAll(slb3));
	}

	@Test
	public void testContainsAll2() {
		SetLongBased slb1 = new SetLongBased(7L);
		Set<Integer> s2 = new TreeSet<Integer>();
		s2.add(0); 
		s2.add(2);
		assertTrue("Should contain all" , slb1.containsAll(s2));
		Set<Integer> s3 = new TreeSet<Integer>();
		s3.add(1); 
		s3.add(3);
		assertFalse("Should not contain all"+slb1+" "+s3 , slb1.containsAll(s3));
	}

	
	@Test
	public void testAddAll() {
		SetLongBased slb1 = new SetLongBased(2L);
		Set<Integer> s2 = new TreeSet<Integer>();
		s2.add(0); 
		s2.add(3);
		slb1.addAll(s2);
		assertEquals("Should be" , "1011", slb1.toString());
	}

	@Test
	public void testRemoveAll() {
		SetLongBased slb1 = new SetLongBased(15L);
		Set<Integer> s2 = new TreeSet<Integer>();
		s2.add(0); 
		s2.add(3);
		slb1.removeAll(s2);
		assertEquals("Should be" , "110", slb1.toString());
	}

	@Test
	public void testRetainAll() {
		SetLongBased slb1 = new SetLongBased(14L);
		Set<Integer> s2 = new TreeSet<Integer>();
		s2.add(0); 
		s2.add(3);
		slb1.retainAll(s2);
		assertEquals("Should be" , "1000", slb1.toString());
	}

	@Test
	public void testClear() {
		SetLongBased slb2 = new SetLongBased(5L);
		slb2.clear();
		assertTrue("now Empty?", slb2.isEmpty());
		assertEquals(" same?" , "0", slb2.toString());
	}

	@Test
	public void testToString() {
		SetLongBased slb2 = new SetLongBased(10L);
		assertEquals(" same?" , "1010", slb2.toString());
	}

}
