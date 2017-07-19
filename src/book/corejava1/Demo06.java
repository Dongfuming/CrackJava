/**
 * 
 */
package book.corejava1;

import java.lang.reflect.Method;

import book.corejava1.object.Employee;

/**
 * 反射调用任意方法
 * @author Dongfuming
 * @date 2016-7-6 上午9:35:52
 */
public class Demo06 {

	public static void main(String[] args) throws Exception {

		Employee e1 = new Employee("dongfuming", 1000.0);
		
		Method m1 = Employee.class.getMethod("getName");
		Method m2 = Employee.class.getMethod("raiseSalary", double.class);
		
		Object nameValue = m1.invoke(e1);
		System.out.println("name value = " + nameValue);
		
		Object m2Value = m2.invoke(e1, 10.0);
		System.out.println("m2 value = " + m2Value);
		System.out.println("e1 salary = " + e1.getSalary());
		
		System.out.println();
		printTable(1, 10, 10, Math.class.getMethod("sqrt", double.class));
	}

	public static void printTable(double from, double to, int row, Method method) throws Exception {
		System.out.println(method);
		double dx = (to - from) / (row - 1);
		for (double x = from; x <= to; x += dx) {
			double y = (Double) method.invoke(null, x);
			System.out.printf("%-10.2f | %10.2f\n", x , y);
		}
	}
}
