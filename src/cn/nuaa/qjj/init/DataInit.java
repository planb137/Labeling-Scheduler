package cn.nuaa.qjj.init;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.nuaa.qjj.domain.Job;
import cn.nuaa.qjj.util.ReadCSVFile;

public class DataInit {
	// ��ʼ����������,������ʼ״̬�µ�����Ǩ�ƣ���������������Job
	public List<Job> dataInput() {
		List<Job> jobList = new ArrayList<Job>();
		// ���ļ��������е����ݳ�ʼ����
		try {
			jobList = ReadCSVFile.readCsv();
			/*
			 * for (Job job : jobList) { System.out.println(job.toString());
			 * System.out.println(); }
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jobList;
	}
}
