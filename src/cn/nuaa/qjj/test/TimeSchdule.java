package cn.nuaa.qjj.test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//ָ��ʱ���ִ�У�ģ���ļ�����ܵ���ռ�ã�����Ҫ��ʱ�䡣
public class TimeSchdule {

	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("IMP ��ǰʱ��" + this.scheduledExecutionTime());
			}
		}, new Date(System.currentTimeMillis() + 3 * 1000));
	}
}
