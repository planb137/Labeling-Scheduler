package cn.nuaa.qjj.check;

import java.util.Iterator;

import cn.nuaa.qjj.domain.Task;
import cn.nuaa.qjj.main.DataAbout;
import cn.nuaa.qjj.main.MainSchdule;
import cn.nuaa.qjj.main.TaskAbout;

public class TaskFinishCheck {
	public void taskFinishCheck() {
		if (null != MainSchdule.runningTasks) {
			Iterator<Task> iterator = MainSchdule.runningTasks.iterator();
			while (iterator.hasNext()) {
				// �ж���Ҫ�������Ƿ���NVM֮�У��������ܵ�ִ��ʱ��
				Task ts = iterator.next();
				int sumExe = new TaskAbout().getSumExeTime(ts);
				// ���ִ��ʱ�䵽��
				if (MainSchdule.timeSlot >= (ts.getStartTime() + sumExe)) {
					ts.setEndTime(MainSchdule.timeSlot);
					ts.setStatus(-1);
					MainSchdule.finishTasks.add(ts);// ������ɵĶ���
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
					System.out.println("job����ɣ����ɹ��Ƴ� " + ts.toString());
				}
			}
		}
	}

}
