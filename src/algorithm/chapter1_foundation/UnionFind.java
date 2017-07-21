/**
 * 
 */
package algorithm.chapter1_foundation;

/**
 * 动态连通算法
 * @author Dongfuming
 * @date 2017-7-20 上午11:48:54
 */
public class UnionFind {
	
	private int[] ids; // 分量id，以触点为索引
	private int count; // 分量数量

	public UnionFind(int n) {
		this.count = n;
		this.ids = new int[n];
		for (int i = 0; i < n; i++) {
			ids[i] = i;
		}
	}
	
	public int count() {
		return this.count;
	}
	
	private void validate(int p) {
		if (p < 0 || p >= ids.length) {
			throw new IllegalArgumentException("入参错误");
		}
	}
	
	/** ------------ quick find ------------ */
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}
	
	/** 时间复杂度：1 */
	public int find(int p) {
		validate(p);
		return ids[p];
	}
	
	/** 时间复杂度：N */
	public void union(int p, int q) {
		int pID = find(p);
		int qID = find(q);
		if (pID == qID) {
			return;
		}
		
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == pID) {
				ids[i] = qID;
			}
		}
		count--;
	}
	
	/** ------------ quick union ------------ */
	public boolean isConnected2(int p, int q) {
		return find2(p) == find2(q);
	}
	
	/** 时间复杂度：树的高度 */
	public int find2(int p) {
		validate(p);
		while (p != ids[p]) {
			p = ids[p];
		}
		return p;
	}
	
	/** 时间复杂度：树的高度 */
	public void union2(int p, int q) {
		int pRoot = find2(p);
		int qRoot = find2(q);
		
		if (pRoot == qRoot) {
			return;
		}
		ids[pRoot] = qRoot;
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
		
		UnionFind uf = new UnionFind(10);
		while (! queue.isEmpty()) {
			int p = queue.dequeue();
			int q = queue.dequeue();
			if (! uf.isConnected2(p, q)) {
				uf.union2(p, q);
				System.out.printf("p = %d, q = %d\n", p, q);
			}
		}
		System.out.println(uf.count() + "个连接");
	}
}
