/**
 * 
 */
package algorithm.chapter1_foundation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 背包
 * @author Dongfuming
 * @date 2017-6-24 下午5:37:10
 */
public class Bag<T> implements Iterable<T> {

	private ArrayList<T> dataList = new ArrayList<T>();
	
	public Bag() {
		super();
	}

	public void add(T item) {
		dataList.add(item);
	}
	
	public boolean isEmpty() {
		return dataList.isEmpty();
	}
	
	public int size() {
		return dataList.size();
	}
	
	@Override
	public Iterator<T> iterator() {
		return new InnerIterator();
	}
	
	private class InnerIterator implements Iterator<T> {
		int cursor = 0;
		
		@Override
		public boolean hasNext() {
			return cursor != dataList.size() ;
		}

		@Override
		public T next() {
			try {
				T next = dataList.get(cursor++);
				return next;
			} catch (IndexOutOfBoundsException e) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			dataList.remove(--cursor);
		}
	}
	
	public static void main(String[] args) {
		Bag<Integer> bag = new Bag<Integer>();
		bag.add(111);
		bag.add(222);
		bag.add(333);
		
		for (Integer integer : bag) {
			System.out.println(integer);
		}
	}

}
