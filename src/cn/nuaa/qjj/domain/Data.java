package cn.nuaa.qjj.domain;

public class Data {
	@Override
	public String toString() {
		return "Data [dataID=" + dataID + ", hotness=" + hotness + ", isInteractive=" + isInteractive + ", status="
				+ status + ", type=" + type + "]";
	}

	private int dataID; // ��ʾdata��Ψһ��ʶ
	private int hotness; // ��ʾdata�����ȶ���Ϣ
	private boolean isInteractive; // ��ʾ�Ƿ��ǽ���ʽ������Ҫ�����ݣ�����Ϊtrue������Ϊfalse
	private boolean status; // ��ʾ�����Ƿ���NVM֮�У�����Ϊtrue������Ϊfalse
	private int type = 5; // ��ʾ�Զ�����������ȡֵ1~5,Ĭ��Ϊ��Disk�е���������Ϊ3

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
