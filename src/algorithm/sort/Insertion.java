/**
 * 
 */
package algorithm.sort;

import org.junit.Assert;

/**
 * 插入排序，平均情况下需要 N^2/4 次比较和 N^2/4 次交换
 * @author Dongfuming
 * @date 2017-4-11 下午2:04:00
 */
public class Insertion extends BaseSort {

	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
		}
	}
	
	public static void main(String[] args) {
		String[] a = getArrayString();
		sort(a);
		Assert.assertTrue("排序错误", isSorted(a));
		show(a);
	}
}
