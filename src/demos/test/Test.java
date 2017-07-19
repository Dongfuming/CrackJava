/**
 * 
 */
package demos.test;


/**
 * @author Dongfuming
 * @date 2016-12-5 下午9:36:36
 */
public class Test {
	
	public static void main(String[] args) {
		Person p1 = new Person();
		Person p2 = new Person();
		
		System.out.println(p1);
		System.out.println(p2);
	}
}

class Person {
	private String name;
	private int age;
	
	static {
		System.out.println("静态代码快，只调用一次");
	}
	
	{
		System.out.println("构造代码快，每次创建对象皆执行");
	}
	
	public Person() {
		super();
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
		System.out.println("constructor method revoked");
	}
	
	public void cry() {
		System.out.println("哇...哇...哇...");
	}
	
	@Override
	public String toString() {
		return "Person: name = " + this.name + ", age = " + this.age;
	}
}


