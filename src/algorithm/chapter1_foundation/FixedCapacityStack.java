/**
 * 
 */
package algorithm.chapter1_foundation;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 固定容量大小的栈
 * @author Dongfuming
 * @date 2017-6-25 下午9:02:06
 */
public class FixedCapacityStack<E> implements Iterable<E> {
	
	private E[] items;
	private int size;
	
	@SuppressWarnings("unchecked")
	public FixedCapacityStack(int cap) {
		items = (E[]) new Object[cap];
	}
	
	public void push(E item) {
		if (size == items.length) {
			throw new RuntimeException("栈已满");
		}
		items[size++] = item;
	}
	
	public E pop() {
		if (size == 0) { throw new RuntimeException("栈为空"); }
		E item = items[--size];
		items[size] = null; // 避免对象游离
		return item;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public boolean isFull() {
		return (size == items.length);
	}
	
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<E> {

		private int cursor = size;
		
		@Override
		public boolean hasNext() {
			return cursor > 0;
		}

		@Override
		public E next() {
			if (cursor == 0) {
				throw new NoSuchElementException("栈已空");
			}
			cursor--;
			return items[cursor];
		}

		@Override
		public void remove() { 
			throw new UnsupportedOperationException("避免在迭代中修改数据结构"); 
		}
	}
	
	public static void main(String[] args) {
		FixedCapacityStack<Integer> stack = new FixedCapacityStack<Integer>(10);
		stack.push(111);
		stack.push(222);
		stack.push(333);
		stack.push(444);
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
