package cn.nuaa.qjj.test;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "5.6.7.16.17";
		String[] s1 = s.split("\\.");
		for (int i = 0; i < s1.length; i++) {
			System.out.print(s1[i]);
		}
	}

}
