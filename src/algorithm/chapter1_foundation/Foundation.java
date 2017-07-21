/**
 * 
 */
package algorithm.chapter1_foundation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 算法第4版，第一章-基础
 * @author Dongfuming
 * @date 2017-6-23 下午10:25:12
 */
public class Foundation {

	/** 求最大公约数，递归算法 */
	public static int gcd_recursion(int a, int b) {
		if (b == 0) { return a; };
		int r = a % b;
		return gcd_recursion(b, r);
	}
	
	/** 二分查找，迭代版，前提：数组已排序 */
	public static int binarySearch(int key, int[] a) {
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (key < a[mid]) {
				high = mid - 1;
			} else if (key > a[mid]) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	/** 二分查找，递归版，前提：数组已排序 */
	public static int binarySearch_recursion(int key, int[] a) {
		return binarySearch_recursion_helper(key, a, 0, a.length);
	}
	
	public static int binarySearch_recursion_helper(int key, int[] a, int low, int high) {
		if (low > high) {
			return -1;
		}
		int mid = low + (high - low) / 2;
		if (key < a[mid]) {
			return binarySearch_recursion_helper(key, a, low, mid - 1);
		} else if (key > a[mid]) {
			return binarySearch_recursion_helper(key, a, mid + 1, high);
		} else {
			return mid;
		}
	}
	
	/** 倒转数组元素 */
	public static void reverse(int[] a) {
		int left = 0;
		int right = a.length - 1;
		
		while (left < right) {
			int leftValue = a[left];
			a[left] = a[right];
			a[right] = leftValue;
			left++;
			right --;
		}
	}
	
	/** 计算平方根，牛顿迭代法 */
	public static double sqrt(double x) {
		if (x < 0) { throw new RuntimeException("illegal input"); }
		
		double tolerance = 0.00001;
		double result = x;
		while (Math.abs(result - x/result) > tolerance) {
			result = (x/result + result) / 2.0;
		}
		return result;
	}
	
	/** 判断素数 */
	public static boolean isPrime(int x) {
		if (x < 2) { return false; }
		
		for (int i = 2; i*i <= x; i++) {
			System.out.println("i = " + i);
			if (x % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/** 随机返回 [a, b] 之间的一个double值 */
	public static double random(double a, double b) {
		return a + Math.random() * (b - a);
	}
	
	/** 随机返回 [0, x] 之间的一个int值 */
	public static int random(int x) {
		return (int) (Math.random() * x);
	}
	
	/** 随机返回 [a, b] 之间的一个int值 */
	public static int random(int a, int b) {
		return a + random(b - a);
	}
	
	/** 根据离散概率随机返回的int值(出现i的概率为a[i]), a[]中元素之和必须等于1 */
	public static int discreteRandom(double[] a) {
		double r = Math.random();
		System.out.println("r = " + r);
		double sum = 0.0;
		for (int i = 0; i < a.length; i++) {
			sum = sum + a[i];
			if (sum >= r) { return i; };
		}
		return -1;
	}
	
	/** 随机将double数组中的元素打乱 */
	public static void shuffle(double[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			// 将a[i]和a[i..n-1]中任意一个元素交换
			int r = i + random(n - i);
			System.out.println("r = " + r);
			double temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}
	
	/** 将一个整数转换为二进制的字符串 */
	public static String integerToBinaryString(int x) {
		String result = "";
		for (int i = x;  i > 0;  i /= 2) {
			result = (i % 2) + result;
		}
		return result;
	}
	
	/** 判断字符串是否是一条回文 */
	public static boolean isPalindrome(String s) {
		for (int i = 0; i < s.length()/2; i ++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
	
	/** 检查字符串数组中的元素是否已按字母表排序 */
	public static boolean isSourted(String[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[i-1].compareTo(a[i]) > 0) {
				return false;
			}
		}
		return true;
	}
	
	/** 递归倒转字符串 */
	public static String mystery(String s) {
		int len = s.length();
		if (len <= 1) {
			return s;
		}
		String a = s.substring(0, len/2);
		String b = s.substring(len/2, len);
		return mystery(b) + mystery(a);
	}
	
	/** 判断输入的年、月、日是否有效 */
	public static boolean isValidDayMonthYear(int day, int month, int year) {
		final int[] dayArray = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (month < 1 || month > 12) {
			return false;
		}
		if (day < 1 || day > dayArray[month]) {
			return false;
		}
		if (month == 2 && day == 29 && !isLeapYear(year)) {
			return false;
		}
		return true;
	}
	
	/** 判断是否为闰年 */
	public static boolean isLeapYear(int year) {
		if (year % 400 == 0) {
			return true;
		}
		if (year % 100 == 0) {
			return false;
		}
		return (year % 4 == 0);
	}
	
	/** Dijkstra的双栈算术表达式求值算法, 如 "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )" ==> 101.0; "( 1 + ( sqrt ( 5.0 ) ) / 2 )" ==> 1.6180 */
	public static double evaluate(String expression) {
		Stack<Double> valueStack = new Stack<Double>(); // 操作数
		Stack<String> operatorStack = new Stack<String>(); // 运算符
		
		// 以一个或多个制表符、空格、换行或回车分割
		String[] itemArray = expression.split("\\s+"); 
		for (int i = 0; i < itemArray.length; i++) {
			String item = itemArray[i];
			if (item.equals("(")) { 
				// 遇到'('时，运算符入栈
			} else if ("+".equals(item)) {
				operatorStack.push(item);
			} else if ("-".equals(item)) {
				operatorStack.push(item);
			} else if ("*".equals(item)) {
				operatorStack.push(item);
			} else if ("/".equals(item)) {
				operatorStack.push(item);
			} else if ("sqrt".equals(item)) {
				operatorStack.push(item);
			} else if (")".equals(item)) {
				// 遇到')'时，弹出运算符和操作数，计算数值并压入栈
				String operator = operatorStack.pop();
				double value = valueStack.pop();
				if ("+".equals(operator)) {
					value = valueStack.pop() + value;
				} else if ("-".equals(operator)) {
					value = valueStack.pop() - value;
				} else if ("*".equals(operator)) {
					value = valueStack.pop() * value;
				} else if ("/".equals(operator)) {
					value = valueStack.pop() / value;
				} else if ("sqrt".equals(operator)) {
					value = Math.sqrt(value);
				}
				valueStack.push(value);
			} else {
				// 遇到操作数，入栈
				valueStack.push(Double.parseDouble(item));
			}
		}
		return valueStack.pop();
	}
	
	private static final char LEFT_PAREN     = '(';
    private static final char RIGHT_PAREN    = ')';
    private static final char LEFT_BRACKET   = '[';
    private static final char RIGHT_BRACKET  = ']';
    private static final char LEFT_BRACE     = '{';
    private static final char RIGHT_BRACE    = '}';
	/** 检查大、中、小括号是否配对, 如 "[()]{}{[()()]()}" ==> ture; "[(])" ==> false */
	public static boolean isBalancedOfParentheses(String expression) {
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		
		for (int i = 0; i < expression.length(); i++) {
			Character item = expression.charAt(i);
			if (LEFT_PAREN == item || LEFT_BRACKET == item || LEFT_BRACE == item) {
				stack.push(item);
			} else if (RIGHT_PAREN == item) {
				if (stack.isEmpty() || LEFT_PAREN != stack.pop()) {
					return false;
				}
			} else if (RIGHT_BRACKET == item) {
				if (stack.isEmpty() || LEFT_BRACKET != stack.pop()) {
					return false;
				}
			} else if (RIGHT_BRACE == item) {
				if (stack.isEmpty() || LEFT_BRACE != stack.pop()) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
	
	/** 中序列表式转为后序表达式，如 ( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) ) ==> 2 3 4 + 5 6 * * +  */
	public static String infixToPostfix(String expression) {
		LinkedListStack<String> stack = new LinkedListStack<String>();
		String[] itemArray = expression.split("\\s+");
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < itemArray.length; i++) {
			String item = itemArray[i];
			if ("+".equals(item) || "-".equals(item) || "*".equals(item) || "/".equals(item)) {
				stack.push(item);
			} else if ("(".equals(item)) {
				
			} else if (")".equals(item)) {
				result.append(stack.pop() + " ");
			} else {
				result.append(item + " ");
			}
		}
		return result.toString();
	}
	
	/** 对后序表达式求值，如 3 8 5 - * ==> 9.0 */
	public static double evaluatePostfix(String expression) {
		LinkedListStack<Double> stack = new LinkedListStack<Double>();
		String[] itemArray = expression.split("\\s+");
		
		for (int i = 0; i < itemArray.length; i++) {
			String item = itemArray[i];
			if ("+".equals(item)) {
				stack.push(stack.pop() + stack.pop());
			} else if ("-".equals(item)) {
				Double first = stack.pop();
				Double second = stack.pop();
				stack.push(second - first);
			} else if ("*".equals(item)) {
				stack.push(stack.pop() * stack.pop());
			} else if ("/".equals(item)) {
				Double first = stack.pop();
				Double second = stack.pop();
				stack.push(second / first);
			} else {
				stack.push(Double.parseDouble(item));
			}
		}
		return stack.pop();
	}
	
	/** 据说著名犹太历史学家 Josephus有过以下的故事：在罗马人占领乔塔帕特後，39 个犹太人与Josephus及他的朋友躲到一个洞中，
	 * 39个犹太人决定宁愿死也不要被人抓到，于是决定了一个自杀方式，41个人排成一个圆圈，由第1个人开始报数，每报数到第3人该人就必须自杀，
	 * 然后再由下一个重新报数，直到所有人都自杀身亡为止。然而Josephus 和他的朋友并不想遵从，Josephus要他的朋友先假装遵从，
	 * 他将朋友与自己安排在第16个与第31个位置，于是逃过了这场死亡游戏 
	 */
	public static void JosephusProblem(int totalNum, int curseNum) {
		LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
		
		for (int i = 1; i <= totalNum; i++) {
			queue.enqueue(i);
		}
		while (! queue.isEmpty()) {
			for (int i = 0; i < curseNum - 1; i ++) {
				queue.enqueue(queue.dequeue());
			}
			int outNo = queue.dequeue(); 
			System.out.println(outNo + " ");
		}
	}
	
	public static void findDuplicateNum(int[] a) {
		List<Integer> uniqueNumList = new LinkedList<Integer>();
		for (int i = 0; i < a.length; i++) {
			if (uniqueNumList.contains(a[i])) {
				System.out.printf("重复的num = %d, index = %d\n", a[i], i);
			} else {
				uniqueNumList.add(a[i]);
			}
		}
	}
	
	public static void findDuplicateNum2(int[] a) {
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		
		for (int i = 0; i < a.length; i++) {
			if (binarySearch(a[i], a) > i+1) {
				System.out.printf("重复的num = %d, index = %d\n", a[i], i);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] a = {5, 4, 3, 2, 1, 3, 3};
		//findDuplicateNum(a);
		findDuplicateNum2(a);
	}
}
