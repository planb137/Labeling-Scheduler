package cn.nuaa.qjj.util;

import java.util.ArrayList;
import java.util.List;

import cn.nuaa.qjj.domain.Job;

public class RemoveTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Job job1 = new Job(1, 1, null, 1, 1, 1);
		Job job2 = new Job(2, 1, null, 1, 1, 1);
		Job job3 = new Job(3, 1, null, 1, 1, 1);
		Job job4 = new Job(4, 1, null, 1, 1, 1);
		List<Job> jobList = new ArrayList<Job>();
		jobList.add(job1);
		jobList.add(job2);
		jobList.add(job3);
		jobList.add(job4);
		jobList.remove(job2);
		System.out.println(jobList.toString());
	}

}
