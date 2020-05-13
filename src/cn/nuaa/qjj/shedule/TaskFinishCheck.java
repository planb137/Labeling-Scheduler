package cn.nuaa.qjj.shedule;

import java.util.Iterator;

import cn.nuaa.qjj.domain.Task;
import cn.nuaa.qjj.main.DataAbout;
import cn.nuaa.qjj.main.MainSchdule;
import cn.nuaa.qjj.main.TaskAbout;

public class TaskFinishCheck extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (null != MainSchdule.runningTasks) {
				Iterator<Task> iterator = MainSchdule.runningTasks.iterator();
				while (iterator.hasNext()) {
					// �ж���Ҫ�������Ƿ���NVM֮�У��������ܵ�ִ��ʱ��
					Task ts = iterator.next();
					int sumExe = new TaskAbout().getSumExeTime(ts);
					// ���ִ��ʱ�䵽��
					if (MainSchdule.timeStamp >= (ts.getStartTime() + sumExe)) {
						ts.setEndTime(MainSchdule.timeStamp);
						int dataId = ts.getRequiredData();
						boolean interactive = ts.isType();
						if (interactive) {
							// ��������ǽ���ʽ�ģ�������������Ϊ3,�����ǣ���
							new DataAbout().changeTypeByDataId(dataId, 3);
						} else {
							// ���������ͨ�ģ�������������Ϊ5,�����ǣ���
							new DataAbout().changeTypeByDataId(dataId, 5);
						}
						iterator.remove();// ���������е������а���������Ƴ�
						System.out.println("jobID = " + ts.getTaskID() + " ��job����ɣ����Ƴ���");
					}
				}
			}

		}
	}
}
