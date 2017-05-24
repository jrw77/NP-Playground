package de.ostfalia.weimar.reduction;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetLongBased implements Set<Integer> {
	long l;
	
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
		if (i >= Long.SIZE){
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
		throw new UnsupportedOperationException(" Not yet done!");
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException(" Not yet done!");
	}

	@Override
	public boolean add(Integer i) {
		if (i>= Long.SIZE){
			throw new RuntimeException("Way too big!");
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
		if (i>= Long.SIZE){
			throw new RuntimeException("Way too big!");
		}
		boolean old = (l & (1L<<i)) != 0;
		l &= ~(1L<<i);
		return old;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c){
			if (! contains(o)){
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
		throw new UnsupportedOperationException(" Not yet done!");
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
