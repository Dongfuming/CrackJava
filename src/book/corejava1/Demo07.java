package book.corejava1;

import java.util.Arrays;

import book.corejava1.object.Employee;

/**
 * 接口 和 clone
 * @author Dongfuming
 * @date 2016-7-6 上午11:13:46
 */
public class Demo07 {

	public static void main(String[] args) throws CloneNotSupportedException {
		Employee[] employees = new Employee[3]; 
		employees[0] = new Employee("AAA", 1003.0);
		employees[1] = new Employee("BBB", 1001.0);
		employees[2] = new Employee("CCC", 1002.0);
		
		Arrays.sort(employees);
		for (Employee employee : employees) {
			System.out.println(employee);
		}
		
		Employee e1 = new Employee("dongfuming", 3000.0);
		e1.setHireDay(2016, 7, 6);
		
		Employee e1Clone = e1.clone();
		e1Clone.setName("1212");
		System.out.println(e1.getName());
		System.out.println(e1Clone.getName());
		
		int[] luckyNumbers = { 2, 3, 5, 7, 11, 13 };
		// 所有的数组类型均包含一个pulic的clone方法
		int[] clonedNumbers = luckyNumbers.clone();
		clonedNumbers[0] = 1212;
		System.out.println(luckyNumbers[0]);
		
		Employee original = new Employee("original", 1000.0);
		original.setHireDay(2000, 1, 1);
		
		Employee copyOriginal = original.clone();
		copyOriginal.setName("copyOriginal");
		copyOriginal.raiseSalary(10);
		copyOriginal.setHireDay(2016, 1, 1);
		
		System.out.println(original);
		System.out.println(copyOriginal);
	}
}
