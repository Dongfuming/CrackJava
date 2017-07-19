
package book.corejava1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 一些零碎知识点 和 一个计算收益的程序
 * @author Dongfuming
 * 2016-4-27 下午3:42:52 
 */
public class Demo01 {

	public static void main(String[] args) throws Exception {
		//Path path = Paths.get("/Users/Dongfuming/Desktop/data.txt");
		//System.out.println(path);
		
		// 当前解释器路径
		String dir = System.getProperty("user.dir");
		System.out.println(dir);
		
		System.out.println(2.0 - 1.1);
		
		Size size = Size.M;
		System.out.println("size = " + size);
		
		String str = "java\u2122";
		System.out.println(str);
		char c = str.charAt(1);
		System.out.println(c);
		
		StringBuilder builder = new StringBuilder();
		builder.append("hello ");
		builder.append("world");
		String s = builder.toString();
		System.out.println(s);
		
		builder.insert(1, "xxx");
		s = builder.toString();
		System.out.println(s);
		
		int i = 0;
		outer:
			while (true) {
				//inner:
				for (int j = 0; j < 3; j++) {
					System.out.println("hello world");
					if (i > 2) { break outer; }
					System.out.println("i = " + i);
				}
				i++;
			}
		System.out.println("over, i = " + i);
		
		List<String> list = new ArrayList<String>();
		list.add("111");
		list.add("222");
		list.add("333");
		String[] strArray = new String[list.size()];
		list.toArray(strArray);
		Object[] array = list.toArray();
		for (Object object : array) {
			System.out.println(object);
		}
		System.out.println();
		for (String aStr: strArray) {
			System.out.println(aStr);
		}
	}
	
	@Test
	public void calculateBalanceThroughMonthRate() {
		double yearRate = 10; // 9.5长期平均 - 0.8管理费 = 8.7
		double averageMonthRate = (Math.pow(1+yearRate/100, 1.0/12) - 1) * 100;
		int totalMonth = 12 * 20; // 33年(2017—2049)
		int count = 0;
		double inputPerMonth = 4000; 
		double initialInput = 0;
		double balance = initialInput;
		double totalInput = inputPerMonth * totalMonth;
		
		while (count < totalMonth) {
			balance += inputPerMonth;
			double interest = balance * (averageMonthRate / 100);
			balance += interest;
			count++;
		}
		System.out.println("month = " + totalMonth + 
						   ", inputPerMonth = " + inputPerMonth + 
						   ", totalInput = "+ totalInput + 
						   ", totalBalance = " + balance +   
						   ", net = " + (balance-totalInput-initialInput)  +
						   ", totoalRate = " + ((balance-totalInput-initialInput)/totalInput * 100) + "%");
		
		System.out.println("if yearRate is " + yearRate + 
						   ", monthRate = " + averageMonthRate);
	} 

	@Test
	public void calculateBalanceThroughYearRate() {
		double balance = 0;
		double payment = 2750;//1500 * 12; 
		int years = 20; // 33年(2017—2049)
		double interestRate = 15.0; // 9.5长期平均 - 0.8管理费 = 8.7
		double totalInput = years * payment;
		int count = 0;
		
		while (count < years) {
			balance += payment;
			double interest = balance * interestRate / 100;
			balance += interest;
			count++;
		}
		System.out.println("totalInput = "+ totalInput + 
				   ", totalBalance = " + balance +  
				   ", net = " + (balance-totalInput)  +
				   ", totoalRate = " + (balance-totalInput)/totalInput);
	}
	
	public static void calculateYearRate(double totalRate, int totalYear, double guess) {
		double balance = 0;
		double inputPerMonth = 1000;
		double inputPerYear = inputPerMonth * 12;
		double inputTotal = inputPerYear * totalYear;
		int totalYearCopy = totalYear;
		
		while (totalYear > 0) {
			balance += inputPerYear;
			double interest = balance * guess / 100;
			balance += interest;
			totalYear--;
		}
		double resultRate = (balance - inputTotal) / inputTotal;
		
		if (Math.abs(totalRate - resultRate) < 0.01 ) {
			System.out.println("average year rate = " + guess);
		} else if (totalRate - resultRate > 0) {
			guess += 0.2;
			calculateYearRate(totalRate, totalYearCopy, guess); 
		} else {
			guess -= 0.2;
			calculateYearRate(totalRate, totalYearCopy, guess); 
		}
	}
	
	public static void retirementPlan() {
		/* 
		 时间：59岁 - 27岁(含) + 1 = 33年
		 年均通胀：4%
		 30年年均收益：9.5%
		 60岁理财收益：5%
		 社保现在：1500元 * 2人 ＝ 3000。通胀33年后：3000元 * pow(1.04, 33) * 12月 = 13万
		 现在生活水平2口之家：4000元 * 12月 = 4.8万；旅游1次：1.5w * 2 = 3w；总：7.8万。通胀33年后：28.5万
		 缺口：28.5 - 13 = 15.5万，理财本金：310万； 
		 33年后达到此水平，现在每月应该投：1400元
		*/
	}
}

 	

enum Size { S, M, L, XL };



