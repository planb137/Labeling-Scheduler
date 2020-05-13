package cn.nuaa.qjj.domain;

public class CPUSlot {
	private int slotID; // 表示CPU中某一slot的唯一标识（共有m个slots）
	private int time; // 表示某一时间点的时间属性。
	private int taskID; // 表示某一时间点此CPU中此slot中正在执行的任务ID。

	public CPUSlot() {
		super();
	}

	public int getSlotID() {
		return slotID;
	}

	public void setSlotID(int slotID) {
		this.slotID = slotID;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
}
