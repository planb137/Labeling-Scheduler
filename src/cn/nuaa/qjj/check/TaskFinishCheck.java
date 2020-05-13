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
				// 判断需要的数据是否在NVM之中，进而算总的执行时间
				Task ts = iterator.next();
				int sumExe = new TaskAbout().getSumExeTime(ts);
				// 如果执行时间到达
				if (MainSchdule.timeSlot >= (ts.getStartTime() + sumExe)) {
					ts.setEndTime(MainSchdule.timeSlot);
					ts.setStatus(-1);
					MainSchdule.finishTasks.add(ts);// 加入完成的队列
					int dataId = ts.getRequiredData();
					boolean interactive = ts.isType();
					if (interactive) {
						// 如果任务是交互式的，更改数据类型为3,别忘记！！
						new DataAbout().changeTypeByDataId(dataId, 3);
					} else {
						// 如果任务普通的，更改数据类型为5,别忘记！！
						new DataAbout().changeTypeByDataId(dataId, 5);
					}
					iterator.remove();// 从正在运行的任务中把这个任务移除
					System.out.println("job已完成，并成功移除 " + ts.toString());
				}
			}
		}
	}

}
