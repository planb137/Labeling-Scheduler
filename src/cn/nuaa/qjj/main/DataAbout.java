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

	// 能否进行数据迁移
	public void canDoMigration() {
		if (0 != Disk.disk.size()) {
			doMigration();
		}

	}

	// 按Disk中的数据类型从小到达依次迁移
	public void doMigration() {
		// 把Disk中的数据先按照等级，从小到大，再按着hotness从大到小的顺序排序。
		new SortDataInDisk().sortData(Disk.disk);
		/*
		 * for (Data dt : Disk.disk) { System.out.println(dt.toString()); }
		 */
		int minType = getMinType(Disk.disk);// 获得disk中最小的数据类型,只考虑前3类
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

	// 传入Disk中的需要迁移数据的类型，并根据迁移策略，执行迁移
	public void migrationStrategy(int type) {
		// 获得NVM中最大的数据类型
		int maxType = getMaxType(NVM.nvm);
		if (type == 1) {
			// 如果需要迁移的数据是第1类，替换的数据2-5都行
			if (maxType > 1) {
				migration();
			}
		}
		if (type == 2) {
			// 如果需要迁移的数据是第2类，替换的数据3-5都行，
			if (maxType > 2) {
				migration();
			}
		}
		if (type == 3) {
			// 如果需要迁移的数据是第3类，替换的数据3-5都行，同是3时候，比较hoteness的大小
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

	// 真正做数据迁移
	public void migration() {
		while (!MainSchdule.existMigration) {
			// 记录开始迁移的时间戳
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
			dt.setStatus(true);// 状态改为在NVM之中
			NVM.nvm.add(dt);
			MainSchdule.NVMWriteTimes++;
			MainSchdule.existMigration = false;
			MainSchdule.filePipe = false;// 释放文件传输管道
			System.out.println("迁移完成==========================================================================");
			System.out.println("disk size:" + Disk.disk.size());
			System.out.println("NVM  size:" + NVM.nvm.size());
		}
	}

	// 在Disk中是否存在指定id的数据
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

	// 获得指定集合最小的数据类型
	public int getMinType(List<Data> list) {
		int min = list.get(0).getType();
		for (Data dt : list) {
			if (dt.getType() < min) {
				min = dt.getType();
			}
		}
		return min;
	}

	// 获得指定集合最大的数据类型
	public int getMaxType(List<Data> list) {
		int max = list.get(0).getType();
		for (Data dt : list) {
			if (dt.getType() > max) {
				max = dt.getType();
			}
		}
		return max;
	}

	// 当有任务进入等待队列的时候，改变其数据类型
	public void changeTypeWhenEnterWaiting(Job job) {
		if (job.getTasks().size() == 1) {
			// 如果是交互式任务，修改类型为 1
			int id = job.getTasks().get(0).getRequiredData();
			changeTypeByDataId(id, 1);
		} else {
			for (Task task : job.getTasks()) {
				int id = task.getRequiredData();
				changeTypeByDataId(id, 2);// 普通任务，修改类型为 2
			}
		}
	}

	// 第一次数据迁移
	public void firstMigration() {

		int sum = 0; // 记录迁移文件的个数，不大于n
		// 随机初始化NVM过程
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

	// 供查看disk和NVM之中的数据
	public void showData() {
		System.out.println("Disk中的数据有：");
		for (Data a : Disk.disk) {
			System.out.println(a.toString());
		}
		System.out.println("===============================================");
		System.out.println("NVM中的数据有：");
		for (Data a : NVM.nvm) {
			System.out.println(a.toString());
		}
	}

	// 根据dataID查询需要的数据是否在NVM之中
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

	// 传入集合，数据id，修改指定的数据类型
	public void changeTypeByDataId(int id, int type) {
		boolean flag = false;
		for (Data dt : NVM.nvm) {
			if (dt.getDataID() == id) {
				dt.setType(type);
				flag = true;
				break;
			}
		}
		// 如果在NVM的话，直接跳过
		if (!flag) {
			for (Data dt : Disk.disk) {
				if (dt.getDataID() == id) {
					dt.setType(type);
					break;
				}
			}
		}

	}

	// 根据id查看是否是此数据交互类型数据
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
	 * // 根据id更改他的数据类型 public void changeTypeById(int id, int type) { for (Data
	 * data : NVM.nvm) { if (data.getDataID() == id) { data.setType(type);
	 * break; } } for (Data data : Disk.disk) { if (data.getDataID() == id) {
	 * data.setType(type); break; } } }
	 */

	// 随时间，更改hotness的值
	public void changeHotness() {
		for (Data data : Disk.disk) {
			data.setHotness(data.getHotness() - 1);
		}
		for (Data data : NVM.nvm) {
			data.setHotness(data.getHotness() - 1);
		}

	}

	// 用到的数据更改hotness的值
	public void changeHotnessBecauseUse(Task task) {
		int id = task.getRequiredData();
		boolean rs = false;
		if (task.isType()) {
			// 如果是交互式任务
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
