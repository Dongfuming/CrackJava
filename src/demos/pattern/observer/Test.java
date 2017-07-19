package demos.pattern.observer;

/**
 * 测试观察者模式
 * @author Dongfuming
 * @date 2016-03-21 上午11:28:20
 */
public class Test {

	public static void main(String[] args) throws InterruptedException, SecurityException {
		
		WeatherStation station = new WeatherStation();
		station.broadcastWeather();
		
		Person p1 = new Person("AAA");
		Person p2 = new Person("BBB");
		Person p3 = new Person("CCC");
		
		station.addListener(p1);
		station.addListener(p2);
		station.addListener(p3);
	}
}
