package cn.nuaa.qjj.domain;

public class Data {
	@Override
	public String toString() {
		return "Data [dataID=" + dataID + ", hotness=" + hotness + ", isInteractive=" + isInteractive + ", status="
				+ status + ", type=" + type + "]";
	}

	private int dataID; // 表示data的唯一标识
	private int hotness; // 表示data的冷热度信息
	private boolean isInteractive; // 表示是否是交互式任务需要的数据，是则为true，否则为false
	private boolean status; // 表示数据是否在NVM之中，是则为true，否则为false
	private int type = 5; // 表示自定义的数据类别，取值1~5,默认为在Disk中的其他，即为3

	public Data(int dataID, int hotness, boolean isInteractive, boolean status, int type) {
		super();
		this.dataID = dataID;
		this.hotness = hotness;
		this.isInteractive = isInteractive;
		this.status = status;
		this.type = type;
	}

	public Data(int dataID, int hotness, boolean isInteractive, boolean status) {
		super();
		this.dataID = dataID;
		this.hotness = hotness;
		this.isInteractive = isInteractive;
		this.status = status;
	}

	public Data() {
		super();
	}

	public int getDataID() {
		return dataID;
	}

	public void setDataID(int dataID) {
		this.dataID = dataID;
	}

	public int getHotness() {
		return hotness;
	}

	public void setHotness(int hotness) {
		this.hotness = hotness;
	}

	public boolean isInteractive() {
		return isInteractive;
	}

	public void setInteractive(boolean isInteractive) {
		this.isInteractive = isInteractive;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
