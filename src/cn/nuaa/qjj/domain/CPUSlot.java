package cn.nuaa.qjj.domain;

public class CPUSlot {
	private int slotID; // ��ʾCPU��ĳһslot��Ψһ��ʶ������m��slots��
	private int time; // ��ʾĳһʱ����ʱ�����ԡ�
	private int taskID; // ��ʾĳһʱ����CPU�д�slot������ִ�е�����ID��

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
