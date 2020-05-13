package cn.nuaa.qjj.util;

public class RandomTest {
	public static void main(String[] args) {

		for (int i = 0; i < 50; i++) {
			int num = (int) (Math.random() * 100);
			System.out.println(num);
		}
	}
}
