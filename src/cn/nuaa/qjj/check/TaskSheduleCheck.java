package cn.nuaa.qjj.check;

import java.util.List;

import cn.nuaa.qjj.domain.Job;
import cn.nuaa.qjj.domain.Task;
import cn.nuaa.qjj.main.DataAbout;
import cn.nuaa.qjj.main.MainSchdule;
import cn.nuaa.qjj.main.TaskAbout;
import cn.nuaa.qjj.util.SortJob;

//检查是cpu是否有空闲，进行任务调度
public class TaskSheduleCheck {

	public void taskSheduleCheck() {
		// 如果有空闲，则进行调度
		if (MainSchdule.runningTasks.size() < MainSchdule.m) {
			int residue = MainSchdule.m - MainSchdule.runningTasks.size();
			for (int i = 0; i < residue; i++) {
				if (MainSchdule.waitingJobs != null) {
					// 根据job的最晚调度时间进行排序，调整顺序
					new SortJob().SortJobByLatSheTime(MainSchdule.waitingJobs);
					// Iterator<Job> iterator =
					// MainSchdule.waitingJobs.iterator();
					w :for (Job job : MainSchdule.waitingJobs) {
						if (job.isInteractive()) {
							int daId = job.getTasks().get(0).getRequiredData();
							if (new DataAbout().dataInNVM(daId)) {
								MainSchdule.dataInNVMTimes++;// 用于计算nvm命中率
							}
							int sumExe = new TaskAbout().getSumExeTime(job.getTasks().get(0)); // 算一下每个需要的执行时间
							job.setStarttime(MainSchdule.timeSlot);
							job.getTasks().get(0).setStartTime(MainSchdule.timeSlot);
							job.getTasks().get(0).setStatus(1);
							job.setEndtime(sumExe + MainSchdule.timeSlot);
							// MainSchdule.runningJobs.add(job);
							MainSchdule.runningTasks.add(job.getTasks().get(0));
							System.out.println("有交互式任务开始运行++++++++++++++++：" + job.getTasks().get(0).toString());
							// 别忘记修改数据的类型，很重要
							int id = job.getTasks().get(0).getRequiredData();
							new DataAbout().changeTypeByDataId(id, 1);// 更改数据类型为1
							MainSchdule.finishJobs.add(job);
							MainSchdule.waitingJobs.remove(job);
							break w;// 结束循环，判断是否cpu有空余
						} else {
							// 优先调用数据在NVM之中的
							List<Task> listTask = new TaskAbout().sortTaskByDataInNVM(job.getTasks());
							if (null != listTask) {
								// 在还有空余的情况下，把批量任务尽可能都直接执行
								for (Task task : listTask) {
									if (new DataAbout().dataInNVM(task.getRequiredData())) {
										MainSchdule.dataInNVMTimes++;// 用于计算nvm命中率
									}
									int sumExe = new TaskAbout().getSumExeTime(task); // 算一下每个需要的执行时间
									if (job.getStarttime() == 0) {
										job.setStarttime(MainSchdule.timeSlot);// 判断有没有设置其开始时间
									}
									task.setStartTime(MainSchdule.timeSlot);
									task.setStatus(1);
									MainSchdule.runningTasks.add(task);
									System.out.println("有批量任务开始运行==============" + task.toString());
									// 别忘记修改数据的类型，很重要
									int id = task.getRequiredData();
									new DataAbout().changeTypeByDataId(id, 1);// 更改数据类型为1
									listTask.remove(task);
									job.setTasks(listTask); // 很重要
									if (job.getTasks().size() == 0) {
										//job.getTasks().add(task);// 记录每个的执行时间
										System.out.println("最后一个task完成，有一个job完成" + job.toString() + sumExe);
										job.setEndtime(MainSchdule.timeSlot + sumExe);// 记录最后一个完成时间，当做最终完成时间
										MainSchdule.finishJobs.add(job);
										MainSchdule.waitingJobs.remove(job);
									}
									break w;
								}
							}
						}
					}
					
					
				}

			}

		}
	}

}
