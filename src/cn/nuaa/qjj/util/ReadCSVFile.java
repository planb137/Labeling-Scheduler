package cn.nuaa.qjj.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

import cn.nuaa.qjj.domain.Data;
import cn.nuaa.qjj.domain.Disk;
import cn.nuaa.qjj.domain.Job;
import cn.nuaa.qjj.domain.Task;
import cn.nuaa.qjj.main.DataAbout;
import cn.nuaa.qjj.main.MainSchdule;

//CSV读取，并封装对象
public class ReadCSVFile {
	public static int num = 0;// 用于生成task的id

	/**
	 * CSV导出
	 *
	 */
	public static List<Job> readCsv() throws IOException {
		String srcCSV = "C:/Users/planb/Desktop/data_generator(1)/data_generator/output/jobs2.csv";
		// String targetFile = "F:/test.csv";
		CsvReader reader = new CsvReader(srcCSV, ',', Charset.forName("UTF-8"));
		String[] header = {};
		List<Job> jobList = new ArrayList<Job>();
		while (reader.readRecord()) {
			// 把头保存起来
			if (reader.getCurrentRecord() == 0) {
				header = reader.getValues();
			}
			String s = reader.getRawRecord();
			// System.out.println(s);
			if (s != null) {
				String[] temp = s.split(",");
				Job job = new Job();
				Job job1 = new Job();
				job.setJobID(Integer.parseInt(temp[1]));
				job.setArriveTime(Integer.parseInt(temp[0]));
				job1.setJobID(Integer.parseInt(temp[1])+30000);
				job1.setArriveTime(Integer.parseInt(temp[0]));
				int count = Integer.parseInt(temp[2]);
				// String[] dataId = temp[7].replaceAll("\"",
				// "").split("\\.");//
				// 去掉字符串两端的双引号
				List<Task> taskList = new ArrayList<Task>();
				List<Task> taskList1 = new ArrayList<Task>();
				if (count == 1) {
					Task tk = new Task();
					Task tk1 = new Task();
					Data dt = new Data();
					Data dt1 = new Data();
					tk.setTaskID(ReadCSVFile.num);
					tk.setArriveTime(Integer.parseInt(temp[0]));
					tk.setDeadline(
							2 * Integer.parseInt(temp[3]) + MainSchdule.readDataTime + Integer.parseInt(temp[0]));
					tk.setRequiredData(Integer.parseInt(temp[4]));
					tk.setType(true);
					tk.setPu(MainSchdule.w0);
					tk.setStatus(2);
					tk.setExeTime(Integer.parseInt(temp[3]));

					tk1.setTaskID(ReadCSVFile.num+50000);
					tk1.setArriveTime(Integer.parseInt(temp[0]));
					tk1.setDeadline(
							2 * Integer.parseInt(temp[3]) + MainSchdule.readDataTime + Integer.parseInt(temp[0]));
					tk1.setRequiredData(Integer.parseInt(temp[4])+1000);
					tk1.setType(true);
					tk1.setPu(MainSchdule.w0);
					tk1.setStatus(2);
					tk1.setExeTime(Integer.parseInt(temp[3]));

					dt.setInteractive(true);
					dt.setDataID(Integer.parseInt(temp[4]));
					dt.setHotness(0);
					dt.setStatus(false);
					dt.setType(5);

					dt1.setInteractive(true);
					dt1.setDataID(Integer.parseInt(temp[4])+1000);
					dt1.setHotness(0);
					dt1.setStatus(false);
					dt1.setType(5);

					if (null != Disk.disk) {
						// 首先检查数据是否已经存在
						boolean exist = new DataAbout().exitData(Integer.parseInt(temp[4]));
						boolean exist1 = new DataAbout().exitData(Integer.parseInt(temp[4])+1000);
						if (!exist) {
							Disk.disk.add(dt);// 初始化Disk
						}
						if (!exist1) {
							Disk.disk.add(dt1);// 初始化Disk
						}
					}
					taskList.add(tk);
					taskList1.add(tk1);
					ReadCSVFile.num++; // 用于TaskId的自增，不重复。

					job.setInteractive(true);
					job.setDeadline(
							2 * Integer.parseInt(temp[3]) + MainSchdule.readDataTime + Integer.parseInt(temp[0]));
					job.setUtility(MainSchdule.w0);
					job.setExeTime(Integer.parseInt(temp[3]));


					job1.setInteractive(true);
					job1.setDeadline(
							2 * Integer.parseInt(temp[3]) + MainSchdule.readDataTime + Integer.parseInt(temp[0]));
					job1.setUtility(MainSchdule.w0);
					job1.setExeTime(Integer.parseInt(temp[3]));
				} else {
					String[] dataId = temp[5].split("\\.");
					for (int j = 0; j < count; j++) {
						Task tk = new Task();
						Data dt = new Data();
						tk.setTaskID(ReadCSVFile.num);
						tk.setArriveTime(Integer.parseInt(temp[0]));
						tk.setDeadline(Integer.parseInt(temp[4]) + Integer.parseInt(temp[0]));
						tk.setRequiredData(Integer.parseInt(dataId[j]));
						// 初始化Data数据，全部存进Disk
						dt.setDataID(Integer.parseInt(dataId[j]));
						dt.setHotness(0);
						dt.setStatus(false);
						dt.setType(5);
						dt.setInteractive(false);
						tk.setExeTime(Integer.parseInt(temp[3]));
						tk.setType(false);
						tk.setStatus(2);
						tk.setPu(MainSchdule.w1);
						taskList.add(tk);
						if (null != Disk.disk) {
							// 首先检查数据是否已经存在
							boolean exist = new DataAbout().exitData(Integer.parseInt(dataId[j]));
							if (!exist) {
								Disk.disk.add(dt);// 初始化Disk
							}
						}
						ReadCSVFile.num++; // 用于TaskId的自增，不重复。
						job.setInteractive(false);
						job.setDeadline(Integer.parseInt(temp[4]) + Integer.parseInt(temp[0]));
						job.setUtility(MainSchdule.w1);
						job.setExeTime(Integer.parseInt(temp[3]) * count);
					}
				}

				job.setTasks(taskList);
				job1.setTasks(taskList1);
				jobList.add(job);// 初始化所有job
				jobList.add(job1);// 初始化所有job
			}

		}
		reader.close();
		return jobList;
	}

	public static void main(String[] args) {
		try {
			int size = 0;
			List<Job> jobList = readCsv();
			for (Job job : jobList) {
				System.out.println(job.toString());
			}
			//System.out.println("共有task  "+size+"个");
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * System.out.println("===================================="); for (Data
		 * dt : Disk.disk) { System.out.println(dt.toString()); }
		 */

		//System.out.println("共有数据：" + Disk.disk.size());
	}
}