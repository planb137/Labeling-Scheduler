package cn.nuaa.qjj.shedule;

import java.util.Iterator;
import java.util.List;

import cn.nuaa.qjj.domain.Job;
import cn.nuaa.qjj.domain.Task;
import cn.nuaa.qjj.main.DataAbout;
import cn.nuaa.qjj.main.MainSchdule;
import cn.nuaa.qjj.main.TaskAbout;
import cn.nuaa.qjj.util.SortJob;

//�����cpu�Ƿ��п��У������������
public class TaskSheduleCheck extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			// ����п��У�����е���
			if (MainSchdule.runningTasks.size() < MainSchdule.m)

			{
				if (MainSchdule.waitingJobs != null) {
					// ����job���������ʱ��������򣬵���˳��
					new SortJob().SortJobByLatSheTime(MainSchdule.waitingJobs);
					Iterator<Job> iterator = MainSchdule.waitingJobs.iterator();
					while (iterator.hasNext()) {
						// �����������ʱ��Ĵ�С�����ȵ����������ʱ����С��
						Job job = iterator.next();
						// ����ǽ���ʽ����ֱ�Ӽ���
						if (job.isInteractive()) {
							int daId = job.getTasks().get(0).getRequiredData();
							if (new DataAbout().dataInNVM(daId)) {
								MainSchdule.dataInNVMTimes++;// ���ڼ���nvm������
							}
							int sumExe = new TaskAbout().getSumExeTime(job.getTasks().get(0)); // ��һ��ÿ����Ҫ��ִ��ʱ��
							job.setStarttime(MainSchdule.timeSlot);
							job.getTasks().get(0).setStartTime(MainSchdule.timeSlot);
							job.getTasks().get(0).setStatus(1);
							job.setEndtime(sumExe + MainSchdule.timeSlot);
							// MainSchdule.runningJobs.add(job);
							MainSchdule.runningTasks.add(job.getTasks().get(0));
							System.out.println("�н���ʽ����ʼ����++++++++++++++++��" + job.getTasks().get(0).toString());
							// �������޸����ݵ����ͣ�����Ҫ
							int id = job.getTasks().get(0).getRequiredData();
							new DataAbout().changeTypeByDataId(id, 1);// ������������Ϊ1
							MainSchdule.finishJobs.add(job);
							iterator.remove();
							break;// ����ѭ�����ж��Ƿ�cpu�п���
						} else {
							// ���ȵ���������NVM֮�е�
							List<Task> listTask = new TaskAbout().sortTaskByDataInNVM(job.getTasks());
							if (null != listTask) {
								for (Task task : listTask) {
									if (new DataAbout().dataInNVM(task.getRequiredData())) {
										MainSchdule.dataInNVMTimes++;// ���ڼ���nvm������
									}
									int sumExe = new TaskAbout().getSumExeTime(task); // ��һ��ÿ����Ҫ��ִ��ʱ��
									if (job.getStarttime() == 0) {
										job.setStarttime(MainSchdule.timeSlot);// �ж���û�������俪ʼʱ��
									}
									task.setStartTime(MainSchdule.timeSlot);
									task.setStatus(1);
									MainSchdule.runningTasks.add(task);
									System.out.println("����������ʼ����==============" + task.toString());
									// �������޸����ݵ����ͣ�����Ҫ
									int id = task.getRequiredData();
									new DataAbout().changeTypeByDataId(id, 1);// ������������Ϊ1
									listTask.remove(task);
									job.setTasks(listTask); // ����Ҫ
									if (job.getTasks().size() == 0) {
										System.out.println("���һ��task��ɣ���һ��job���" + job.toString() + sumExe);
										job.setEndtime(MainSchdule.timeSlot + sumExe);// ��¼���һ�����ʱ�䣬�����������ʱ��
										MainSchdule.finishJobs.add(job);
									}

									break;
								}
							}

						}
					}
				}

			}
		}

	}

}
