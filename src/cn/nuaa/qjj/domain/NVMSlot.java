package cn.nuaa.qjj.domain;

public class NVMSlot {
	private int slotID;// 表示NVM中某一slot的唯一标识（共有n个slots，但空间大小有限）。
	private int time; // 表示某一时间点的时间属性。
	private int dataID;// 表示某一时间点NVM中此slot中存储的数据ID。

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
