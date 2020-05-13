package cn.nuaa.qjj.domain;

public class Task {

	private int taskID; // ��ʾtask��Ψһ��ʶ
	private int arriveTime; // ��ʶ����ĵ���ʱ��
	private int deadline; // ��ʾ����Ľ�ֹʱ��
	private int requiredData; // ��ʾ������Ҫ���ݵ�ID
	private int exeTime; // ��ʾ����ִ����Ҫʱ��
	private int startTime; // ��ʾ������ִ��ʱ��Ŀ�ʼʱ��
	private int endTime; // ��ʾ����ִ�е����ʱ��
	private boolean type; // ��ʾ���������ǳ������񣬽�������Ϊtrue������Ϊfalse��
	private int status; // ��ʾ�����״̬��ִ��Ϊ1���ȴ�Ϊ0������Ϊ-1,����Ϊ2����û�н������񣩡�
	private int pu; // ��ʾ���������

	public Task() {
		super();
	}

	@Override
	public String toString() {
		return "Task [taskID=" + taskID + ", arriveTime=" + arriveTime + ", deadline=" + deadline + ", requiredData="
				+ requiredData + ", exeTime=" + exeTime + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", type=" + type + ", status=" + status + ", pu=" + pu + "]";
	}

	public Task(int taskID, int deadline, int requiredData, int exeTime, boolean type, int pu) {
		super();
		this.taskID = taskID;
		this.deadline = deadline;
		this.requiredData = requiredData;
		this.exeTime = exeTime;
		this.type = type;
		this.pu = pu;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public int getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(int arriveTime) {
		this.arriveTime = arriveTime;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public int getRequiredData() {
		return requiredData;
	}

	public void setRequiredData(int requiredData) {
		this.requiredData = requiredData;
	}

	public int getExeTime() {
		return exeTime;
	}

	public void setExeTime(int exeTime) {
		this.exeTime = exeTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPu() {
		return pu;
	}

	public void setPu(int pu) {
		this.pu = pu;
	}
}
