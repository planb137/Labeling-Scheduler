package cn.nuaa.qjj.domain;

public class NVMSlot {
	private int slotID;// ��ʾNVM��ĳһslot��Ψһ��ʶ������n��slots�����ռ��С���ޣ���
	private int time; // ��ʾĳһʱ����ʱ�����ԡ�
	private int dataID;// ��ʾĳһʱ���NVM�д�slot�д洢������ID��

	public NVMSlot() {
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

	public int getDataID() {
		return dataID;
	}

	public void setDataID(int dataID) {
		this.dataID = dataID;
	}

}
