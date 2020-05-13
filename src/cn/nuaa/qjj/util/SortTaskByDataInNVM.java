package cn.nuaa.qjj.util;

import java.util.Comparator;

import cn.nuaa.qjj.domain.Task;
import cn.nuaa.qjj.main.DataAbout;

public class SortTaskByDataInNVM implements Comparator<Task> {

	@Override
	public int compare(Task o1, Task o2) {
		// TODO Auto-generated method stub
		int id = o1.getRequiredData();
		int id2 = o2.getRequiredData();
		boolean in  = new DataAbout().dataInNVM(id);
		boolean in2  = new DataAbout().dataInNVM(id2);
		if(in == true && in2 == false){
			return 1;
		}
		return 0;
	}

}
