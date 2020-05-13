package cn.nuaa.qjj.main;

/**
 * ʱ��Ƭ�����߳�
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
			System.out.println("ʱ��Ƭ:" + MainSchdule.timeStamp);
			try {
				Thread.sleep(second * 1000); // 2��������һ��ʱ��Ƭ
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
