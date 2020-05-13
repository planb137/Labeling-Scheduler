package cn.nuaa.qjj.test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//指定时候后执行，模拟文件传输管道被占用，所需要的时间。
public class TimeSchdule {

	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("IMP 当前时间" + this.scheduledExecutionTime());
			}
		}, new Date(System.currentTimeMillis() + 3 * 1000));
	}
}
