package demos.pattern.singleton;

/**
 * 饿汉单例模式
 * @author Dongfuming
 * @date 2016-03-21 上午11:26:21
 */
public class SingletonHungry {
	private static SingletonHungry instance = new SingletonHungry();
	
	private SingletonHungry() { }
	
	public static SingletonHungry getInstace() {
		return instance;
	}
}

/**
 * 懒汉单例模式
 * @author Dongfuming
 * @date 2016-03-21 上午11:26:38
 */
class SingletonLazy {
	private static SingletonLazy instance;			
	
	private SingletonLazy() { }
	
	public static SingletonLazy getInstance() {
		if (instance == null) {
			synchronized ("lock") {
				if (instance == null) {
					instance = new SingletonLazy();
				} 
			}
		}
		return instance;
	}
}
