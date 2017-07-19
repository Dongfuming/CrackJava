/**
 * 
 */
package algorithm.chapter1_foundation;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 队列，先进先出
 * 数组实现，大小动态调整
 * @author Dongfuming
 * @date 2017-6-25 下午3:54:55
 */
public class Queue<T> implements Iterable<T> {
	
	@SuppressWarnings("unchecked")
	private T[] dataArray = (T[]) new Object[2];
	private int head = 0;
	private int tail = 0;

	public void enqueue(T item) {
		if ((tail - head) == dataArray.length) {
			resize(2 * dataArray.length);
		}
		dataArray[tail++] = item;
	}
	
	public T dequeue() {
		T item = dataArray[head];
		dataArray[head] = null;
		head++;
		if ((tail - head) == dataArray.length/4) {
			resize(dataArray.length / 2);
			int size = tail - head;
			head = 0;
			tail = size;
		}
		return item;
	}
	
	public boolean isEmpty() {
		return (head == tail);
	}
	
	public int size() {
		return (tail - head);
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int max) {
		T[] temp = (T[]) new Object[max];
		for (int i = head; i < tail; i++) {
			temp[i-head] = dataArray[i];
		}
		dataArray = temp;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new InnerIterator();
	}
	
	private class InnerIterator implements Iterator<T> {
		int cursor = head;
		
		@Override
		public boolean hasNext() {
			return (cursor < tail);
		}

		@Override
		public T next() {
			if (cursor == tail) {
				throw new NoSuchElementException("已到尽头");
			}
			return dataArray[cursor++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("避免迭代时修改数据结构");
		}
	}
	
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(111);
		queue.enqueue(222);
		queue.enqueue(333);
		queue.enqueue(444);
		queue.enqueue(555);
		queue.enqueue(666);
		queue.enqueue(777);
		queue.enqueue(888);
		
		for (Integer integer : queue) {
			System.out.println(integer);
		}
		
		System.out.println("dequeue = " + queue.dequeue());
		System.out.println("dequeue = " + queue.dequeue());
		System.out.println("dequeue = " + queue.dequeue());
		System.out.println("dequeue = " + queue.dequeue());
		System.out.println("dequeue = " + queue.dequeue());
		System.out.println("dequeue = " + queue.dequeue());
		
		for (Integer integer : queue) {
			System.out.println(integer);
		}
 	}
}
