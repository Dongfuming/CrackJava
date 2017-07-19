package demos.pattern.observer;

/**
 * 观察者对象
 * @author Dongfuming
 * @date 2016-03-21 上午11:33:06
 */
public class Person implements WeatherReceiver {
	private String name;
	
	public Person(String name) {
		this.name = name;
	}
	
	@Override
	public void didReceiveWeather(String weather) {
		if ("大风".equals(weather)) {
			System.out.println(name + " 收到大风天气");
		} else if ("下雨".equals(weather)) {
			System.out.println(name + " 收到下雨天气");
		} else if ("下雪".equals(weather)) {
			System.out.println(name + " 收到下雪天气");
		} else if ("阴天".equals(weather)) {
			System.out.println(name + " 收到阴天天气");
		}
	}
}
