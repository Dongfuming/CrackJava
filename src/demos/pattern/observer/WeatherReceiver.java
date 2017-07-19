package demos.pattern.observer;

/**
 * 收到天气接口
 * @author Dongfuming
 * @date 2016-03-21 上午11:30:11
 */
public interface WeatherReceiver {
	public abstract void didReceiveWeather(String weather);
}
