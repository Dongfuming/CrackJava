package book.corejava1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.Timer;

/**
 * 1. 接口 与 回调
 * 2. 内部类(未写代码)
 * @author Dongfuming
 * @date 2016-7-6 下午4:52:09
 */
public class Demo08 {

	public static void main(String[] args) {
		ActionListener listener = new TimePrinter();
		Timer timer = new Timer(2000, listener);
		timer.start();
	}
}

class TimePrinter implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Date now = new Date();
		System.out.println("At the tone, the time is " + now);
	}
}