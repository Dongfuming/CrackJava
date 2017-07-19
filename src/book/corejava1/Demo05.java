package book.corejava1;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

import book.corejava1.object.Employee;
import book.corejava1.object.Manager;

/**
 * 1. 反射获取、设置对象属性
 * 2. 反射写通用的toString方法
 * 3. 反射的Array类copy一个任意数组
 * @author Dongfuming
 * @date 2016-7-5 下午4:44:53
 */
public class Demo05 {
	
	private static ArrayList<Object> visitedArray = new ArrayList<Object>();
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {
		
		Employee e1 = new Employee("Dongfuming", 3000.0);
		Class e1Class = e1.getClass();  
		
		Field field = e1Class.getDeclaredField("name");
		field.setAccessible(true);
		
		Object e1Name = field.get(e1);
		System.out.println(e1Name);
		
		field.set(e1, "dfm");
		System.out.println(e1.getName());
		
		// 测试通用toString方法
		
		System.out.println();
		//System.out.println(objectToString(e1));
		toString(e1);
		System.out.println(e1);
		
		System.out.println();
		Manager m1 = new Manager("xiao cai", 5000.0, 3000.0);
		System.out.println(objectToString(m1));
		toString(m1);
		System.out.println(m1);
		
		System.out.println();
		ArrayList<Integer> squares = new ArrayList<Integer>();
		for (int i = 1; i <= 5; i++) {
			squares.add(i * i);
		}
		//System.out.println(objectToString(squares));
		toString(squares);
		System.out.println(squares);
		
		Integer[] iArray0 = new Integer[] { 1, 4, 9, 16, 25};
		Integer[] iArray1 = Arrays.copyOf(iArray0, squares.size());
		System.out.println(iArray0);
		System.out.println(iArray1);
		
		System.out.println();
		int a[] = { 1, 2, 3, 4, 5 };
		System.out.println("a = " + a + ", values = " + Arrays.toString(a));
		a = (int[]) goodCopyOf(a, 13);
		System.out.println("a = " + a + ", values = " + Arrays.toString(a));
	}
	
	public static Object goodCopyOf(Object array, int newLength) {
		Class<?> clazz = array.getClass();
		if ( ! clazz.isArray()) {
			return null;
		}
		Class<?> componentType = clazz.getComponentType();
		Object newArray = Array.newInstance(componentType, newLength);
		int oldLength = Array.getLength(array);
		System.arraycopy(array, 0, newArray, 0, Math.min(oldLength, newLength));
		
		return newArray;
	}
	
	public static void toString(Object obj) throws Exception {
		Class<?> clazz = obj.getClass();
		String info = clazz.getSimpleName();
		info += " [";
		
		while (clazz != null ) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if ( ! Modifier.isStatic(field.getModifiers())) {
					if ( ! info.endsWith("[")) {
						info += ", ";
					}
					info += (field.getName() + "=");
					Object value = field.get(obj);
					info += value;
				}
			}
			clazz = clazz.getSuperclass();
		}
		
		info += "]";
		System.out.println(info);
	}
	
	public static String objectToString(Object obj) throws Exception {
		if (obj == null) { 
			return "null"; 
		}
		if (visitedArray.contains(obj)) { 
			return ""; 
		}
		visitedArray.add(obj);
		
		Class<? extends Object> clazz = obj.getClass();
		if (clazz == String.class) { 
			return (String)obj; 
		}
		if (clazz.isArray()) {
			String info = clazz.getComponentType() + "[]{";
			for (int i = 0; i < Array.getLength(obj); i++) {
				if (i > 0) {
					info += ", ";
				}
				Object value = Array.get(obj, i);
				if (clazz.getComponentType().isPrimitive()) {
					info += value;
				} else {
					info += objectToString(value);
				}
			}
			return info += "}";
		}
		String info = clazz.getSimpleName();
		while (clazz != null  && clazz != Object.class) {
			info += " [";
			Field[] fields = clazz.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
			for (Field field : fields) {
				if ( ! Modifier.isStatic(field.getModifiers())) {
					if ( ! info.endsWith("[")) {
						info += ", ";
					}
					info += (field.getName() + "=");
					Object value = field.get(obj);
					Class<?> type = field.getType();
					if (type.isPrimitive()) {
						info += value;
					} else {
						info += objectToString(value);
					}
					info += objectToString(value);
				}
			}
			info += "]";
			clazz = clazz.getSuperclass();
		} 
		
		return info;
	}
}
