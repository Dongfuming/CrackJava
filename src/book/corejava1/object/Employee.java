package book.corejava1.object;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;


/**
 * Cloneable接口表明此对象需要克隆功能，需要重写父类的clone()方法
 * @author Dongfuming
 * @date 2016-7-2 上午10:16:53
 */
public class Employee implements Comparable<Employee>, Cloneable {
	
	private String name; // 不要用proctected修饰
	private Double salary;
	private Date hireDay = new Date();
	
	public Employee() { }
	
	public Employee(String name, Double salary) {
		super();
		this.name = name;
		this.salary = salary;
	}

	public void raiseSalary(double percent) {
		this.salary *= (1 + percent/100);
	}
	
	public void setHireDay(int year, int month, int day) {
		Date newHireDay = new GregorianCalendar(year, month -1, day).getTime();
		this.hireDay.setTime(newHireDay.getTime());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Double getSalary() {
		return salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Employee other = (Employee)obj;
		
		return this.name.equals(other.name) && 
			   this.salary.equals(other.salary);
	}
	
//	@Override
//	public int hashCode() {
//		return Objects.hash(this.name, this.salary);
//	}

//	@Override
//	public String toString() {
//		return "Employee [name=" + name + ", salary=" + salary + ", hireDay="
//				+ hireDay + "]";
//	}

	@Override
	public int compareTo(Employee other) {
		return Double.compare(this.salary, other.salary);
	}
	
	/** 深拷贝 */
	@Override
	public Employee clone() throws CloneNotSupportedException {
		Employee cloned = (Employee)super.clone();
		cloned.hireDay = (Date) hireDay.clone();
		
		return cloned;
	}
	
	public String test(Employee e, String... args) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchMethodException, InvocationTargetException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		for (String fieldName : args) {
			String method = "get" + new String(new char[]{fieldName.charAt(0)}).toUpperCase() + fieldName.substring(1);
			Method getter = e.getClass().getDeclaredMethod(method);
			Object value = getter.invoke(e);

			map.put(fieldName, value);
		}
		/*
		List<String> list = Arrays.asList(args);
		Field[] declaredFields = e.getClass().getDeclaredFields();  
		for (Field field : declaredFields) {
			if (list.contains(field.getName())) {
				map.put(field.getName(), (String)field.get(e));
			}
		}
		*/
		
		System.out.println("map = " + map.toString());
		return map.toString();
	}
	
	public static void main(String[] args) {
		Employee e = new Employee();
		e.setName("dong");
		e.setSalary(1000.0);
		
		try {
			String text = e.test(e, "name");
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
	}
}
