package book.corejava1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * 使用代理跟踪方法的调用
 * 代理类具有：指定接口的所需要的所有方法 和 Object类中的全部方法，如toString、equals等
 * 代理类是在程序运行过程中创建的
 * @author Dongfuming
 * @date 2016-7-6 下午9:50:51
 */
public class Demo09 {

	public static void main(String[] args) {

		Object[] elements = new Object[1000];
		for (int i = 0; i < elements.length; i++) {
			Integer value = i + 1;
			InvocationHandler handler = new TraceHandler(value);
			// null表示系统类的加载器
			Object proxy = Proxy.newProxyInstance(null, new Class[] { Comparable.class }, handler);
			// Integer的代理填充数组
			elements[i] = proxy; 
		}
		
		// 1 ～ 1000(含)
		Integer randomValue = new Random().nextInt(elements.length) + 1; 
		int index = Arrays.binarySearch(elements, randomValue);
		if (index >= 0) {
			System.out.println();
			System.out.println(elements[index]);
		}
	}
}

class TraceHandler implements InvocationHandler {
	// 传入了Integer类型的对象
	private Object target; 
	
	public TraceHandler(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.print(target);
		System.out.print("." + method.getName() + "(");
		
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				System.out.print(args[i]);
				if (i < args.length - 1) {
					System.out.print(", ");
				}
			}
		}
		System.out.println(")");
		
		return method.invoke(target, args);
	}
}
