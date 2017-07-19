package demos.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

/**
 * 浅拷贝 && 深拷贝
 * @author Dongfuming
 * @date 2016-03-29
 */
public class Person implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 101L;
	private int age;
	private String name;
	private Address address;
	
	public Person (String name, int age, Address address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Object deepClone() throws IOException, ClassNotFoundException {
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
		objectOutputStream.writeObject(this);
		
		ByteArrayInputStream byteInputeStream = new ByteArrayInputStream(byteOutputStream.toByteArray());
		ObjectInputStream objectInputStream = new ObjectInputStream(byteInputeStream);

		return objectInputStream.readObject();
	}
	
	@Override
	public String toString() {
		return "[ name = " + this.name + ", age = " + this.age + ", city = " + address.getCity() + " ]";  
	}
	
	public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException, IOException {
		Address addr1 = new Address("广州天河");
		
		Person p1 = new Person("aaa", 111, addr1);
		Person p2 = (Person)p1.clone();
		Person p3 = (Person)p1.deepClone();
		
		System.out.println("\np1 = " + p1);
		System.out.println("p2 = " + p2);
		System.out.println("p3 = " + p3);
		
		p2.setName("bbb");
		p2.setAge(222);
		p2.getAddress().setCity("赣州大余");
		System.out.println("\np1 = " + p1);
		System.out.println("p2 = " + p2);
		System.out.println("p3 = " + p3);
		
		p3.setName("ccc");
		p3.setAge(333);
		p3.getAddress().setCity("深圳罗湖");
		System.out.println("\np1 = " + p1);
		System.out.println("p2 = " + p2);
		System.out.println("p3 = " + p3);
	}
	
	@Test
	public void unitTest() {
		System.out.println(111111111);
	}
}

class Address implements Serializable {
	private static final long serialVersionUID = 1001L;
	private String city;
	
	public Address(String city) {
		super();
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
