package book.corejava1;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 打印月度日历 和 一个随机抽奖程序
 * @author Dongfuming
 * @date 2016-6-29 下午9:55:55
 */
public class Demo02 {

	public static void main(String[] args) {
		lotteryDrawing();
		printThisMonthCalender();
	}
	
	/** 打印本月的日历，有点难度 */
	public static void printThisMonthCalender() {
		Locale.setDefault(Locale.US);
		
		GregorianCalendar calendar = new GregorianCalendar();
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		int thisMonth = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, 1); // 本月的第一天
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK); // 本月的第一天为星期几(1-星期日，2-星期一,...)
		int firstDayOfWeek = calendar.getFirstDayOfWeek(); // 当前地区星期的起始日，如美国为周日，欧洲为周一
		
		int indent = 0;
		while (weekDay != firstDayOfWeek) {
			indent++;
			calendar.add(Calendar.DAY_OF_MONTH, -1); // 向前进一天, 一直回到星期的起始日
			weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		}
		
		String[] weekDayNameArray = new DateFormatSymbols().getShortWeekdays();
		do {
			System.out.printf("%4s", weekDayNameArray[weekDay]);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		} while (weekDay != firstDayOfWeek); // 向后进一天，一直回到星期的正常日
		
		System.out.println(); // 打印完星期字母，换行打印数字
		
		for (int i = 0; i < indent; i++) {
			System.out.print("    ");
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		do {
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			System.out.printf("%3d", day);
			if (day == today) {
				System.out.print("*");
			} else {
				System.out.print(" ");
			}
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			weekDay = calendar.get(Calendar.DAY_OF_WEEK);
			if (weekDay == firstDayOfWeek) {
				System.out.println();
			}
		} while (calendar.get(Calendar.MONTH) == thisMonth);
		
		if (weekDay == firstDayOfWeek) {
			System.out.println();
		}
	}
	
	/** 随机抽奖程序 */
	public static void lotteryDrawing() {
		int maxNum = 200; // 能取的最大数
		int drawNum = 6; // 取几个数
		int[] numbersArray = new int[maxNum]; 
		int[] resultsArray = new int[drawNum];
		
		// 初始化能取的数字
		for (int i = 0; i < numbersArray.length; i++) {
			numbersArray[i] = i + 1;
		}
		
		// 随机的取到哪些数
		for (int i = 0; i < resultsArray.length; i++) {
			int randomIndex = (int) (Math.random() * maxNum); // 0 ～ maxNum-1
			System.out.println("random index = " + randomIndex);
			int randomNum = numbersArray[randomIndex]; // 0 ~ maxNum
			resultsArray[i] = randomNum;
			
			numbersArray[randomIndex] = numbersArray[maxNum-1]; // 把最大的数填到取出去的位置
			maxNum--;
		}
		
		//Arrays.sort(resultsArray);
		System.out.println(Arrays.toString(resultsArray));
	}

}
