package cn.nuaa.qjj.domain;

import java.util.List;

public class Job {
	private int jobID; // 标识job的唯一属性。
	private int arriveTime;// 表示job的到达时间
	private List<Task> tasks; // 表示job中包含的所有task
	private int deadline; // 表示整个任务的最晚截止时间
	private int starttime; // 表示job的开始时间
	private int endtime; // 表示整个任务的完成时间
	private int exeTime; // job的执行时间，交互式的表示单个执行时间，批量任务总的执行时间
	private int utility; // 表示整个job的总收益
	private boolean isInteractive = true; // 表示是否是交互式job

	public boolean isInteractive() {
		return isInteractive;
	}

	public void setInteractive(boolean isInteractive) {
		this.isInteractive = isInteractive;
	}

	public Job(int jobID, int arriveTime, List<Task> tasks, int deadline, int exeTime, int utility) {
		super();
		this.jobID = jobID;
		this.arriveTime = arriveTime;
		this.tasks = tasks;
		this.deadline = deadline;
		this.exeTime = exeTime;
		this.utility = utility;
	}

	@Override
	public String toString() {
		return "Job [jobID=" + jobID + ", arriveTime=" + arriveTime + ", tasks=" + tasks + ", deadline=" + deadline
				+ ", starttime=" + starttime + ", endtime=" + endtime + ", exeTime=" + exeTime + ", utility=" + utility
				+ ", isInteractive=" + isInteractive + "]";
	}

	public int getStarttime() {
		return starttime;
	}

	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}

	public int getEndtime() {
		return endtime;
	}

	public void setEndtime(int endtime) {
		this.endtime = endtime;
	}

	public int getExeTime() {
		return exeTime;
	}

	public void setExeTime(int exeTime) {
		this.exeTime = exeTime;
	}

	public int getDeadline() {
		return deadline;
	}

	public int getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(int arriveTime) {
		this.arriveTime = arriveTime;
	}

	public int getUtility() {
		return utility;
	}

	public void setUtility(int utility) {
		this.utility = utility;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public Job() {
		super();
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
