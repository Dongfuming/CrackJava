/**
 * 
 */
package algorithm.chapter1_foundation;

/**
 * 动态连通
 * 加权quick-union, find--log(N), union - log(N)
 * 路径压缩加权quick-union, find --> 1, union --> 1
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
		validate(p);
		while (p != ids[p]) {
			p = ids[p];
		}
		return p;
	}
	
	private void validate(int p) {
		if (p < 0 || p >= ids.length) {
			throw new IllegalArgumentException("入参错误");
		}
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
	
	/** 路径压缩加权quick-union */
	public int find2(int p) {
		validate(p);
		while (p != ids[p]) {
			// 将在路径上遇到的所有节点都直接链接到根节点
			// 得到几乎完全扁平化的树
			ids[p] = ids[ids[p]];
			p = ids[p];
		}
		return p;
	}
	
	public void union2(int p, int q) {
		int i = find2(p);
		int j = find2(q);
		
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
	
	public boolean isConnected2(int p, int q) {
		return find2(p) == find2(q);
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
			if (! uf.isConnected2(p, q)) {
				uf.union2(p, q);
				System.out.printf("p = %d, q = %d\n", p, q);
			}
		}
		System.out.println(uf.count() + "个连接");
	}
}
