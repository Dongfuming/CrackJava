/**
 * 
 */
package algorithm.sort;

import org.junit.Assert;


/**
 * 选择排序，大约需要 N^2/2 次比较和 N 次交换
 * @author Dongfuming
 * @date 2017-4-11 上午11:42:40
 */
public class Selection extends BaseSort {
  
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i+1; j < n; j++) {
				if (less(a[j], a[min])) {
					min = j;
				}
			}
			exch(a, i, min);
		}
	}
	
	public static void main(String[] args) {
		String[] a = getArrayString();
		sort(a);
		Assert.assertTrue("排序错误", isSorted(a));
		show(a);
	}
}
