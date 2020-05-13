package cn.nuaa.qjj.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.nuaa.qjj.domain.Job;
import cn.nuaa.qjj.domain.Task;

@SuppressWarnings("ALL")
public class TaskAbout {
	// ��ȡָ��ʱ��Ƭ����������Jobs
	public List<Job> arriveJobs(List<Job> jobList) {
		List<Job> tempJobs = new ArrayList<Job>();
		for (Job job : jobList) {
			if (job.getArriveTime() == MainSchdule.timeSlot) {
				System.out.println("�����񵽴" + job.toString());
				tempJobs.add(job);
				for (Task task : job.getTasks()) {
					new DataAbout().changeHotnessBecauseUse(task); // ���ĵ���������Ҫ�����ݵ�hotness������Ҫ����
				}
			}
		}
		return tempJobs;
	}

	// �ж��Ƿ����job
	public boolean acceptJob(List<Job> jobList) {
		if (null == jobList) {
			return false;
		} else {
			for (Job job : jobList) {
				if (receiveJob(job)) {
					for (Task task : job.getTasks()) {
						task.setStatus(0);// ���ȸ���task��״̬Ϊ�ȴ�
					}
					MainSchdule.waitingJobs.add(job); // ������գ�ֱ�Ӽ���ȴ�������
					MainSchdule.acceptJobs++;
					System.out.println("����job��" + job.toString());
					// ������Ҫ���������ͣ�����Ҫ
					new DataAbout().changeTypeWhenEnterWaiting(job);
				} else {
					MainSchdule.rejectJob.add(job);
					System.out.println("�ܾ���job��" + job.toString());
				}
			}
			return true;
		}
	}

	// ͨ������Job�����ؽ��ܻ��Ǿܾ��ķ����������վܾ�����
	public boolean receiveJob(Job job) {
		boolean flag = false;
		int latSchTime = getLatestTime(job);
		if (acceptTask(latSchTime)) {
			flag = true;
		}
		return flag;
	}

	// ���ܻ�ܾ�������Է���
	public boolean acceptTask(int latSchTime) {
		if (latSchTime < MainSchdule.timeSlot) {
			return false;
		} else {
			boolean flag = false;
			int numNVM = 0;
			int numDisk = 0;
			int load = 0;
			for (Task task : MainSchdule.runningTasks) {
				int dataID = task.getRequiredData();
				boolean inNVM = new DataAbout().dataInNVM(dataID);
				if (inNVM) {
					numNVM++;
				} else {
					numDisk++;
				}
			}
			for (Job job : MainSchdule.waitingJobs) {
				int time = getLatestTime(job); // ��õȴ�����������������ʱ��
				// ֻ�е��ȴ������е�������������ʱ��С���ж�������������ʱ��ʱ����������
				if (time < latSchTime) {
					List<Task> tasks = job.getTasks();
					for (Task task : tasks) {
						int dataID = task.getRequiredData();
						boolean inNVM = new DataAbout().dataInNVM(dataID);
						if (inNVM) {
							numNVM++;
							load += task.getExeTime();
						} else {
							load += task.getExeTime() + MainSchdule.readDataTime;
							numDisk++;
						}
					}
				}

			}
			/*
			 * // p��ʾ������NVM֮�е����� double x = numNVM * MainSchdule.p *
			 * (MainSchdule.NVMTime + numDisk * (1 - MainSchdule.p) *
			 * MainSchdule.DiskTime)/MainSchdule.m; if (Math.ceil(x) <
			 * latSchTime) { flag = true; }
			 */
			if (Math.ceil(load / MainSchdule.m) < latSchTime) {
				flag = true;
			}
			return flag;
		}
	}

	// ����Job����������������ʱ��
	public int getLatestTime(Job job) {
		if (job.isInteractive()) {
			// ����ǽ���ʽ����
			Task tempTask = job.getTasks().get(0);
			int dataID = tempTask.getRequiredData();
			boolean inNVM = new DataAbout().dataInNVM(dataID);
			int sumExeTime;
			if (inNVM) {
				sumExeTime = job.getExeTime();
			} else {
				sumExeTime = job.getExeTime() + MainSchdule.readDataTime;
			}
			int latSchTime = job.getDeadline() - sumExeTime; // �������ʱ��=��ֹʱ��-��ִ��ʱ��
			return latSchTime;
		} else {
			// ���������������
			int notInNVM = 0;
			for (Task task : job.getTasks()) {
				int id = task.getRequiredData();
				int time = task.getExeTime();
				boolean inNVM = new DataAbout().dataInNVM(id);
				if (!inNVM) {
					notInNVM++;
				}
			}
			int sumExeTime = notInNVM * MainSchdule.readDataTime + job.getTasks().size() * job.getExeTime();// �����ܵ�ִ��ʱ��
			int latSchTime = job.getDeadline() - sumExeTime;
			return latSchTime;
		}
	}

	// ����Task����������ִ��ʱ��
	public int getSumExeTime(Task ts) {
		int dataID = ts.getRequiredData();
		int sumExe = ts.getExeTime();
		boolean inNVM = new DataAbout().dataInNVM(dataID);
		if (!inNVM) {
			sumExe += MainSchdule.readDataTime;
		}
		return sumExe;
	}

	// ����task��Ҫ�������ڲ���nvm��������
	public List<Task> sortTaskByDataInNVM(List<Task> listTasks) {
		List<Task> list = listTasks;
		List<Task> temp = new ArrayList<Task>();
		Iterator<Task> it = list.iterator();
		while (it.hasNext()) {
			Task task = it.next();
			int dataId = task.getRequiredData();
			if (new DataAbout().dataInNVM(dataId)) {
				temp.add(task);
				it.remove();
			}
		}
		for (Task tk : list) {
			temp.add(tk);
		}
		return temp;
	}

	public int getNumTask(Job job) {
		int sum = 1;
		for (Job jo : MainSchdule.acceptJob) {
			if (job.getJobID() == jo.getJobID()) {
				sum = jo.getTasks().size();
				break;
			}
		}
		return sum;
	}

}
