/**
 * 
 */
package algorithm.chapter1_foundation;

import java.util.Iterator;

/**
 * 背包，链表实现
 * @author Dongfuming
 * @date 2017-6-27 上午9:28:06
 */
public class LinkedListBag<T> implements Iterable<T> {

	private Node first;
	private int size;
	
	private class Node {
		T item;
		Node next;
	}
	
	public void add(T item) {
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		size++;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new InnerIterator();
	}
	
	private class InnerIterator implements Iterator<T> {

		private Node cursor = first;
		
		@Override
		public boolean hasNext() {
			return (cursor != null);
		}

		@Override
		public T next() {
			T item = cursor.item;
			cursor = cursor.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("避免迭代时修改数据结构");
		}
	}
	
	public static void main(String[] args) {
		LinkedListBag<Integer> bag = new LinkedListBag<Integer>();
		bag.add(111);
		bag.add(222);
		bag.add(333);
		
		for (Integer integer : bag) {
			System.out.println(integer);
		}
	}

}
