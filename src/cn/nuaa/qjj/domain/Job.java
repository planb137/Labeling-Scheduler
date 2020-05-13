package cn.nuaa.qjj.domain;

import java.util.List;

public class Job {
	private int jobID; // ��ʶjob��Ψһ���ԡ�
	private int arriveTime;// ��ʾjob�ĵ���ʱ��
	private List<Task> tasks; // ��ʾjob�а���������task
	private int deadline; // ��ʾ��������������ֹʱ��
	private int starttime; // ��ʾjob�Ŀ�ʼʱ��
	private int endtime; // ��ʾ������������ʱ��
	private int exeTime; // job��ִ��ʱ�䣬����ʽ�ı�ʾ����ִ��ʱ�䣬���������ܵ�ִ��ʱ��
	private int utility; // ��ʾ����job��������
	private boolean isInteractive = true; // ��ʾ�Ƿ��ǽ���ʽjob

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
