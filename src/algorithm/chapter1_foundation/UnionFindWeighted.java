/**
 * 
 */
package algorithm.chapter1_foundation;

/**
 * 动态连通，加权quick-union
 * @author Dongfuming
 * @date 2017-7-20 下午3:54:47
 */
public class UnionFindWeighted {
	
	private int[] ids; // 父链接数组
	private int count; // 连通分量的数量
	private int[] sz; // 各个根切点所对应分量的大小
	
	public UnionFindWeighted(int n) {
		count = n;
		ids = new int[n];
		sz = new int[n];
		for (int i = 0; i < n; i++) {
			ids[i] = i;
			sz[i] = 1; 
		}
	}
	
	public int count() {
		return count;
	}
	
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}
	
	public int find(int p) {
		while (p != ids[p]) {
			p = ids[p];
		}
		return p;
	}
	
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		
		// 将小树的根节点连接到大树的根节点
		if (sz[i] < sz[j]) {
			ids[i] = j;
			sz[j] += sz[i];
		} else {
			ids[j] = i;
			sz[i] += sz[j];
		}
		count--;
	}
	
	public static void main(String[] args) {
		LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
		queue.enqueue(4); queue.enqueue(3);
		queue.enqueue(3); queue.enqueue(8);
		queue.enqueue(6); queue.enqueue(5);
		queue.enqueue(9); queue.enqueue(4);
		queue.enqueue(2); queue.enqueue(1);
		queue.enqueue(8); queue.enqueue(9);
		queue.enqueue(5); queue.enqueue(0);
		queue.enqueue(7); queue.enqueue(2);
		queue.enqueue(6); queue.enqueue(1);
		queue.enqueue(1); queue.enqueue(0);
		queue.enqueue(6); queue.enqueue(7);
		
		UnionFindWeighted uf = new UnionFindWeighted(10);
		while (! queue.isEmpty()) {
			int p = queue.dequeue();
			int q = queue.dequeue();
			if (! uf.isConnected(p, q)) {
				uf.union(p, q);
				System.out.printf("p = %d, q = %d\n", p, q);
			}
		}
		System.out.println(uf.count() + "个连接");
	}
}
