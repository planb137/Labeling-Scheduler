package cn.nuaa.qjj.test;

import java.math.BigDecimal;

public class CalcTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 7;
		int b = 8;
		double f1 = new BigDecimal((float) a / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(f1);
	}

}
