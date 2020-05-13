package cn.nuaa.qjj.test;

import java.util.ArrayList;
import java.util.List;

import cn.nuaa.qjj.domain.Job;
import cn.nuaa.qjj.util.SortJob;

public class JobSortTest {
	public static void main(String[] args) {
		Job job = new Job(1, 4, null, 5, 1, 2);
		Job job1 = new Job(2, 2, null, 4, 1, 2);
		List<Job> list = new ArrayList<Job>();
		list.add(job);
		list.add(job1);
		System.out.println("≈≈–Ú«∞£∫" + list.get(0).toString());
		new SortJob().SortJobByLatSheTime(list);
		System.out.println("≈≈–Ú∫Û£∫" + list.get(0).toString());

	}
}
