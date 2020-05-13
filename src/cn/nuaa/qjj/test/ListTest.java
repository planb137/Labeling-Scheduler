package cn.nuaa.qjj.test;

import java.util.ArrayList;
import java.util.List;

import cn.nuaa.qjj.domain.Data;

//²âÊÔList
public class ListTest {
	public static void main(String[] args) {
		List<Data> list1 = new ArrayList<Data>();
		List<Data> list2 = new ArrayList<Data>();
		Data d1 = new Data(1, 100, true, false);
		list1.add(d1);
		list2.add(d1);
		list1.remove(d1);
		System.out.println("====list1===" + list1);

	}

}
