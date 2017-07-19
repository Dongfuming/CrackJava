/**
 * 
 */
package algorithm.chapter1_foundation;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 栈，链表实现
 * @author Dongfuming
 * @date 2017-6-26 下午9:54:01
 */
public class LinkedListStack<T> implements Iterable<T>  {
	
	private Node first;
	private int size;
	
	private class Node {
		T item;
		Node next;
	}
	
	public void push(T item) {
		Node old = first;
		first = new Node();
		first.item = item;
		first.next = old;
		size++;
	}
	
	public T pop() {
		T item = first.item;
		first = first.next;
		size--;
		return item;
	}
	
	/** 返回栈中最近添加的元素 */
	public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack underflow");
		}
		T item = first.item;
		return item;
	}
	
	public boolean isEmpty() {
		return (first == null);
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
			return cursor != null;
		}

		@Override
		public T next() {
			T item = cursor.item;
			cursor = cursor.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("避免在迭代中修改数据结构");
		}
	}
	
	public static void main(String[] args) {
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
		stack.push(111);
		stack.push(222);
		stack.push(333);
		
		System.out.println("pop = " + stack.pop());
		
		for (Integer integer : stack) {
			System.out.println(integer);
		}
	}
}
