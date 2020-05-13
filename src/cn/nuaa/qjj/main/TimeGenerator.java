package cn.nuaa.qjj.main;

/**
 * 时间片生成线程
 * 
 * @author planb
 *
 */
public class TimeGenerator extends Thread {
	private int time;
	private int second;

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public TimeGenerator(int time, int second) {
		this.time = time;
		this.second = second;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			this.time++;
			MainSchdule.timeStamp = this.time;
			System.out.println("时间片:" + MainSchdule.timeStamp);
			try {
				Thread.sleep(second * 1000); // 2秒钟生成一个时间片
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
