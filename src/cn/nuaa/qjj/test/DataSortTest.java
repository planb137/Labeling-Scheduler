package cn.nuaa.qjj.test;

import java.util.ArrayList;
import java.util.List;

import cn.nuaa.qjj.domain.Data;
import cn.nuaa.qjj.util.SortDataInNVM;

public class DataSortTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Data d1 = new Data(1, 450, false, false, 2);
		Data d2 = new Data(2, 500, false, false, 2);
		Data d3 = new Data(3, 450, true, false, 1);
		Data d4 = new Data(4, 320, true, false, 1);
		List<Data> dataList = new ArrayList<Data>();
		dataList.add(d1);
		dataList.add(d2);
		dataList.add(d3);
		dataList.add(d4);
		System.out.println("≈≈–Ú«∞£∫");
		for (Data dt : dataList) {
			System.out.println(dt.toString());
		}
		//new SortDataInDisk().sortData(dataList);
		new SortDataInNVM().sortData(dataList);
		System.out.println("≈≈–Ú∫Û£∫");
		for (Data dt : dataList) {
			System.out.println(dt.toString());
		}
	}

}
