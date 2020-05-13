package cn.nuaa.qjj.domain;

public class Task {

	private int taskID; // 表示task的唯一标识
	private int arriveTime; // 标识任务的到达时间
	private int deadline; // 表示任务的截止时间
	private int requiredData; // 表示任务需要数据的ID
	private int exeTime; // 表示任务执行需要时间
	private int startTime; // 表示任务在执行时候的开始时间
	private int endTime; // 表示任务执行的完成时间
	private boolean type; // 表示交互任务还是常规任务，交互任务为true，常规为false。
	private int status; // 表示任务的状态，执行为1，等待为0，结束为-1,其他为2（如没有接受任务）。
	private int pu; // 表示任务的收益

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
