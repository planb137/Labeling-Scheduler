package cn.nuaa.qjj.main;

import java.math.BigDecimal;

import cn.nuaa.qjj.domain.Job;
import cn.nuaa.qjj.init.DataInit;

public class DataStatistics {

	public double calc(int a, int b) {
		double f1 = new BigDecimal((float) a / b).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	// ��������Ľ�����
	public double getAcceptTate() {
		int a = MainSchdule.acceptJobs;
		int b = new DataInit().dataInput().size();
		System.out.println("���յ�job���� �� " + a + "  �����job���� �� " + b);
		double f1 = new BigDecimal((float) a / b).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	// ����nvmд��Ĵ���
	public int getNVMWriteTimes() {
		return MainSchdule.NVMWriteTimes;
	}

	// ����nvm������
	public double NVMHitRate() {
		int a = MainSchdule.dataInNVMTimes;
		int b = MainSchdule.finishTasks.size();
		System.out.println("��Ҫ������NVM�еĴ����� " + a + "  ����Ҫ���ݵĴ�����" + b);
		double f1 = new BigDecimal((float) a / b).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	// ����utility
	public int getUtility() {
		int sumU = 0;
		for (Job job : MainSchdule.finishJobs) {
			if (job.isInteractive()) {
				if (job.getEndtime() <= job.getDeadline()) {
					sumU += (MainSchdule.w0 * job.getTasks().get(0).getExeTime());
				} else {
					sumU += (MainSchdule.a * (job.getEndtime() - job.getDeadline())
							* job.getTasks().get(0).getExeTime());
				}
			} else {
				int sum = new TaskAbout().getNumTask(job);
				sumU += (MainSchdule.w1 - ((job.getEndtime() / job.getDeadline()) * (MainSchdule.w1 - MainSchdule.w2))
						* job.getExeTime() / sum);
			}
		}

		return sumU;
	}

}
