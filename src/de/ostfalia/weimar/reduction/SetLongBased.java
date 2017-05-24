package de.ostfalia.weimar.reduction;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * An efficient implementation of a set of integers based on the 
 * bitvector-idea. Stores sets with small integers very efficiently while
 * implementing the set interface. The set can only contain Integers from 0 .. 63.
 * @author jrw77@jweimar.de
 *
 */
public class SetLongBased implements Set<Integer> {
	private long l;
	
	/**
	 * create a new set based on the given long as bit vector.
	 * @param l
	 */
	public SetLongBased(long l) {
		this.l = l;
	}

	@Override
	public int size() {
		return Long.bitCount(l);
	}

	@Override
	public boolean isEmpty() {
		return l == 0L;
	}

	@Override
	public boolean contains(Object o) {
		if (!(o instanceof Integer)){
			return false;
		}
		int i = (Integer)o;
		if (i < 0 || i >= Long.SIZE){
			return false;
		}
		return (l & (1L<<i)) != 0;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>(){
			int bit = 0;
			@Override
			public boolean hasNext() {
				return  (l >>> bit) != 0 ;
			}

			@Override
			public Integer next() {
				long next = Long.lowestOneBit(l & ~((1<<bit)-1));
				bit =  Long.numberOfTrailingZeros(next)+1;
				return bit-1;
			}
		};
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException("Not done because inefficient!");
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException("Not done because inefficient!");
	}

	@Override
	public boolean add(Integer i) {
		if (i < 0 || i>= Long.SIZE){
			throw new RuntimeException("Out of range!");
		}
		boolean old = (l & (1L<<i)) != 0;
		l |= (1L<<i);
		return !old;
	}

	@Override
	public boolean remove(Object o) {
		if (!(o instanceof Integer)){
			return false;
		}
		int i = (Integer)o;
		if (i < 0 || i>= Long.SIZE){
			throw new RuntimeException("Out of Range!");
		}
		boolean old = (l & (1L<<i)) != 0;
		l &= ~(1L<<i);
		return old;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c){
			if (! this.contains(o)){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		boolean changed = false;
		for (Integer i : c){
			changed |= add(i); 
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean changed = false;
		for (int i=Long.SIZE-1; i>=0; i--){
			if ((l&(1L<<i)) != 0){
				if (!c.contains(i)){
					l &= ~(1L<<i);
					changed = true;
				}
			}
		}
		return changed;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean changed = false;
		for (Object o : c){
			changed |= remove(o); 
		}
		return changed;
	}

	@Override
	public void clear() {
		l = 0L;
	}
	
	@Override
	public String toString(){
		return Long.toString(l, 2);
	}

}
