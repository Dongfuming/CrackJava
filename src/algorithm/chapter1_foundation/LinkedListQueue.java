/**
 * 
 */
package algorithm.chapter1_foundation;

import java.util.Iterator;

/**
 * 队列，链表实现
 * @author Dongfuming
 * @date 2017-6-26 下午10:21:36
 */
public class LinkedListQueue<T> implements Iterable<T> {

	private Node head;
	private Node tail;
	private int size;
	
	private class Node {
		T item;
		Node next;
	}
	
	public void enqueue(T item) {
		Node oldTail = tail;
		tail = new Node();
		tail.item = item;
		tail.next = null;
		if (isEmpty()) {
			head = tail;
		} else {
			oldTail.next = tail;
		}
		size++;
	}
	
	public T dequeue() {
		T item = head.item;
		head = head.next;
		if (isEmpty()) {
			tail = null;
		}
		size--;
		return item;
	}
	
	public boolean isEmpty() {
		return (head == null);
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new InnerIterator();
	}

	private class InnerIterator implements Iterator<T> {

		private Node cursor = head;
		
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
			throw new UnsupportedOperationException("避免在迭代中修改数据结构");
		}
	}
	
	public static void main(String[] args) {
		LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
		queue.enqueue(111);
		queue.enqueue(222);
		queue.enqueue(333);
		queue.enqueue(444);
		
		System.out.println("dequeue = " + queue.dequeue());
		
		for (Integer integer : queue) {
			System.out.println(integer);
		}
	}
}
