package book.corejava1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 利用反射打印类的所有信息
 * @author Dongfuming
 * @date 2016-7-4 下午10:05:36
 */
public class Demo04 {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws ClassNotFoundException {
		
		String className = "java.lang.String";
		Class clazz = Class.forName(className);
		
		printClass(clazz);
		
		System.out.println();
		printFields(clazz);
		
		System.out.println();
		printConstructors(clazz);
		
		System.out.println();
		printMethods(clazz);
		
		printTail();
		
		System.out.println("---------");
	}
	
	/** 打印类名 */
	@SuppressWarnings("rawtypes")
	private static void printClass(Class clazz) {
		String modifiers = Modifier.toString(clazz.getModifiers());
		System.out.print(modifiers + " ");
		System.out.print("class " + clazz.getName());
		
		Class superClazz = clazz.getSuperclass();
		if (superClazz != null && superClazz != Object.class) {
			System.out.print("extends " + superClazz.getName());
		}
		System.out.println(" {");
	}
	
	/** 打印属性 */
	@SuppressWarnings("rawtypes")
	private static void printFields(Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Class type = field.getType();
			String name = field.getName();
			String modifier = Modifier.toString(field.getModifiers());
			System.out.print("    " + (modifier.length() > 0 ? modifier: "") + " ");
			System.out.println(type.getSimpleName() + " " + name + ";");
		}
	}
	
	/** 打印构造函数 */
	@SuppressWarnings("rawtypes")
	private static void printConstructors(Class clazz) {
		Constructor[] constructors = clazz.getDeclaredConstructors();
		for (Constructor constructor : constructors) {
			String name = constructor.getName();
			String modifier = Modifier.toString(constructor.getModifiers());
			System.out.print("    " + modifier + " ");
			System.out.print(name + "(");
			
			Class[] parameterTypes = constructor.getParameterTypes();
			for (int i = 0; i < parameterTypes.length; i++) {
				if (i > 0) { System.out.print(", "); }
				System.out.print(parameterTypes[i].getSimpleName());
			}
			System.out.println(");");
		}
	}
	
	/** 打印方法 */
	@SuppressWarnings("rawtypes")
	private static void printMethods(Class clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			String modifier = Modifier.toString(method.getModifiers());
			String name = method.getName();
			System.out.print("    " + modifier + " ");
			System.out.print(name + "(");
			
			Class[] parameterTypes = method.getParameterTypes();
			for (int i = 0; i < parameterTypes.length; i++) {
				if (i > 0) { System.out.print(", "); }
				System.out.print(parameterTypes[i].getSimpleName());
			}
			System.out.println(");");
		}
	}
	
	private static void printTail() {
		System.out.println("}");
	}
}
