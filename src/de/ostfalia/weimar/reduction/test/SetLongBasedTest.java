package de.ostfalia.weimar.reduction.test;

import static org.junit.Assert.*;

import java.util.Iterator;

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
		fail("Not yet implemented");
	}

	@Test
	public void testAddAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAll() {
		fail("Not yet implemented");
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
