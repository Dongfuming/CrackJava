/**
 * 
 */
package algorithm.sort;

import org.junit.Assert;

/**
 * @author Dongfuming
 * @date 2017-4-11 下午3:12:26
 */
public class Shell extends BaseSort {

	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		int h = 1; // 递增序列，1、4、13、40、121、364、1093...
		while (h < a.length/3) {
			h = 3 * h + 1; 
		}
		while (h >= 1) {
			for (int i = h; i < a.length; i++) {
				for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
					exch(a, j, j-h);
				}
			}
			h /= 3;
		}
	}
	
	public static void main(String[] args) {
		String[] a = getArrayString();
		sort(a);
		Assert.assertTrue("排序错误", isSorted(a));
		show(a);
	}
}
