package cn.nuaa.qjj.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.nuaa.qjj.domain.Data;

public class SortDataInNVM implements Comparator<Data> {

	@Override
	public int compare(Data o1, Data o2) {
		// TODO Auto-generated method stub
		int type = o1.getType();
		int type2 = o2.getType();
		int hotness1 = o1.getHotness();
		int hotness2 = o2.getHotness();
		if (type < type2) {
			return 1;
		} else if (type > type2) {
			return -1;
		} else {
			if (hotness1 > hotness2) {
				return 1;
			}
			if (hotness1 < hotness2) {
				return -1;
			}
		}
		return 0;
	}

	public void sortData(List<Data> data) {
		Collections.sort(data, new SortDataInNVM());
	}
}
