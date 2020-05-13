package cn.nuaa.qjj.main;

import java.util.Iterator;
import java.util.List;

import cn.nuaa.qjj.domain.Data;
import cn.nuaa.qjj.domain.Disk;
import cn.nuaa.qjj.domain.Job;
import cn.nuaa.qjj.domain.NVM;
import cn.nuaa.qjj.domain.Task;
import cn.nuaa.qjj.util.SortDataInDisk;

public class DataAbout {

	public DataAbout() {
	}

	// �ܷ��������Ǩ��
	public void canDoMigration() {
		if (0 != Disk.disk.size()) {
			doMigration();
		}

	}

	// ��Disk�е��������ʹ�С��������Ǩ��
	public void doMigration() {
		// ��Disk�е������Ȱ��յȼ�����С�����ٰ���hotness�Ӵ�С��˳������
		new SortDataInDisk().sortData(Disk.disk);
		/*
		 * for (Data dt : Disk.disk) { System.out.println(dt.toString()); }
		 */
		int minType = getMinType(Disk.disk);// ���disk����С����������,ֻ����ǰ3��
		if (minType == 1) {
			migrationStrategy(1);
		}
		if (minType == 2) {
			migrationStrategy(2);
		}
		if (minType == 3) {
			migrationStrategy(3);
		}
	}

	// ����Disk�е���ҪǨ�����ݵ����ͣ�������Ǩ�Ʋ��ԣ�ִ��Ǩ��
	public void migrationStrategy(int type) {
		// ���NVM��������������
		int maxType = getMaxType(NVM.nvm);
		if (type == 1) {
			// �����ҪǨ�Ƶ������ǵ�1�࣬�滻������2-5����
			if (maxType > 1) {
				migration();
			}
		}
		if (type == 2) {
			// �����ҪǨ�Ƶ������ǵ�2�࣬�滻������3-5���У�
			if (maxType > 2) {
				migration();
			}
		}
		if (type == 3) {
			// �����ҪǨ�Ƶ������ǵ�3�࣬�滻������3-5���У�ͬ��3ʱ�򣬱Ƚ�hoteness�Ĵ�С
			if (maxType > 3) {
				migration();
			} else if (maxType == 3) {
				int hotness = Disk.disk.get(0).getHotness();
				int hotness2 = NVM.nvm.get(0).getHotness();
				if (hotness > hotness2) {
					migration();
				}
			}
		}

	}

	// ����������Ǩ��
	public void migration() {
		while (!MainSchdule.existMigration) {
			// ��¼��ʼǨ�Ƶ�ʱ���
			MainSchdule.timeStamp = MainSchdule.timeSlot;
			MainSchdule.existMigration = true;
		}
		MainSchdule.filePipe = true;
		if (MainSchdule.timeSlot >= MainSchdule.timeStamp + MainSchdule.readDataTime) {
			Data data = NVM.nvm.get(0);
			Data dt = Disk.disk.get(0);
			Disk.disk.remove(0);
			data.setStatus(false);
			NVM.nvm.remove(0);
			Disk.disk.add(data);
			dt.setStatus(true);// ״̬��Ϊ��NVM֮��
			NVM.nvm.add(dt);
			MainSchdule.NVMWriteTimes++;
			MainSchdule.existMigration = false;
			MainSchdule.filePipe = false;// �ͷ��ļ�����ܵ�
			System.out.println("Ǩ�����==========================================================================");
			System.out.println("disk size:" + Disk.disk.size());
			System.out.println("NVM  size:" + NVM.nvm.size());
		}
	}

	// ��Disk���Ƿ����ָ��id������
	public boolean exitData(int id) {
		boolean flag = false;
		for (Data dt : Disk.disk) {
			if (id == dt.getDataID()) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	// ���ָ��������С����������
	public int getMinType(List<Data> list) {
		int min = list.get(0).getType();
		for (Data dt : list) {
			if (dt.getType() < min) {
				min = dt.getType();
			}
		}
		return min;
	}

	// ���ָ������������������
	public int getMaxType(List<Data> list) {
		int max = list.get(0).getType();
		for (Data dt : list) {
			if (dt.getType() > max) {
				max = dt.getType();
			}
		}
		return max;
	}

	// �����������ȴ����е�ʱ�򣬸ı�����������
	public void changeTypeWhenEnterWaiting(Job job) {
		if (job.getTasks().size() == 1) {
			// ����ǽ���ʽ�����޸�����Ϊ 1
			int id = job.getTasks().get(0).getRequiredData();
			changeTypeByDataId(id, 1);
		} else {
			for (Task task : job.getTasks()) {
				int id = task.getRequiredData();
				changeTypeByDataId(id, 2);// ��ͨ�����޸�����Ϊ 2
			}
		}
	}

	// ��һ������Ǩ��
	public void firstMigration() {

		int sum = 0; // ��¼Ǩ���ļ��ĸ�����������n
		// �����ʼ��NVM����
		/*
		 * for (int i = 0; i < MainSchdule.n; i++) { int num = (int)
		 * (Math.random() * Disk.disk.size()); Data dt = Disk.disk.get(num);
		 * dt.setType(5); dt.setStatus(true); Disk.disk.remove(num);
		 * NVM.nvm.add(dt);
		 * 
		 * }
		 */
		// System.out.println("disk size:"+Disk.disk.size());
		// System.out.println("NVM size:"+NVM.nvm.size());

		Iterator<Data> iterator = Disk.disk.iterator();
		while (iterator.hasNext()) {
			Data data = iterator.next();
			if (sum == MainSchdule.n) {
				break;
			}
			data.setType(5);
			data.setStatus(true);
			NVM.nvm.add(data);
			iterator.remove();
			sum++;
			MainSchdule.NVMWriteTimes++;
		}

	}

	// ���鿴disk��NVM֮�е�����
	public void showData() {
		System.out.println("Disk�е������У�");
		for (Data a : Disk.disk) {
			System.out.println(a.toString());
		}
		System.out.println("===============================================");
		System.out.println("NVM�е������У�");
		for (Data a : NVM.nvm) {
			System.out.println(a.toString());
		}
	}

	// ����dataID��ѯ��Ҫ�������Ƿ���NVM֮��
	public boolean dataInNVM(int id) {
		boolean flag = false;
		for (Data data : NVM.nvm) {
			if (data.getDataID() == id) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	// ���뼯�ϣ�����id���޸�ָ������������
	public void changeTypeByDataId(int id, int type) {
		boolean flag = false;
		for (Data dt : NVM.nvm) {
			if (dt.getDataID() == id) {
				dt.setType(type);
				flag = true;
				break;
			}
		}
		// �����NVM�Ļ���ֱ������
		if (!flag) {
			for (Data dt : Disk.disk) {
				if (dt.getDataID() == id) {
					dt.setType(type);
					break;
				}
			}
		}

	}

	// ����id�鿴�Ƿ��Ǵ����ݽ�����������
	public boolean isDataInteractive(int id) {
		boolean flag = false;
		for (Data data : NVM.nvm) {
			if (data.getDataID() == id && data.isInteractive()) {
				flag = true;
				break;
			}
		}
		for (Data data : Disk.disk) {
			if (data.getDataID() == id && data.isInteractive()) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/*
	 * // ����id���������������� public void changeTypeById(int id, int type) { for (Data
	 * data : NVM.nvm) { if (data.getDataID() == id) { data.setType(type);
	 * break; } } for (Data data : Disk.disk) { if (data.getDataID() == id) {
	 * data.setType(type); break; } } }
	 */

	// ��ʱ�䣬����hotness��ֵ
	public void changeHotness() {
		for (Data data : Disk.disk) {
			data.setHotness(data.getHotness() - 1);
		}
		for (Data data : NVM.nvm) {
			data.setHotness(data.getHotness() - 1);
		}

	}

	// �õ������ݸ���hotness��ֵ
	public void changeHotnessBecauseUse(Task task) {
		int id = task.getRequiredData();
		boolean rs = false;
		if (task.isType()) {
			// ����ǽ���ʽ����
			for (Data data : NVM.nvm) {
				if (data.getDataID() == id) {
					data.setHotness(data.getHotness() + MainSchdule.H1 + MainSchdule.H2);
					rs = true;
					break;
				}
			}
			if (!rs) {
				for (Data data : Disk.disk) {
					if (data.getDataID() == id) {
						data.setHotness(data.getHotness() + MainSchdule.H1 + MainSchdule.H2);
						rs = true;
						break;
					}
				}
			}
		} else {
			for (Data data : NVM.nvm) {
				if (data.getDataID() == id) {
					data.setHotness(data.getHotness() + MainSchdule.H1);
					rs = true;
					break;
				}
			}
			if (!rs) {
				for (Data data : Disk.disk) {
					if (data.getDataID() == id) {
						data.setHotness(data.getHotness() + MainSchdule.H1);
						rs = true;
						break;
					}
				}
			}
		}

	}
}
