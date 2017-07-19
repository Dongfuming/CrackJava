package book.corejava1;

import book.corejava1.object.Employee;
import book.corejava1.object.Manager;

/**
 * 值传递 or 引用传递
 * @author Dongfuming
 * @date 2016-7-2 上午10:18:43
 */
public class Demo03 {

	public static void main(String[] args) {
		Employee e111 = new Employee("e111", 111.0);
		Employee e222 = new Employee("e222", 222.0);

		Manager m111 = new Manager("e111", 111.0, 111.0);
		
		System.out.println("传参前 e111 = " + e111);
		tripleSalary(e111);
		System.out.println("传参后 e111 = " + e111);
		System.out.println("e111.salary = " + e111.getSalary());
		
		swapEmployee(e111, e222);

		System.out.println("e111 == e222 ? " + e111.equals(e222));
		System.out.println("e111 == m111 ? " + e111.equals(m111));
		System.out.println("m111 instanceof Employee ? " + (m111 instanceof Object));

		System.out.println();
		System.out.println(e111.hashCode());
		System.out.println(m111.hashCode());
	}
	
	public static void swapEmployee(Employee aaa, Employee bbb) {
		System.out.println("22222 e111 = " + aaa + ", e222 = " + bbb);
		
		aaa = new Employee();
		bbb = new Employee();
		
		System.out.println("33333 e111 = " + aaa + ", e222 = " + bbb);
	}
	
	public static void tripleValue(double value) {
		value *= 3;
		System.out.println("value = " + value);
	}
	
	public static void tripleSalary(Employee e) {
		System.out.println("参数 e = " + e);
		
		Double salary = e.getSalary();
		salary *= 3;
		e.setSalary(salary);
	}
}
