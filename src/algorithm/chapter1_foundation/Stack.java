/**
 * 
 */
package algorithm.chapter1_foundation;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 栈，先进后出
 * 数组实现，大小动态调整
 * @author Dongfuming
 * @date 2017-6-25 下午4:01:22
 */
public class Stack<T> implements Iterable<T> {

	@SuppressWarnings("unchecked")
	private T[] dataArray = dataArray = (T[]) new Object[1];
	private int size = 0;
	
	public void push(T item) {
		if (size == dataArray.length) {
			resize(2 * dataArray.length);
		}
		dataArray[size++] = item;
	}
	
	public T pop() {
		T item = dataArray[--size];
		dataArray[size] = null;
		if (size > 0 && size == dataArray.length/4) {
			resize(dataArray.length / 2);
		}
		return item;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public int size() {
		return size;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int max) {
		T[] temp = (T[]) new Object[max];
		for (int i = 0; i < size; i++) {
			temp[i] = dataArray[i];
		}
		dataArray = temp;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<T> {
		
		int cursor = size;
		
		@Override
		public boolean hasNext() {
			return cursor > 0;
		}

		@Override
		public T next() {
			if (cursor == 0) {
				throw new NoSuchElementException("已到栈底");
			}
			T next = dataArray[--cursor];
			return next;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("避免在迭代中修改数据结构");
		}
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(111);
		stack.push(222);
		stack.push(333);
		stack.push(444);
		
		Iterator<Integer> iterator = stack.iterator();
		while (iterator.hasNext()) {
			Integer integer = (Integer) iterator.next();
			System.out.println(integer);
		}
		
		for (Integer integer : stack) {
			System.out.println("integer in stack: " + integer);
		}
	}
}
