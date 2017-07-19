/**
 * 
 */
package algorithm.chapter1_foundation;


/**
 * 链表
 * @author Dongfuming
 * @date 2017-6-26 下午9:26:31
 */
public class LinkedList {

	private class Node<T> {
		T item;
		Node<T> next;
		public Node(T item) {
			this.item = item;
		}
	}
	
	public void test() {
		Node<Integer> first = new Node<Integer>(111);
		Node<Integer> second = new Node<Integer>(222);
		Node<Integer> third = new Node<Integer>(333);
		
		first.next = second;
		second.next = third;
		
		for (Node<Integer> x = first; x != null; x = x.next) {
			System.out.println("item = " + x.item);
		}
	}
	
	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
		linkedList.test();
	}

}
