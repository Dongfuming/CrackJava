/**
 * 
 */
package algorithm.sort;



/**
 * @author Dongfuming
 * @date 2017-4-11 上午11:19:09
 */
public class BaseSort {
	
	protected static String[] getArrayString() {
		String[] a = {"3", "5", "1", "4", "2"};
		// String[] a = {"1", "1", "1", "1", "1"};
		return a;
	}
	
	protected static Integer[] getArrayInteger() {
		Integer[] a = new Integer[]{3, 5, 1, 4, 2};
		return a;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	@SuppressWarnings("rawtypes")
	protected static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	@SuppressWarnings("rawtypes")
	protected static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i-1])) {
				return false;
			}
		}
		return true;
	}
}
