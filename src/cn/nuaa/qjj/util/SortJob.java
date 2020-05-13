package cn.nuaa.qjj.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.nuaa.qjj.domain.Job;
import cn.nuaa.qjj.main.TaskAbout;

//自定义比较器,根据最晚调度时间，进行排序
public class SortJob implements Comparator<Job> {
	@Override
	public int compare(Job o1, Job o2) {
		// TODO Auto-generated method stub
		int lastTime = new TaskAbout().getLatestTime(o1);
		int lastTime2 = new TaskAbout().getLatestTime(o2);
		if (lastTime < lastTime2) {
			return -1;
		}
		if (lastTime > lastTime2) {
			return 1;
		}
		return 0;
	}

	public void SortJobByLatSheTime(List<Job> job) {
		Collections.sort(job, new SortJob());
	}

}
