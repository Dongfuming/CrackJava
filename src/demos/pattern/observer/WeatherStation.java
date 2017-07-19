package demos.pattern.observer;

import java.util.ArrayList;
import java.util.Random;

/**
 * 通知中心
 * @author Dongfuming
 * @date 2016-03-21 上午11:33:40
 */
public class WeatherStation {
	private String[] weatherArray = { "大风", "下雨", "下雪", "阴天" };
	private String weather;
	private ArrayList<WeatherReceiver> listenerArray = new ArrayList<WeatherReceiver>();
	
	public void addListener(WeatherReceiver listener) {
		listenerArray.add(listener);
	}
	
	public void broadcastWeather() {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					updateWeather();
					sleepAwhile();
				}
			}
		}.start();
	}
	
	private void sleepAwhile() {
		try {
			Thread.sleep(new Random().nextInt(501) + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void updateWeather() {
		Random random = new Random();
		weather = weatherArray[random.nextInt(weatherArray.length)];
		for (WeatherReceiver listener : listenerArray) {
			listener.didReceiveWeather(weather);
		}
	}
	
	public void setWeather(String weather) {
		this.weather = weather;
	}
	
	public String getWeather() {
		return weather;
	}
}
